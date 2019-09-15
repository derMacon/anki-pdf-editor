import React from 'react';
import {ApiConnector} from '../../../util/ApiConnector';
import './EditorContainer.css';

export class EditorContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      front: undefined,
      back: undefined
    };

    this.frontRef = React.createRef();
    this.backRef = React.createRef();
    this.tagsRef = React.createRef();
    this.handleSubmit = this.handleSubmit.bind(this);

    this.insertAtCaret = this.insertAtCaret.bind(this);
  }

  handleSubmit(event) {
    const projectName = this.props.document.projectName;
    const frontForm = this.frontRef.current.value;
    const backForm = this.backRef.current.value;
    const tagsForm = this.tagsRef.current.value;

    if ((frontForm.length === 0) || (backForm.length === 0)) {
      const errorMessage = 'Only the tag field is optional.';
      alert(errorMessage);
      console.log(errorMessage);
    } else {

      const cardObj = {
        deckName: projectName,
        frontSide: frontForm,
        backSide: backForm,
        tags: tagsForm
      }

      ApiConnector.submitCard(cardObj);
      event.preventDefault();

      this.frontRef.current.value = '';
      this.backRef.current.value = '';
    }
  }

  insertAtCaret(text) {
    // todo
    console.log(text)
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
          </div>

          <div className="btnMenu">
            <button type="button" onClick={this.insertAtCaret('hi')}>Insert page</button>
            <input type="submit" value="Submit"/>
          </div>
        </div>

      </form>
    );
  }
}
