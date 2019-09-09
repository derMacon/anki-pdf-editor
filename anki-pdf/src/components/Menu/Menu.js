import React from 'react';
import './Menu.css';

const GIT_REPO_URL = 'https://github.com/derMacon/anki-pdf-editor.git';

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
    console.log('open proj');
  }

  searchTerm(term) {
    // todo
  }

  displayHelp() {
    // todo
  }

  render() {
    const doc = this.props.document;
    return (
      <div className="Menu">
        <div className="dropdown">
          <button className="dropbtn">Open</button>
          <div className="dropdown-content">
            <a href="#" onClick={this.openNewPdf}>new File</a>
            <a href="#" onClick={this.openNewProject}>new Project</a>
            <a href={GIT_REPO_URL}>Github-Project</a>
          </div>
        </div>
        <button onclick={this.searchTerm}>Search Term</button>
        <button onclick={this.displayHelp}>Help</button>
        <div className="infoItem">
          Project: {this.props.document.projectName}
        </div>
        <div className="infoItem">
          Filename: {this.extractFileName(doc.file)}
        </div>
        <div className="infoItem">
          Page: {doc.currPage} / {doc.pageCnt}
        </div>
      </div>
    );
  }
}
