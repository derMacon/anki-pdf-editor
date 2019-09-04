import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

import { HotKeys } from "react-hotkeys";

const keyMap = {
  MOVE_UP: "up"
};

const handlers = {
  MOVE_UP: event => console.log("Move up hotkey called!")
};

export default class App extends React.Component {

  render() {
    return (
      <HotKeys keyMap={keyMap} handlers={handlers}>
        <div className="App">
          <Menu/>
          <MainWindow/>
        </div>
      </HotKeys>
    );
  }
}
