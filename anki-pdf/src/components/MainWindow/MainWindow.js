import React from 'react';
import {PdfContainer} from './PdfContainer/PdfContainer';
import {EditorContainer} from './EditorContainer/EditorContainer';
import './MainWindow.css';

export class MainWindow extends React.Component {
  render() {
    return (
      <div className="MainWindow">
        <PdfContainer currPage={this.props.currPage}/>
        <EditorContainer currPage={this.props.currPage}/>
      </div>
    );
  }
}
