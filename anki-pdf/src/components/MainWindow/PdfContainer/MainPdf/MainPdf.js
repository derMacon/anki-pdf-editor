import React from 'react';
import {MockPdfReader} from '../PdfReader/MockPdfReader';
import {Reader} from '../PdfReader/Reader';
import './MainPdf.css';

export class MainPdf extends React.Component {
  render() {


        // <MockPdfReader
        //   file={doc.file}
        //   currPage={doc.currPage}
        //   pageCnt={doc.pageCnt}
        // />

    const doc = this.props.document;

    console.log('api; ' + this.props.ApiConnector.getCurrPage_url());

    return (
      <div className="MainPdf">
        <Reader pageUrl={this.props.ApiConnector.getCurrPage_url()}/>
      </div>
    );
  }
}
