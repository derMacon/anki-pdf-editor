import React from 'react';
import { Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;


export default class PdfDocument extends React.PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      numPages: null,
      pageNumber: 1,
      scale: 1.0
    };
  }

  // Custom pagination component
  onPageChange(pageNumber) {
    this.setState({ pageNumber: pageNumber + 1 });
  }

  onDocumentLoad({ numPages }) {
    this.setState({ numPages });
  }

  onPageLoad(page) {
    const parentDiv = document.querySelector('#pdfDocument')
    let pageScale = parentDiv.clientWidth / page.originalWidth
    if (this.state.scale !== pageScale) {
      this.setState({ scale: pageScale });
    }
  }

  render() {
    return (
      <div>
        <div id="pdfDocument">
          <Document
            file="/example.pdf"
            onLoadSuccess={this.onDocumentLoad}
          >
            <Page
              pageNumber={this.state.pageNumber}
              onLoadSuccess={this.onPageLoad}
              scale={this.state.scale}
            />
          </Document>
        </div>
      </div>
    );
  }
}
