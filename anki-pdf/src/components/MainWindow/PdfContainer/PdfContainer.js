import React from 'react';
import {SecPdf} from './SecPdf/SecPdf';
import {MainPdf} from './MainPdf/MainPdf';
import './PdfContainer.css';

export class PdfContainer extends React.Component {
  render() {
    return (
      <div className="PdfContainer">
        <MainPdf/>
        <SecPdf/>
      </div>
    );
  }
}
