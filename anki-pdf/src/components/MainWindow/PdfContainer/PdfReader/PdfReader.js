import React, { Component } from "react";
import { Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

export class PdfReader extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: this.props.document.file,
      numPages: null,
      pageNumber: 1,
      scale: 1.0
    };
  }

  onDocumentLoadSuccess = ({ numPages }) => {
    this.setState({ numPages });
    this.props.setPageCnt(numPages);
  };

  goToPrevPage = () =>
    this.setState(state => ({ pageNumber: state.pageNumber - 1 }));
  goToNextPage = () =>
    this.setState(state => ({ pageNumber: state.pageNumber + 1 }));


  // loads before render function
  componentWillReceiveProps(props) {
      this.setState({
        pageNumber: props.document.currPage
      });
  }

  onPageLoad(page) {

    this.pageObj = page;

     const parentDiv = document.querySelector('#pdfDocument');
     let pageScale = parentDiv.clientWidth / page.originalWidth;
     console.log('hieriereir' + pageScale);
     // // console.log(isNaN(this.state.scale));
     // console.log('test')
     // console.log(typeof this.state.scale === undefined)
     // if (this.state.scale !== pageScale) {
     //   this.setState({ scale: pageScale });
     // }
     // console.log('1test' + this.getScale(page)
   }

   getScale(page) {
     const parentDiv = document.querySelector('#pdfDocument');
     let pageScale = parentDiv.clientWidth / page.originalWidth;
     return pageScale;
   }

  render() {
    const { file, pageNumber, numPages } = this.state;

    return (
      <div id="pdfDocument">
        <nav>
          <button onClick={this.goToPrevPage}>Prev</button>
          <button onClick={this.goToNextPage}>Next</button>
        </nav>

        <div >
          <Document
            file={file}
            onLoadSuccess={this.onDocumentLoadSuccess}
          >
            <Page id="page"
            pageNumber={pageNumber}
            onLoadSuccess={this.onPageLoad}
            scale={1.0}
            />
          </Document>
        </div>

        <p>
          Page {pageNumber} of {numPages}
        </p>
      </div>
    );
  }
}
