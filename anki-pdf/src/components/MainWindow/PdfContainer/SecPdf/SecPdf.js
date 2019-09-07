import React from 'react';
import {SmallPane} from './SmallPane/SmallPane';
import './SecPdf.css';

export class SecPdf extends React.Component {
  render() {
    return (
      <div className="SecPdf">
        <SmallPane document={this.props.document} offset={-1}/>
        <SmallPane document={this.props.document} offset={1}/>
      </div>);
  }
}
