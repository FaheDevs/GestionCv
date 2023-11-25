export const PersonDetails =Vue.component('person-details', {
    props: ['person'],
    template: `
      <div class="border border-white p-3 m-3 position-relative">
      <div v-if="isReadOnly" class="mb-3">
      <div class="d-grid d-md-flex justify-content-md-end">
           <button @click="enableEditMode" class="btn btn-outline-secondary"><i class="bi bi-pencil"></i></button>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-model="gg" :readonly="isReadOnly" class="form-control" placeholder="First name" aria-label="First name">
      </div>
      <div class="col">
        <input v-bind:value="gg" :readonly="isReadOnly" class="form-control" placeholder="Last name" aria-label="Last name">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-bind:value="gg" :readonly="isReadOnly" class="form-control" placeholder="Email" aria-label="Email">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-bind:value="gg" :readonly="isReadOnly" class="form-control" placeholder="DD-MM-YYYY" aria-label="Date of Birth">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-bind:value="gg" :readonly="isReadOnly" class="form-control" placeholder="Website" aria-label="Website">
      </div>
    </div>
    <div v-if="isReadOnly" class="mb-3">
    </div>
    <div v-else class="mb-3">
      <button @click="saveChanges" class="btn btn-success">Save</button>
      <button @click="disableEditMode" class="btn btn-secondary">Cancel</button>
    </div>
  </div>
 
     `,
    data() {
        return {
            isReadOnly: true
        };
    },
    methods: {
        enableEditMode() {
            this.isReadOnly = false;
        },
        disableEditMode() {
            this.isReadOnly = true;
        },
        saveChanges() {
            // Save changes logic here
            // After saving, set isReadOnly back to true
            this.isReadOnly = true;
        }
    }
});