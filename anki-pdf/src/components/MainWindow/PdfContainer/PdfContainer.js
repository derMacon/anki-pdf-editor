import React from 'react';
import {Reader} from './PdfReader/Reader';
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
    const doc = this.props.document;
    return (
      <div className="PdfContainer">
        <div className="MainPdf">
          <Reader document={doc} pageIdx={0}/>
        </div>
        <div>
          <button onClick={doc.turnPrevPage}>previous page</button>
          <button onClick={doc.turnNextPage}>next page</button>
        </div>
        <div className="SecPdf">
          <Reader document={doc} pageIdx={0}/>
          <Reader document={doc} pageIdx={0}/>
        </div>
      </div>
    );
  }
}
