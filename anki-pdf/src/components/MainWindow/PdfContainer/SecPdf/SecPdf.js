import React from 'react';
import {SmallPane} from './SmallPane/SmallPane';
import {ApiConnector} from '../../../../util/ApiConnector';
import './SecPdf.css';

export class SecPdf extends React.Component {

  render() {
    const api = this.props.ApiConnector;

      //   <SmallPane id="previous"
      //     pageUrl={api.getPrevPage_url}
      //     pageTurnFunc={api.turnPrevPage}
      //     refreshIframe={this.props.refreshIframe}
      //   />
      //   <SmallPane id="next"
      //     pageUrl={api.getNextPage_url}
      //     pageTurnFunc={api.turnNextPage}
      //     refreshIframe={this.props.refreshIframe}
      //   />

    const properties = '?#zoom=0&scrollbar=0&toolbar=0';
    return (
      <div className="SecPdf">
        <iframe src={'http://localhost:8080/example.pdf' + properties}
          width="100%" height="100%">
        </iframe>
      </div>
    );

  };

}
