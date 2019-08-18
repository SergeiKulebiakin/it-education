import React, { Component } from 'react';
import logo from './profile.png';
import './App.css';
import { BrowserRouter, Route, Link, Switch } from 'react-router-dom';
import Navbar from "./components/NavBar";
import FormLogin from "./components/LoginForm";
import MainPage from "./components/MainPage";
import SecuredPage from "./components/SecuredPage";
import RegisterForm from "./components/RegisterForm";

class App extends Component {
    render() {
        return (
          <div>
            <div className="App">
              <Navbar />
              <div className="App-header">
                <Switch>
                  <Route exact path='/' component={MainPage}/>
                  <Route exact path='/login' component={FormLogin}/>
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
