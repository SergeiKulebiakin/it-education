import React, { Component } from 'react';
import logo from './profile.png';
import './App.css';
import { BrowserRouter, Route, Link, Switch } from 'react-router-dom';
import axios from 'axios';
import Navbar from "./components/NavBar";
import FormLogin from "./components/LoginForm";
import MainPage from "./components/MainPage";
import SecuredPage from "./components/SecuredPage";
import RegisterForm from "./components/RegisterForm";
import TestArea from "./components/TestArea";
import Profile from "./components/Profile";

class App extends Component {

  state = {
    userId: undefined,
    userName: undefined
  };

  constructor(props) {
    super(props);
    this.setAuthHeader();
  }

  setAuthHeader = () => {
    var token = localStorage.getItem('auth.token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = "Bearer ".concat(token);
      axios.get('/auth')
      .then((response) => {
        this.state.userId = response.data.userId;
        this.state.userName = response.data.userName;
        console.log(this.state.userName);
        console.log(this.state.userId);
        this.forceUpdate();
      }, (error) => {
        console.log(error);
      });
    }
  }

  authenticate = async (e) => {
    e.preventDefault();
    var creds = {
      email: e.target.elements.email.value,
      password: e.target.elements.password.value
    };
    axios.post('/auth', creds)
    .then((response) => {
        localStorage.setItem('auth.token', response.data.token);
        this.setAuthHeader();
      }, (error) => {
        console.log(error);
      }
    );
  }

  render() {
    return (
      <div>
        <div className="App">
          <Navbar
            userName={this.state.userName}
            userId={this.state.id}
          />
          <div className="App-header">
            <Switch>
              <Route exact path='/' component={MainPage}/>
              <Route exact path='/login' render={(props) => <FormLogin {...props} authMethod={this.authenticate} /> } />
              <Route exact path='/about' component={TestArea}/>
              <Route exact path='/register' component={RegisterForm}/>
              <Route exact path='/profile' component={Profile}/>
            </Switch>
          </div>
        </div>
      </div>
    )
  }
}

export default App;
