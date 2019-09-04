import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';

export default class App extends React.Component {

  render() {
    return (
      <div>
        <Menu/>
        <MainWindow/>
      </div>
    );
  }
}
