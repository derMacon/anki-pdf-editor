import React from 'react';
// import {PdfReader} from '../PdfReader/PdfReader';
import {Reader} from '../../PdfReader/Reader';
import './SmallPane.css';

import arrowIcon from './arrowIcon.png';

export class SmallPane extends React.Component {
  constructor(props) {
    super(props);
    this.state = {random: 0};
    this.resetIframe = this.resetIframe.bind(this);
  }

  resetIframe() {
    this.props.pageTurnFunc();
    this.setState({random: this.state.random + 1});
  }

        // <Reader pageUrl={this.props.pageUrl}/>
  render() {
    return (
      <div id={this.props.id} className="SmallPane">
        <button onClick={this.resetIframe}>Turn to {this.props.id} page</button>
        <iframe src={this.props.pageUrl}
          width="100%" height="100%">
        </iframe>
      </div>
    );

  }
}
