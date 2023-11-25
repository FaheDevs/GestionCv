import {updatePersonDetails} from "./utils/apiCalls.js";

export const PersonDetails = Vue.component('person-details', {
    props: ['person'],
    template: `
      <div class="border border-white p-3 m-3 position-relative">
      <div v-if="isReadOnly" class="mb-3">
      <div class="d-grid d-md-flex justify-content-md-end">
           <button @click="enableEditMode" class="btn btn-outline-secondary"><i class="bi bi-pencil"></i></button>
      </div>
    </div>
    <form @submit.prevent="saveChanges">
    <div class="row mb-3">
      <div class="col">
        <input v-model="updatedPersonData.firstName" :readonly="isReadOnly" class="form-control" placeholder="First name" aria-label="First name">
      </div>
      <div class="col">
        <input v-model="updatedPersonData.lastName" :readonly="isReadOnly" class="form-control" placeholder="Last name" aria-label="Last name">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-model="updatedPersonData.email" :readonly="isReadOnly" class="form-control" placeholder="Email" aria-label="Email">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-model="updatedPersonData.birthDay" :readonly="isReadOnly" class="form-control" placeholder="DD-MM-YYYY" aria-label="Date of Birth">
      </div>
    </div>
    <div class="row mb-3">
      <div class="col">
        <input v-model="updatedPersonData.webSite" :readonly="isReadOnly" class="form-control" placeholder="Website" aria-label="Website">
      </div>
    </div>
    
    <div v-if="isReadOnly" class="mb-3">
    </div>
    <div v-else class="mb-3">
      <button @click="saveChanges" class="btn btn-success" type="submit">Save</button>
      <button @click="disableEditMode" class="btn btn-secondary">Cancel</button>
    </div>
    </form>
  </div>
 
     `,
    data() {
        return {
            isReadOnly: true,
            updatedPersonData : {
                firstName: this.person.firstName,
                lastName: this.person.lastName,
                email: this.person.email,
                birthDay: this.person.birthDay,
                webSite: this.person.webSite
            }
        };
    },
    methods: {
        enableEditMode() {
            this.isReadOnly = false;
        },
        disableEditMode() {
            this.isReadOnly = true;
        },
        async saveChanges() {
            console.log("UPDATE-DATA", this.updatedPersonData);
            const updatedPerson = await updatePersonDetails(this.updatedPersonData);
            console.log("UPDATED PERSON ", updatedPerson);
            this.$emit('person-update', true);
            this.isReadOnly = true;
        }
    }
});