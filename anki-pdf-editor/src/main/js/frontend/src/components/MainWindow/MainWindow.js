import React from 'react';
import {PdfContainer} from './PdfContainer/PdfContainer';
import {EditorContainer} from './EditorContainer/EditorContainer';
import './MainWindow.css';

export class MainWindow extends React.Component {
        // <PdfContainer document={this.props.document}/>
        // <div class="isResizable">The quick brown fox jumps over the lazy dog.</div>

  render() {
    return (
      <div className="MainWindow">
        <div className="isResizable">
          <PdfContainer document={this.props.document}/>
        </div>
        <EditorContainer document={this.props.document}/>
      </div>
    );
  }
}
