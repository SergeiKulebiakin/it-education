import React from 'react';

const state = undefined;

class AccountActions extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      options: [1,2,3]
    };
  }
  render() {
    return (
      <li className="nav-item mx-0 mx-lg-1" role="presentation">
        <a className="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger">
          <select>{this.state.options.map((option, idx) => <option key={idx}>{option}</option>)}</select>
        </a>
      </li>
    );
  }
}
export default AccountActions
