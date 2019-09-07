import React from 'react';
import './MockPdfReader.css';

// placeholder for the actual pdf-reader
export class MockPdfReader extends React.Component {

  render() {
    return (
      <div className="MockPdfReader">
        <h3>mock pdf-reader</h3>
        <p>doc: {this.props.file}</p>
        <p>currPage: {this.props.currPage}</p>
        <p>maxPage: {this.props.pageCnt}</p>
      </div>
    );

  }
}
