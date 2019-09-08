import React from 'react';
import './Menu.css';

export class Menu extends React.Component {
  extractFileName(wholePath) {
    // todo check if correct
    return wholePath.replace(/\\$/,'').split('\\').pop();
  }

  openNewPdf() {
    // todo
  }

  openNewProject() {
    // todo
  }

  render() {
    const doc = this.props.document;
    return (
      <div className="Menu">
        <button onclick={this.openNewPdf}>Open new pdf</button>
        <button onclick={this.openNewProject}>Open new project</button>
        <div className="infoItem">
          Projectname: todo not in props at the moment
        </div>
        <div className="infoItem">
          Filename: {this.extractFileName(doc.file)}
        </div>
        <div className="infoItem">
          {doc.currPage} / {doc.pageCnt}
        </div>
      </div>
    );
  }
}
