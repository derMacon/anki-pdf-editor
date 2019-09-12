import React from 'react';
import './Reader.css';

export class Reader extends React.Component {
  render() {
    const properties = '?#zoom=85&scrollbar=0&toolbar=0';
    return (
        <iframe src={this.props.pageUrl + properties}
          width="100%" height="100%">
        </iframe>
    );
  }
}
