import React, { Component } from 'react';
import axios from 'axios';

class TestArea extends Component {

  state = {
    username: undefined,
    userId: undefined
  }

  getSecured = (e) => {
    e.preventDefault();
    axios.get('/auth')
    .then((response) => {
      this.state.username = response.data.userName;
      this.state.userId = response.data.userId;
      this.forceUpdate();
    })
  }

  render() {
    return (
      <div>
        <h1>Hello, {this.state.username} ({this.state.userId})!</h1>
        <div className="block minw-40">
          <button onClick={this.getSecured} className="btn btn-lg btn-secondary btn-block focus-gray-shadow">Get secured</button>
        </div>
      </div>
    );
  }
}

export default TestArea;
