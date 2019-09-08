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

    this.frontRef = React.createRef();
    this.handleSubmit = this.handleSubmit.bind(this);
    this.backRef = React.createRef();
    this.tagsRef = React.createRef();
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

  handleSubmit(event) {
    const frontForm = this.frontRef.current.value;
    const backForm = this.backRef.current.value;
    const tagsForm = this.tagsRef.current.value;
    console.log('bis hier');

    console.log('log: ' + backForm.length + ', ' + (backForm.length === 0));

    if ((frontForm.length === 0) || (backForm.length === 0)) {
      const errorMessage = 'Only the tag field is optional.';
      alert(errorMessage);
      console.log(errorMessage);
    } else {
      const cardObj = {
        front: frontForm,
        back: backForm,
        tags: tagsForm
      }

      ApiConnector.submitCard(cardObj);
      event.preventDefault();

      this.frontRef.current.value = '';
      this.backRef.current.value = '';
      this.tagsRef.current.value = '';
    }
  }

  render() {
    return (
      <div className="EditorContainer">

        <form onSubmit={this.handleSubmit}>

          <div className="CardContainer">
            <input ref={this.frontRef} name="front" type="text" placeholder="front Side"></input>
            <input ref={this.backRef} name="front" type="text" placeholder="back Side"></input>
          </div>
          <input ref={this.tagsRef} name="front" type="text" placeholder="tags"></input>

          <div className="btnMenu">
            <button>todo insert page html</button>
            <input type="submit" value="Submit"/>
          </div>

        </form>

      </div>
    );
  }
}
