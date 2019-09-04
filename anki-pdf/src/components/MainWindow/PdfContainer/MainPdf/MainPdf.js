import React from 'react';
import {PdfReader} from '../PdfReader/PdfReader';
import './MainPdf.css';

export class MainPdf extends React.Component {
  render() {
    return (
      <div className="MainPdf">
        <PdfReader document={this.props.document}/>
      </div>
    );
  }
}
