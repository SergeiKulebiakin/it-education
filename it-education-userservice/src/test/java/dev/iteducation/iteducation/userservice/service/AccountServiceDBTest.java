package dev.iteducation.iteducation.userservice.service;

import dev.iteducation.iteducation.userservice.SpringTest;
import dev.iteducation.iteducation.userservice.domain.document.Account;
import dev.iteducation.iteducation.userservice.domain.document.ActivationLink;
import dev.iteducation.iteducation.userservice.domain.document.UserRole;
import dev.iteducation.iteducation.userservice.domain.repository.AccountRepository;
import dev.iteducation.iteducation.userservice.domain.repository.ActivationLinkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 26.08.2019.
 */
class AccountServiceImplDBTest extends SpringTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ActivationLinkRepository activationLinkRepository;

	/**
	 * Testing correct account creation and verification
	 */
	@Test
	void createAccountTest() {

		// Values for a test account
		final var email = "user@example.com";
		final var username = "user";
		final var pass = "pass";

		// Verify activation link with test email doesn't exist
		StepVerifier.create(activationLinkRepository.findAll().filter(l -> l.getEmail().equals(email)))
				.expectNextCount(0)
				.verifyComplete();

		// Verify account with test email doesn't exist
		StepVerifier.create(accountRepository.findByEmail(email))
				.expectNextCount(0)
				.verifyComplete();

		// Creating the account
		StepVerifier.create(accountService.createAccount(email, username, pass, pass))
				.verifyComplete();

		// Verification created account
		var accounts = new ArrayList<Account>();
		StepVerifier.create(accountRepository.findByEmail(email))
				.consumeNextWith(account -> {
					assertEquals(email, account.getEmail());
					assertEquals(username, account.getName());
					assertNotEquals(pass, account.getPassword());
					assertFalse(account.getEnabled());
					assertNull(account.getRoles());
					accounts.add(account);
				}).verifyComplete();
		var account = accounts.get(0);

		// Verification created activation link
		var links = new ArrayList<ActivationLink>();
		StepVerifier.create(activationLinkRepository.findAll()
				.filter(link -> link.getEmail().equals(email)))
				.consumeNextWith(link -> {
					assertNotNull(link.getId());
					assertEquals(account.getEmail(), link.getEmail());
					assertEquals(account.getId(), link.getPayload());
					links.add(link);
				}).verifyComplete();
		var link = links.get(0);

		// Account verification
		StepVerifier.create(accountService.verifyAccount(link.getId()))
				.verifyComplete();

		// Account has updated correctly and activation link has deleted
		StepVerifier.create(activationLinkRepository.findAll().filter(l -> l.getEmail().equals(email)))
				.expectNextCount(0)
				.verifyComplete();
		StepVerifier.create(accountRepository.findByEmail(email))
				.expectNextMatches(acc -> acc.getEnabled() && acc.getRoles().equals(List.of(UserRole.ROLE_USER)))
				.verifyComplete();
	}
}
