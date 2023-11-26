import {AddExperience} from "./AddExperience.js";
import {deleteExperience, updateExperienceDetails} from "../utils/apiCalls.js";

export const ExperienceList = Vue.component('experience-list', {
    props: ['experiencep'],
    template: `
<div class="card-list-container">
    <div class="d-grid d-md-flex justify-content-md-end">
      <button @click="showForm" class="btn btn-outline-secondary"><i class="bi bi-plus-lg"></i></button>
    </div>
    <br>
    <div>
    <div v-if="experiencep.curriculumVitae.experiences.length === 0">
      <h4>No experiences. You can add one!</h4>
    </div>
    <div v-else class="card-list" ref="cardList">
      <div v-for="(card, index) in experiencep.curriculumVitae.experiences" :key="index" class="experience-card border border-light-subtle">
        <p class="fw-medium">{{ card.title }}</p>
        <p class="fw-lighter fst-italic">{{ card.year }}</p>
        <br>
        <p class="fw-normal">{{ card.description }}</p>
        <br>
        <p class="fw-normal"><a href="#" class="text-reset">{{ card.description }}</a>.</p>
        <button type="button" id="modifyButton" class="btn btn-outline-secondary" @click="modifyCard(index)"><i class="bi bi-pencil-square"></i></button>
        <button type="button" id="deleteButton" class="btn btn-danger" @click="deleteCard(index)"><i class="bi bi-trash"></i></button>
      </div>
    </div>
  </div>
   <!-- Add modification form -->
    <div v-if="showAddForm">
     <add-experience :addexperiencep="experiencep" @on-cancel="cancelForm" @on-save="handleExperienceSave"></add-experience>
    </div>
    <div v-if="showModifyForm" class="experience-form">
      <br>
      <br>
      <h2>Modify Card</h2>
      <form @submit.prevent="saveModifiedCard">
      <div class="mb-3">
        <label for="modifiedTitle" class="form-label">Title:</label>
        <input v-model="modifiedCard.title" type="text" class="form-control" id="modifiedTitle" placeholder="Enter title">
      </div>
      <div class="mb-3">
        <label for="modifiedYear" class="form-label">Year:</label>
        <input v-model="modifiedCard.year" type="text" class="form-control" id="modifiedYear" placeholder="Enter year">
      </div>
      <div class="mb-3">
        <label for="modifiedYear" class="form-label">Year:</label>
        <input v-model="modifiedCard.nature" type="text" class="form-control" id="modifiedYear" placeholder="Enter year">
      </div>
      <div class="form-floating mb-3">
        <textarea v-model="modifiedCard.description" class="form-control" placeholder="Enter description" id="modifiedFloatingTextarea"></textarea>
        <label for="modifiedFloatingTextarea">Description</label>
      </div>
      <div class="mb-3">
        <label for="modifiedWebsite" class="form-label">Website:</label>
        <input v-model="modifiedCard.website" type="text" class="form-control" id="modifiedWebsite" placeholder="Enter website">
      </div>
      <button type="submit" class="btn btn-outline-success">Save</button>
      <button @click="cancelForm" type="button" class="btn btn-outline-secondary">Cancel</button>
      </form>
      <br>
      <br>
    </div>
  </div>
  <br>
  <br>
  `,
    data() {
        return {
            showAddForm: false,
            showModifyForm: false,
            modifiedCard: {
                title: '',
                year:0,
                nature:'',
                description: '',
                website: '',
            },
            selectedCardIndex: null,
        };
    },
    methods: {
        showForm() {
            this.showAddForm = true;
            this.showModifyForm = false;
            this.selectedCardIndex = null;
        }
        ,
        modifyCard(index) {
            this.selectedCardIndex = index;
            this.modifiedCard.title = this.experiencep.curriculumVitae.experiences[index].title;
            this.modifiedCard.year = this.experiencep.curriculumVitae.experiences[index].year;
            this.modifiedCard.nature = this.experiencep.curriculumVitae.experiences[index].nature;
            this.modifiedCard.description = this.experiencep.curriculumVitae.experiences[index].description;
            this.modifiedCard.website = this.experiencep.curriculumVitae.experiences[index].website;
            this.showAddForm = false;
            this.showModifyForm = true;
        },
        async deleteCard(index) {
            if (confirm('Are you sure you want to delete this experience?')) {
                this.selectedCardIndex = index;
                const id = this.experiencep.curriculumVitae.experiences[this.selectedCardIndex].id
                await deleteExperience(id);
                this.$emit('experience-delete', true);
            }
        },
        async saveModifiedCard() {
            if (this.selectedCardIndex !== null) {
                const id = this.experiencep.curriculumVitae.experiences[this.selectedCardIndex].id
                const updatedExperience = await updateExperienceDetails(id, this.modifiedCard)
                console.log("UPDATED EXPERIENCE : ", updatedExperience)
                this.$emit('experience-update', true);
                this.modifiedCard.title = '';
                this.modifiedCard.year = 0;
                this.modifiedCard.nature = '';
                this.modifiedCard.description = '';
                this.modifiedCard.website = '';
                this.showModifyForm = false;
            }
        },
        cancelForm() {
            // this.modifiedCard.title = '';
            // this.modifiedCard.description = '';
            this.showAddForm = false;
            this.showModifyForm = false;
        },
        handleExperienceSave(saved){
            this.cancelForm();
            this.$emit('experience-add', true);
        }
    },
    mounted() {
        this.$refs.cardList.scrollTop = this.$refs.cardList.scrollHeight;
    },
    components: {
        'add-experience':AddExperience
    },
});
