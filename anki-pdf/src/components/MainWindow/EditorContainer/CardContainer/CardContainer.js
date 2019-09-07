import React from 'react';
import {CardSide} from './CardSide/CardSide';
import './CardContainer.css';

export class CardContainer extends React.Component {
  render() {
    return (
      <div className="CardContainer">
        <CardSide submit={this.props.submitFrontSide}/>
        <CardSide submit={this.props.submitBackSide}/>
      </div>
    );
  }
}
