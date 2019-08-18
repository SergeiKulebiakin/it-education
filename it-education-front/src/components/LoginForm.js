import React, { Component } from 'react';

class LoginForm extends Component {
    render() {
        return (
            <div className="block minw-40">
                <form id="form-sign-in" className="form-signin" method="post" action="/login">
                    <h2 className="form-signin-heading">Please sign in</h2>
                    <hr class="star-light mb-5"/>
                    <p>
                        <label for="sign-in-email" className="sr-only">Email</label>
                        <input type="text" id="sign-in-email" name="email" className="form-control focus-gray-shadow" placeholder="email" required autofocus/>
                    </p>
                    <p>
                        <label for="sign-in-password" className="sr-only">Password</label>
                        <input type="password" id="sign-in-password" name="password" className="form-control focus-gray-shadow" placeholder="Password" required/>
                    </p>
                    <button className="btn btn-lg btn-secondary btn-block focus-gray-shadow" type="submit">Sign in</button>
                </form>
            </div>
        );
    }
}

export default LoginForm;
