import React from 'react';
import {Menu} from '../Menu/Menu';
import {MainWindow} from '../MainWindow/MainWindow';
import './App.css';

import Hotkeys from 'react-hot-keys';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      output: 'Hello, I am a component that listens to keydown and keyup of a',
    }
  }
  onKeyUp(keyName, e, handle) {
    console.log("test:onKeyUp", e, handle)
    this.setState({
      output: `onKeyUp ${keyName}`,
    });
  }
  onKeyDown(keyName, e, handle) {
    console.log("test:onKeyDown", keyName, e, handle)
    this.setState({
      output: `onKeyDown ${keyName}`,
    });
  }
  render() {
    return (
      <Hotkeys
        keyName="shift+a,alt+s"
        onKeyDown={this.onKeyDown.bind(this)}
        onKeyUp={this.onKeyUp.bind(this)}
      >
        <div style={{ padding: "50px" }}>
          {this.state.output}
        </div>
      </Hotkeys>
    )
  }
}

/*
  render() {
    return (
      <div className="App">
        <Menu/>
        <MainWindow/>
      </div>
    );
  }
}
*/
