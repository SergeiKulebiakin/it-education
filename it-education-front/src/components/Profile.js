import React, { Component } from 'react';
import axios from 'axios';

class TestArea extends Component {

  doLogout = (e) => {
    e.preventDefault();
    localStorage.removeItem('auth.token');
    window.location = "/";
  }

  render() {
    return (
      <div>
        <div className="block minw-40">
          <button onClick={this.doLogout} className="btn btn-lg btn-secondary btn-block focus-gray-shadow">Logout</button>
        </div>
      </div>
    );
  }
}

export default TestArea;
