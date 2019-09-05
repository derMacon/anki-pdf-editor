import React from 'react';
import {SecPdf} from './SecPdf/SecPdf';
import {MainPdf} from './MainPdf/MainPdf';
import './PdfContainer.css';

export class PdfContainer extends React.Component {
  render() {
    return (
      <div className="PdfContainer">
        <MainPdf document={this.props.document}
        setPageCnt={this.props.setPageCnt}/>
        <SecPdf document={this.props.document}/>
      </div>
    );
  }
}
