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
          pageUrl={api.prevPage_url}
          pageTurnFunc={api.turnNextPage}
        />
        <SmallPane id="next"
          pageUrl={api.nextPage_url}
          pageTurnFunc={api.turnNextPage}
        />
      </div>);
  };

}
