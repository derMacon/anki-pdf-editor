// import React from 'react';
// import './Reader.css';

// export class Reader extends React.Component {
//   render() {
//     const properties = '?#zoom=0&scrollbar=0&toolbar=0';
//     return (
//         <iframe src={this.props.pageUrl + properties}
//           width="100%" height="100%">
//         </iframe>
//     );
//   }
// }



import React, { PureComponent } from "react"
import throttle from "lodash.throttle"
import './Reader.css';

import { Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

export class Reader extends PureComponent {
  constructor(props) {
    super(props)
    this.state = {width: null}
  }

  componentDidMount () {
    this.setDivSize()
    window.addEventListener("resize", throttle(this.setDivSize, 500))
  }

  componentWillUnmount () {
    window.removeEventListener("resize", throttle(this.setDivSize, 500))
  }

  setDivSize = () => {
    this.setState({width: this.pdfWrapper.getBoundingClientRect().width})
  }

  render() {
    return (
      <div id="row" style={{height: "100%", width: "100%", display: "flex", overflow: "hidden"}}>
        <div id="pdfWrapper" style={{width: "100%"}} ref={(ref) => this.pdfWrapper = ref}>
          <PdfComponent wrapperDivSize={this.state.width} />
        </div>
      </div>
    )
  }
}

class PdfComponent extends PureComponent {
  render() {
    const pdf = './example.pdf';
    return (
      <div>
        <Document
          file={pdf}
        >
          <Page pageIndex={2} width={this.props.wrapperDivSize} />
        </Document>
      </div>
    )
  }
}
