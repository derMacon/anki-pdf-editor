import React from 'react';
import './BusinessList.css';
import Business from '../Business/Business';

class BusinessList extends React.Component {
  render() {
    console.log(this.props.propBusiness);
    return (
      <div className="BusinessList">
        {this.props.propBusiness.map((business) => {
          return <Business propBusiness={business}/>;
        })
      }
      </div>
    );
  }
}

export default BusinessList;
