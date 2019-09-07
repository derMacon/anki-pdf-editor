import React from 'react';
import {CardTags} from './CardTags/CardTags';
import {CardContainer} from './CardContainer/CardContainer';
import {ApiConnector} from '../../../util/ApiConnector';
import './EditorContainer.css';

export class EditorContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.state.front;
    this.state.back;

    this.submitFrontSide = this.submitFrontSide.bind(this);
    this.submitBackSide = this.submitBackSide.bind(this);
    this.submitTags = this.submitTags.bind(this);
    this.submitWholeCard = this.submitWholeCard.bind(this);
  }

  submitFrontSide(input) {
    this.setState({front: input})
  }

  submitBackSide(input) {
    this.setState({back: input})
  }

  submitTags(input) {
    this.setState({tags: input})
  }

  submitWholeCard() {

  }

  render() {
    return (
      <div className="EditorContainer">
        <CardContainer
          submitFrontSide={this.submitFrontSide}
          submitBackSide={this.submitBackSide}
        />
        <CardTags submitTags={this.submitTags}/>
        <button onClick={this.submitWholeCard}>submit</button>
      </div>
    );
  }
}
