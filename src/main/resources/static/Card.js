
export const Card =Vue.component('card', {
    props: ['value'],
    template: `
  <div class="card mb-3">
  <div class="card-header fs-4 fw-bold">
    {{value.fullName}}
  </div>
  <div class="card-body">
    <h5 class="card-title fs-4">{{value.text}}</h5>
    <p class="card-text fs-4">With supporting text below as a natural lead-in to additional content.</p>
    <a href="#" class="btn btn-primary fs-4">Go somewhere</a>
  </div>
</div>
  `
});



