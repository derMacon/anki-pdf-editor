import React from 'react';
import {Reader} from '../PdfReader/Reader';
import './MainPdf.css';

export class MainPdf extends React.Component {
  render() {
    const properties = '?#zoom=0&scrollbar=0&toolbar=0';
    console.log('main: ' + this.props.ApiConnector.getCurrPage_url());
    return (
      <div className="MainPdf">
        <iframe src={this.props.ApiConnector.getCurrPage_url() + properties}
          width="100%" height="100%">
        </iframe>
      </div>
    );
  }
}
