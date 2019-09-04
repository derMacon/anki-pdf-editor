import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

import { HotKeys } from "react-hotkeys";


export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      document: {
        currPage: 1
      },

      keyMap: {
        NEXT_PAGE: "z",
        PREV_PAGE: "shift+z"
      },

      handlers: {
        NEXT_PAGE: event => this.turnNextPage(),
        PREV_PAGE: event => this.turnPrevPage()
      }

    };
  }

  turnNextPage() {
    console.log('next page');
    const page = this.state.currPage + 1;
    this.setState({currPage: page});
  }

  turnPrevPage() {
    console.log('prev page');
    const page = this.state.currPage - 1;
    if (page >= 0) {
      this.setState({currPage: page});
    }
  }

  render() {
    return (
      <HotKeys keyMap={this.state.keyMap} handlers={this.state.handlers}>
        <div className="App">
          <Menu currPage={this.state.document}/>
          <MainWindow currPage={this.state.document}/>
        </div>
      </HotKeys>
    );
  }
}
