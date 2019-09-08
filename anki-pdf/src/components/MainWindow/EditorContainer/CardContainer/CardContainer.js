import React from 'react';
import {InputField} from '../InputField/InputField';
import './CardContainer.css';

export class CardContainer extends React.Component {
  render() {
    return (
      <div className="CardContainer">
        <InputField submit={this.props.submitFrontSide}/>
        <InputField submit={this.props.submitBackSide}/>
      </div>
    );
  }
}
