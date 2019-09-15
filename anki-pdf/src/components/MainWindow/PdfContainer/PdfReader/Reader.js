import React, { PureComponent } from "react"
import throttle from "lodash.throttle"
import './Reader.css';
import { Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

export class Reader extends PureComponent {
  constructor(props) {
    super(props)
    this.state = {width: null, height: null}
  }

  componentDidMount () {
    this.setDivSize()
    window.addEventListener("resize", throttle(this.setDivSize, 500))
  }

  componentWillUnmount () {
    window.removeEventListener("resize", throttle(this.setDivSize, 500))
  }

  setDivSize = () => {
    this.setState({width: this.pdfWrapper.getBoundingClientRect().width});
    this.setState({height: this.pdfWrapper.getBoundingClientRect().height});
  }

  render() {
    return (
      <div id="row" style={{height: "100%", width: "100%", display: "flex", overflow: "hidden"}}>
        <div id="pdfWrapper" style={{width: "100%"}} ref={(ref) => this.pdfWrapper = ref}>
          <PdfComponent
            wrapperDivWidth={this.state.width}
            wrapperDivHeight={this.state.height}
            document={this.props.document}
          />
        </div>
      </div>
    )
  }
}

class PdfComponent extends PureComponent {
  render() {
    const doc = this.props.document;
    const pdf = doc.file;
    // const pdf = './example.pdf';
    return (
      <div>
        <Document file={pdf}>
          <Page
            pageIndex={doc.currPage}
            width={this.props.wrapperDivWidth}
            height={this.props.wrapperDivHeight}
          />
        </Document>
      </div>
    )
  }
}
