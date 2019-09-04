import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

import { HotKeys } from "react-hotkeys";

const keyMap = {
  NEXT_PAGE: "up"
};

const handlers = {
  NEXT_PAGE: event => console.log("next")
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
