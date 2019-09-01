import React from 'react';
import './Business.css';

class Business extends React.Component {
  render() {
    return (
      <div className="Business">
        <div className="image-container">
          <img src={this.props.propBusiness.imageSrc} alt=''/>
        </div>
        <h2>{this.props.propBusiness.name}</h2>
        <div className="Business-information">
          <div className="Business-address">
            <p>{this.props.propBusiness.address}</p>
            <p>{this.props.propBusiness.city}</p>
            <p>{this.props.propBusiness.state}</p>
          </div>
          <div className="Business-reviews">
            <h3>{this.props.propBusiness.category}</h3>
            <h3 className="rating">{this.props.propBusiness.rating} stars</h3>
            <p>{this.props.propBusiness.reviewCount} reviews</p>
          </div>
        </div>
    </div>
    );
  }
}

export default Business;
