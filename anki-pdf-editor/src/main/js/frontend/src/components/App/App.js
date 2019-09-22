import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import {ApiConnector} from '../../util/ApiConnector';
import { HotKeys } from "react-hotkeys";
import './App.css';

export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {

      // doc obj will be distributed throughout whole component structure
      // contains the file, currPage and maxPage number.
      document: {
        projectName: 'TestDeck',
        updateProjectName: (newName) => this.setState({projectName: newName}),
        file: ApiConnector.selectedPdf,
        fileName: () => ApiConnector.selectedPdfName(),
        selectFile: () => ApiConnector.selectNewPdf(),
        selectDeck: () => ApiConnector.selectDeck(),
        currPage: 1,
        pageCnt: undefined,
        updatePageCnt: (newPageCnt) => this.setState({pageCnt: newPageCnt}),
        turnNextPage: () => this.turnNextPage(),
        turnPrevPage: () => this.turnPrevPage(),
        click: this.handleClick
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
    this.setPageCnt = this.setPageCnt.bind(this);
    this.turnNextPage = this.turnNextPage.bind(this);
    this.turnPrevPage = this.turnPrevPage.bind(this);
    this.updatePageNum = this.updatePageNum.bind(this);

    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    console.log('Click happened');
  }

  // todo not working for some reason
  componentDidMount() {
    this.setPageCnt(300);
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
