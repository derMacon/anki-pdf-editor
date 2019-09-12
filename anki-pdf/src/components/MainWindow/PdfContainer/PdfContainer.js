import React from 'react';
import {SecPdf} from './SecPdf/SecPdf';
import {MainPdf} from './MainPdf/MainPdf';

import {ApiConnector} from '../../../util/ApiConnector';

import './PdfContainer.css';



export class PdfContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {random: 0};
    this.refreshIframe = this.refreshIframe.bind(this);
  }

  refreshIframe() {
    this.setState({random: this.state.random + 1});
    console.log('refreshed')
  }

  render() {
    console.log(ApiConnector)
    return (
      <div className="PdfContainer">
        <MainPdf ApiConnector={ApiConnector}/>
        <SecPdf ApiConnector={ApiConnector} refreshIframe={this.refreshIframe}/>
      </div>
    );
  }
}
