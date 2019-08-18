import React from 'react';
import logo from '../profile.png';

function MainPage() {
  return (
    <div>
      <img src={logo} alt="logo" />
      <p>
        Edit <code>src/App.js</code> and save to reload.
      </p>
      <a
        className="App-link"
        href="https://reactjs.org"
        target="_blank"
        rel="noopener noreferrer"
      >
        Learn React
      </a>
    </div>
  );
}

export default MainPage;
