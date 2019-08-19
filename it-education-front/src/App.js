import React, { Component } from 'react';
import logo from './profile.png';
import './App.css';
import { BrowserRouter, Route, Link, Switch } from 'react-router-dom';
import Navbar from "./components/NavBar";
import FormLogin from "./components/LoginForm";
import MainPage from "./components/MainPage";
import SecuredPage from "./components/SecuredPage";
import RegisterForm from "./components/RegisterForm";

const authHeader = undefined;


class App extends Component {

  testMethod = async (e) => {
    e.preventDefault();
    console.log('test not default');
  }

  authenticate = async (e) => {
    e.preventDefault();
    var creds = {
      email: e.target.elements.email.value,
      password: e.target.elements.password.value
    };
    console.log(creds);
    console.log("fetching");
    var resp = await fetch('/auth', {
      method: 'post',
      body: JSON.stringify(creds),
      headers: {
        "Content-Type": "application/json"
      }
    });
    var token = await resp.json();
    console.log(token);
    this.authHeader = "Bearer " + token;
  }

  render() {
    return (
      <div>
        <div className="App">
          <Navbar />
          <div className="App-header">
            <Switch>
              <Route exact path='/' component={MainPage}/>
              <Route exact path='/login' render={(props) => <FormLogin {...props} authMethod={this.authenticate} /> } />
              <Route exact path='/about' component={SecuredPage}/>
              <Route exact path='/register' component={RegisterForm}/>
            </Switch>
          </div>
        </div>
      </div>
    )
  }
}

export default App;
