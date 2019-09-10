import React from 'react';

export class Reader extends React.Component {
  render() {
    return (
      <iframe src="http://localhost:8080/example.pdf"
        width="100%" height="100%"
        frameBorder="0"></iframe>
    );
  }
}
