import React from 'react';
import {PdfContainer} from './PdfContainer/PdfContainer';
import {EditorContainer} from './EditorContainer/EditorContainer';
import './MainWindow.css';

export class MainWindow extends React.Component {
  
  render() {
    return (
      <div className="MainWindow">
        <PdfContainer document={this.props.document}/>
        <EditorContainer document={this.props.document}/>
      </div>
    );
  }
}
