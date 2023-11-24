
Vue.component('navbar', {
    props:["isLoggedIn", "person"],
    template: `
    <header class="p-3 text-bg-dark">
    <div class="container-fluid">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
        <span class="fs-3 fw-light text-white">Simple header</span>
      </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="#/" class="nav-link px-2 text-white fs-5">Home</a></li>
        </ul>

        <div class="text-end">
         <!-- Conditional rendering based on user login status -->
            <template v-if="isLoggedIn">
              <!-- Content to display when the user is logged in -->
              <span class="text-white fs-4 me-2">Welcome, {{ person.firstName }}</span>
            </template>
            <template v-else>
              <!-- Content to display when the user is not logged in -->
              <a type="button" class="btn btn-outline-light me-2 fs-4" href="#/login">Login</a>
              <a type="button" class="btn btn-warning fs-4" href="/signup">Sign-up</a>
            </template>
        </div>
      </div>
    </div>
  </header>
  `,
    data() {
        return {
            // Assuming you have some way to check if the user is logged in
            isLoggedIn: this.$root.isLoggedIn,
            // Assuming you have the user's name when they are logged in
        };
    },
});


