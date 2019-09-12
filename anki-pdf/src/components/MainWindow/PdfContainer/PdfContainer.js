import React from 'react';
import {SecPdf} from './SecPdf/SecPdf';
import {MainPdf} from './MainPdf/MainPdf';

import {ApiConnector} from '../../../util/ApiConnector';

import './PdfContainer.css';



export class PdfContainer extends React.Component {
  render() {
    console.log(ApiConnector)
    return (
      <div className="PdfContainer">
        <MainPdf ApiConnector={ApiConnector}/>
        <SecPdf ApiConnector={ApiConnector}/>
      </div>
    );
  }
}
