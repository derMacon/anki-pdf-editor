import React, { PureComponent } from "react"
import throttle from "lodash.throttle"
import './Reader.css';
import { Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

export class Reader extends PureComponent {
  constructor(props) {
    super(props)
    this.state = {
      width: null,
      height: null,
      pageIdx: this.props.pageIdx
    }
  }

  componentDidMount () {
    this.setDivSize()
    window.addEventListener("resize", throttle(this.setDivSize, 500))
  }

  componentWillUnmount () {
    window.removeEventListener("resize", throttle(this.setDivSize, 500))
  }


  componentWillReceiveProps({document}) {
    this.setState({pageIdx: document.currPage});
  }

  setDivSize = () => {
    this.setState({width: this.pdfWrapper.getBoundingClientRect().width});
    this.setState({height: this.pdfWrapper.getBoundingClientRect().height});
  }

  render() {
    const doc = this.props.document;
    return (
      <div id="row" style={{height: "100%", width: "100%", display: "flex", overflow: "hidden"}}>
        <div id="pdfWrapper" style={{width: "100%"}} ref={(ref) => this.pdfWrapper = ref}>
          <PdfComponent
            wrapperDivWidth={this.state.width}
            wrapperDivHeight={this.state.height}
            file={doc.file}
            pageIdx={this.state.pageIdx}
          />
        </div>
      </div>
    )
  }
}


class PdfComponent extends PureComponent {

  // constructor(props) {
  //   super(props);
  //   this.state = {currPageIdx: this.props.document.currPage - 1}
  // }

  onDocumentLoadSuccess = ({ numPages }) => {
    this.setState({ numPages });
    // this.props.document.pageCnt = numPages;
    // this.props.document.updatePageCnt(numPages);
  };

  componentDidMount() {
    // this.props.document.updatePageCnt(this.state.numPages);
  }


  componentWillReceiveProps({document}) {
    console.log('updated')
  }

  render() {
    // const doc = this.props.document;
    // const pdf = doc.file;
    return (
      <div>
        <Document
          file={this.props.file}
          onLoadSuccess={this.onDocumentLoadSuccess}
        >
          <Page
            pageIndex={this.props.pageIdx}
            width={this.props.wrapperDivWidth}
            height={this.props.wrapperDivHeight}
          />
        </Document>
      </div>
    )
  }
}
