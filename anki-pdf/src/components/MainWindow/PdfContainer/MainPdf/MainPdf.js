import React from 'react';
import {Reader} from '../PdfReader/Reader';
import './MainPdf.css';

export class MainPdf extends React.Component {
  render() {
    return (
      <div className="MainPdf">
        <Reader pageUrl={this.props.ApiConnector.currPage_url}/>
      </div>
    );
  }
}
