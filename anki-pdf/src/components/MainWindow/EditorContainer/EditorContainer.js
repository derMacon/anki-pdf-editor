import React from 'react';
import {CardTags} from './CardTags/CardTags';
import {CardContainer} from './CardContainer/CardContainer';
import './EditorContainer.css';

export class EditorContainer extends React.Component {
  render() {
    return (
      <div className="EditorContainer">
        <CardContainer/>
        <CardTags/>
      </div>
    );
  }
}
