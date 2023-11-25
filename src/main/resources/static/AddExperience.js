import {addExperience} from "./utils/apiCalls.js";

export const AddExperience =Vue.component('add-experience', {
    props: ['addexperiencep'],
    template: `
  <div class="experience-form" >
  <form @submit.prevent="addCard">
    <h2>Add Card</h2>
    <div class="mb-3">
      <label for="title" class="form-label">Title:</label>
      <input v-model="newCard.title" type="text" class="form-control" id="title" placeholder="Enter title">
    </div>
    <div class="mb-3">
      <label for="year" class="form-label">Year:</label>
      <input v-model="newCard.year" type="text" class="form-control" id="year" placeholder="Enter year">
    </div>
    <div class="mb-3">
      <label for="nature" class="form-label">Nature:</label>
      <input v-model="newCard.nature" type="text" class="form-control" id="nature" placeholder="Enter nature">
    </div>
    <div class="form-floating mb-3">
      <textarea v-model="newCard.description" class="form-control" placeholder="Enter description" id="floatingTextarea"></textarea>
      <label for="floatingTextarea">Description</label>
    </div>
    <div class="mb-3">
      <label for="website" class="form-label">Website:</label>
      <input v-model="newCard.website" type="text" class="form-control" id="website" placeholder="Enter website">
    </div>
    <button type="submit" class="btn btn-outline-success">SAVE</button>
    <button @click="cancel" type="button" class="btn btn-outline-secondary">Cancel</button>
      </form>
    
  </div>
  `,data() {
        return {
            newCard: {
                title: '',
                nature: '',
                year:0,
                description: '',
                website:''
            },
        };
    },
    methods:{
        async addCard() {
                const experienceID = await addExperience(this.addexperiencep.curriculumVitae.id, this.newCard)
                console.log("ADDED EXPERIENCE ID :",experienceID)
                this.newCard.title = '';
                this.newCard.nature = '';
                this.newCard.year = 0;
                this.newCard.description = '';
                this.newCard.website = '';
                this.$emit('on-save', true);
        },
        cancel(){
            this.$emit('on-cancel', true);
        }
    }

});