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

  insertAtCaret(text) {
    text = text || '';
    if (document.selection) {
      // IE
      this.focus();
      var sel = document.selection.createRange();
      sel.text = text;
    } else if (this.selectionStart || this.selectionStart === 0) {
      // Others
      var startPos = this.selectionStart;
      var endPos = this.selectionEnd;
      this.value = this.value.substring(0, startPos) +
        text +
        this.value.substring(endPos, this.value.length);
      this.selectionStart = startPos + text.length;
      this.selectionEnd = startPos + text.length;
    } else {
      this.value += text;
    }
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>

        <div className="EditorContainer">
          <div className="frontSide">
            <textarea id="front" ref={this.frontRef} name="front" type="text" placeholder="front Side"></textarea>
          </div>

          <div className="backSide">
            <textarea ref={this.backRef} name="front" type="text" placeholder="back Side"></textarea>
          </div>

          <div className="footerMenu">
            <textarea ref={this.tagsRef} name="front" type="text" placeholder="tags"></textarea>
            <button>todo insert page html</button>
            <input type="submit" value="Submit"/>
          </div>
        </div>

      </form>
    );
  }
}
