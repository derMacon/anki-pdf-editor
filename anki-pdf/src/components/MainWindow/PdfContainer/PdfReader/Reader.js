import React from 'react';

export class Reader extends React.Component {
  render() {
    return (
      <iframe src={this.props.pageUrl}
        width="100%" height="100%"
        frameBorder="0"></iframe>
    );
  }
}
