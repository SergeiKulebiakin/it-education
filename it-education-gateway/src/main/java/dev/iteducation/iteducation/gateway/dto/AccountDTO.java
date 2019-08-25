package dev.iteducation.iteducation.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AccountDTO implements Serializable {

	private static final long serialVersionUID = -670064950925973295L;

	private String userId;
	private String userName;

}
