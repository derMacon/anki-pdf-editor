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
        file: "/example.pdf",
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
    this.updatePageNum = this.updatePageNum.bind(this);
  }

  turnNextPage() {
    console.log('next page');
    const page = this.state.document.currPage + 1;
    this.updatePageNum(page);
  }

  turnPrevPage() {
    const page = this.state.document.currPage - 1;
    if (page > 0) {
      console.log('prev page');
      this.updatePageNum(page);
    }
  }

  updatePageNum(pageNum) {
    const newDoc = this.state.document;
    newDoc.currPage = pageNum;
    this.setState({document: newDoc});
  }

  render() {
    console.log('app: ' + this.state.document.currPage);
    return (
      <HotKeys keyMap={this.state.keyMap} handlers={this.state.handlers}>
        <div className="App">
          <Menu document={this.state.document}/>
          <MainWindow document={this.state.document}/>
        </div>
      </HotKeys>
    );
  }
}
