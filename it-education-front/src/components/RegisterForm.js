import React, { Component } from 'react';
import '../App.css';
import { BrowserRouter, Route, Link, Switch } from 'react-router-dom';

class RegisterForm extends Component {
    render() {
        return (
            <div className="block minw-40">
                <form id="form-sign-up" className="form-signup" method="post" action="/account">
                    <h2 className="form-signin-heading">Create new account</h2>
                    <hr class="star-light mb-5"/>
                    <p>
                        <label for="sign-up-email" className="sr-only">Email</label>
                        <input type="email" id="sign-up-email" name="email" className="form-control focus-gray-shadow" placeholder="email" required autofocus/>
                    </p>
                    <p>
                        <label for="sign-up-username" className="sr-only">Name</label>
                        <input type="text" id="sign-up-username" name="username" className="form-control focus-gray-shadow" placeholder="username" required/>
                    </p>
                    <p>
                        <label for="sign-up-password" className="sr-only">Password</label>
                        <input type="password" id="sign-up-password" name="password" className="form-control focus-gray-shadow" placeholder="Password" required/>
                    </p>
                    <p>
                        <label for="sign-up-confirm-password" className="sr-only">Confirm password</label>
                        <input type="password" id="sign-up-confirm-password" name="password" className="form-control focus-gray-shadow" placeholder="Confirm password" required/>
                    </p>
                    <button className="btn btn-lg btn-secondary btn-block focus-gray-shadow" type="submit">Sign up</button>
                </form>
            </div>
        );
    }
}

export default RegisterForm;
