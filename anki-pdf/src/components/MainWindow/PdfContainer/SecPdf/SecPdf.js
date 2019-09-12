import React from 'react';
import {SmallPane} from './SmallPane/SmallPane';
import {ApiConnector} from '../../../../util/ApiConnector';
import './SecPdf.css';

export class SecPdf extends React.Component {

  render() {
    const api = this.props.ApiConnector;

    return (
      <div className="SecPdf">
        <SmallPane id="previous"
          pageUrl={api.getPrevPage_url}
          pageTurnFunc={api.turnPrevPage}
          refreshIframe={this.props.refreshIframe}
        />
        <SmallPane id="next"
          pageUrl={api.getNextPage_url}
          pageTurnFunc={api.turnNextPage}
          refreshIframe={this.props.refreshIframe}
        />
      </div>
    );

  };

}
