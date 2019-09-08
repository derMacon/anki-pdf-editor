import React from 'react';
import {InputField} from './InputField/InputField';
import {ApiConnector} from '../../../util/ApiConnector';
import './EditorContainer.css';

export class EditorContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      front: undefined,
      back: undefined
    };

    this.submitFrontSide = this.submitFrontSide.bind(this);
    this.submitBackSide = this.submitBackSide.bind(this);
    this.submitTags = this.submitTags.bind(this);
    this.submitCard = this.submitCard.bind(this);
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

  submitCard() {
    if (this.state.front !== undefined
      && this.state.back !== undefined) {
        ApiConnector.submitCard(this.state);
    }
    // todo error handling
  }

  render() {
    const formId = 'editor-form';
    return (
      <div className="EditorContainer">

        <form id={formId} action='http://localhost:8080/pushCard' method='post'>

          <div className="CardContainer">
            <input name="front" type="text"></input>
            <input name="back" type="text"></input>
          </div>
          <input name="tags" type="text"></input>

          <div className="btnMenu">
            <button>todo insert page html</button>
            <button form={formId} type="submit">submit</button>
          </div>

        </form>
      </div>
    );
  }
}
