const urlApiServer = 'http://localhost:8080/';
const serveSelectedPdf_endpoint = urlApiServer + 'serveSelectedPdf';

const url = serveSelectedPdf_endpoint;
// const url = '../docs/pdf.pdf';

let pdfDoc = null,
  pageNum = 1,
  pageIsRendering = false,
  pageNumIsPending = null;

let scale = 4,
  canvas = document.querySelector('#pdf-render'),
  ctx = canvas.getContext('2d');


// Render the page
const renderPage = num => {
  pageIsRendering = true;

  // Get page
  pdfDoc.getPage(num).then(page => {
    // Set scale
    const viewport = page.getViewport({ scale });
    canvas.height = viewport.height;
    canvas.width = viewport.width;

    const renderCtx = {
      canvasContext: ctx,
      viewport
    };

    page.render(renderCtx).promise.then(() => {
      pageIsRendering = false;

      if (pageNumIsPending !== null) {
        renderPage(pageNumIsPending);
        pageNumIsPending = null;
      }
    });

    // Output current page
    document.querySelector('#page-num').textContent = num;
  });
};

// Check for pages rendering
const queueRenderPage = num => {
  if (pageIsRendering) {
    pageNumIsPending = num;
  } else {
    renderPage(num);
  }
};

// Show Prev Page
const showPrevPage = () => {
  if (pageNum <= 1) {
    return;
  }
  pageNum--;
  queueRenderPage(pageNum);
};

// Show Next Page
const showNextPage = () => {
  if (pageNum >= pdfDoc.numPages) {
    return;
  }
  pageNum++;
  queueRenderPage(pageNum);
};

// scale func for button
const incScalePage = () => {
  scale = scale + 0.5;
  renderPage(pageNum);
  console.log('hier')
}

// scale func for button
const decScalePage = () => {
  scale = scale - 0.5;
  renderPage(pageNum);
  console.log('hier')
}


// Get Document
pdfjsLib
  .getDocument(url)
  .promise.then(pdfDoc_ => {
    pdfDoc = pdfDoc_;

    document.querySelector('#page-count').textContent = pdfDoc.numPages;

    renderPage(pageNum);
  })
  .catch(err => {
    // Display error
    const div = document.createElement('div');
    div.className = 'error';
    div.appendChild(document.createTextNode(err.message));
    document.querySelector('body').insertBefore(div, canvas);
    // Remove top bar
    document.querySelector('.top-bar').style.display = 'none';
  });

// Button Events
document.querySelector('#prev-page').addEventListener('click', showPrevPage);
document.querySelector('#next-page').addEventListener('click', showNextPage);
document.querySelector('#incScale').addEventListener('click', incScalePage);
document.querySelector('#decScale').addEventListener('click', decScalePage);
