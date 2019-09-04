import React from 'react';
import {MainPdf} from './MainPdf/MainPdf';
import {SecPdf} from './SecPdf/SecPdf';

export class PdfContainer extends React.Component {
  render() {
    return (
      <div>
        <MainPdf/>
      </div>
    );
  }
}
