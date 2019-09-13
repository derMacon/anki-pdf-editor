import React from 'react';
// import {PdfReader} from '../PdfReader/PdfReader';
import {Reader} from '../../PdfReader/Reader';
import './SmallPane.css';


export class SmallPane extends React.Component {

  constructor(props) {
    super(props);
  }

  turnPage() {
    console.log('hierhoasdjfasdf');
    () => {this.props.pageTurnFunc()};
    () => {this.props.refreshIframe()};
  }

        // <iframe src={this.props.pageUrl() + properties}
        //   width="100%" height="100%">
        // </iframe>

  render() {
    const properties = '?#zoom=0&scrollbar=0&toolbar=0';
    return (
      <div id={this.props.id} className="SmallPane">
        <button onClick={() => {this.props.pageTurnFunc(); this.props.refreshIframe()}}>Turn to {this.props.id} page</button>
        <div>
          <Reader/>
        </div>
      </div>
    );

  }
}
