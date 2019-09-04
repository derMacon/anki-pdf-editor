import React from 'react';
import {CardSide} from './CardSide/CardSide';
import './CardContainer.css';

export class CardContainer extends React.Component {
  render() {
    return (
      <div className="CardContainer">
        <CardSide/>
        <CardSide/>
      </div>
    );
  }
}
