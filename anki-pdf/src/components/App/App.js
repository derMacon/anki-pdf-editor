import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

export default class App extends React.Component {

  render() {
    return (
      <div className="App">
        <Menu/>
        <MainWindow/>
      </div>
    );
  }
}
