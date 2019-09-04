import React from 'react';
import {PdfReader} from '../PdfReader/PdfReader';

export class SecPdf extends React.Component {
  render() {
    return (
      <div>
        <PdfReader/>
        <PdfReader/>
      </div>);
  }
}
