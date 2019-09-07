import React from 'react';
// import {PdfReader} from '../PdfReader/PdfReader';
import {MockPdfReader} from '../PdfReader/MockPdfReader';
import './MainPdf.css';

export class MainPdf extends React.Component {
  render() {
    return (
      <div className="MainPdf">
        <MockPdfReader
          file={this.props.document.file}
          currPage={this.props.document.currPage}
          pageCnt={this.props.document.pageCnt}
        />
      </div>
    );
  }
}
