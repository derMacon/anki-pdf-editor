import React from 'react';
import {PdfReader} from '../PdfReader/PdfReader';
import './SmallPane.css';

export class SmallPane extends React.Component {
  render() {
    return (
      <div className="SmallPane">
        <PdfReader/>
      </div>
    );
  }
}
