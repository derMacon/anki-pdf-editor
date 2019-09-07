import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

import { HotKeys } from "react-hotkeys";


export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {

      // doc obj will be distributed throughout whole component structure
      // contains the file, currPage and maxPage number.
      document: {
        file: "/example.pdf",
        currPage: 1,
        pageCnt: undefined
      },

      // Keymap for the keybindings, each keykombination for the shortcuts
      // has to be declared in this obj so it can be used in the handelers
      // obj.
      keyMap: {
        NEXT_PAGE: "z",
        PREV_PAGE: "shift+z"
      },

      // handler methods for the declared keykombination.
      handlers: {
        NEXT_PAGE: event => this.turnNextPage(),
        PREV_PAGE: event => this.turnPrevPage()
      }

    };
    this.updatePageNum = this.updatePageNum.bind(this);
    this.setPageCnt = this.setPageCnt.bind(this);
  }

  componentDidMount() {
    this.setPageCnt(5);
  }

  // Setter function for the overall page count, will be used
  // as a callback in the document obj.
  setPageCnt(pageCnt) {
    console.log('pageCnt: ' + pageCnt);
    const newDoc = this.state.document;
    newDoc.pageCnt = pageCnt;
    this.setState({document: newDoc});
  }


  // increments the currPage count and updates the doc if possible.
  turnNextPage() {
    let page = Number(this.state.document.currPage) + 1;
    if (this.state.document.pageCnt >= page) {
      console.log('next page ' + page + '/' + this.state.document.pageCnt);
      this.updatePageNum(page);
    }
  }

  // decrements the currPage count and updates the doc if possible.
  turnPrevPage() {
    const page = this.state.document.currPage - 1;
    if (page > 0) {
      console.log('prev page');
      this.updatePageNum(page);
    }
  }

  // updates the internal doc obj
  updatePageNum(pageNum) {
    const newDoc = this.state.document;
    newDoc.currPage = pageNum;
    this.setState({document: newDoc});
  }

  // actual render function of the component
  render() {
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
