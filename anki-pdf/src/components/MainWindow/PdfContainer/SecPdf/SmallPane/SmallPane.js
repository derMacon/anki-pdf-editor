import React from 'react';
// import {PdfReader} from '../PdfReader/PdfReader';
import {Reader} from '../../PdfReader/Reader';
import './SmallPane.css';

import arrowIcon from './arrowIcon.png';

export class SmallPane extends React.Component {

  generateContent() {
    const doc = this.props.document;
    const currPage = doc.currPage + this.props.offset;

    if (currPage < 1 || currPage > doc.pageCnt) {
      return <p>Out of bound (todo red background)</p>
    }

    return <Reader/>;
  }

  testCall() {
    console.log('boah2');
  }
        // <div id="imgIcon">
        //   {this.generateContent()}
        // </div>

  render() {
    const pageTurnFunc = this.props.offset < 0 ?
      this.props.document.turnPrevPage :
      this.props.document.turnNextPage;

    return (
      <div id={this.props.id} className="SmallPane">
        <button onClick={pageTurnFunc}>Turn to _____ page</button>
        {this.generateContent()}
      </div>
    );

  }
}
