import React from 'react';
import {SmallPane} from './SmallPane/SmallPane';
import {ApiConnector} from '../../../../util/ApiConnector';
import './SecPdf.css';




export class SecPdf extends React.Component {

  render() {
    return (
      <div className="SecPdf">
        <SmallPane id="prevPage" document={this.props.document} offset={-1}/>
        <SmallPane id="nextPage" document={this.props.document} offset={1}/>
      </div>);
  }
}
