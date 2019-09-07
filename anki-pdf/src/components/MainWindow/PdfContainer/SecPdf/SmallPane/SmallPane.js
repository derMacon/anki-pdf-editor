import React from 'react';
// import {PdfReader} from '../PdfReader/PdfReader';
import {MockPdfReader} from '../../PdfReader/MockPdfReader';
import './SmallPane.css';

export class SmallPane extends React.Component {

  render() {
    const doc = this.props.document;
    const currPage = doc.currPage + this.props.offset;

    if (currPage < 1 || currPage > doc.pageCnt) {
      return <p>Out of bound (todo red background)</p>
    }

    return (
      <div className="SmallPane">
        <MockPdfReader
          file={this.props.document.file}
          currPage={currPage}
          pageCnt={this.props.pageCnt}
        />
      </div>
    );

  }
}
