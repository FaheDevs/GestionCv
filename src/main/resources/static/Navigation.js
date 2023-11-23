Vue.component('navbar', {
    template: `
    <header class="p-3 text-bg-dark">
    <div class="container-fluid">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
        <span class="fs-3 fw-light text-white">Simple header</span>
      </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#" class="nav-link px-2 text-secondary fs-5">Home</a></li>
          <li><a href="#" class="nav-link px-2 text-white fs-5">Features</a></li>
          <li><a href="#" class="nav-link px-2 text-white fs-5">Pricing</a></li>
          <li><a href="#" class="nav-link px-2 text-white fs-5">FAQs</a></li>
          <li><a href="#" class="nav-link px-2 text-white fs-5">About</a></li>
        </ul>

        <div class="text-end">
          <a type="button" class="btn btn-outline-light me-2 fs-4" href="/test">Login</a>
          <a type="button" class="btn btn-warning fs-4" href="/test">Sign-up</a>
        </div>
      </div>
    </div>
  </header>
  `
});