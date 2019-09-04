import React from 'react';

export class Menu extends React.Component {
  render() {
    return <div>{this.props.document.currPage}</div>;
  }
}
