import React, { Component } from 'react';
import { Route, Link } from 'react-router-dom';

class Navbar extends Component {
    render() {
        return (
          <nav className="navbar navbar-light navbar-expand-lg fixed-top bg-secondary text-uppercase" id="mainNav">
              <div className="container">
              <a className="navbar-brand js-scroll-trigger" href="/">IT Education</a>
              <button data-toggle="collapse" data-target="#navbarResponsive" className="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" aria-controls="navbarResponsive"
                      aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                  <div className="collapse navbar-collapse" id="navbarResponsive">
                      <ul className="nav navbar-nav ml-auto">
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/articles"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">articles</a></Link></li>
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/mentor"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">find mentor</a></Link></li>
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/about"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">about</a></Link></li>
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/login"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">sign in</a></Link></li>
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/register"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">sign up</a></Link></li>
                          <li className="nav-item mx-0 mx-lg-1" role="presentation"><Link to="/logout"><a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">logout</a></Link></li>
                      </ul>
                  </div>
              </div>
          </nav>
        );
    }
}

export default Navbar;
