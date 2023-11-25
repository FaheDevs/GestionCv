export const ExperienceList = Vue.component('experience-list', {
    template: `
<div class="card-list-container">
    <div class="d-grid d-md-flex justify-content-md-end">
      <button @click="showForm" class="btn btn-outline-secondary"><i class="bi bi-plus-lg"></i></button>
    </div>
    <br>
    <div class="card-list" ref="cardList">
      <div v-for="(card, index) in cards" :key="index" class="experience-card border border-light-subtle">
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
   <!-- Add modification form -->
    <div v-if="showAddForm" class="experience-form">
      <br>
      <br>
      <h2>Add Card</h2>
      <div class="mb-3">
        <label for="title" class="form-label">Title:</label>
        <input v-model="newCard.title" type="text" class="form-control" id="title" placeholder="Enter title">
      </div>
      <div class="mb-3">
        <label for="year" class="form-label">Year:</label>
        <input v-model="newCard.year" type="text" class="form-control" id="year" placeholder="Enter year">
      </div>
      <div class="form-floating mb-3">
        <textarea v-model="newCard.description" class="form-control" placeholder="Enter description" id="floatingTextarea"></textarea>
        <label for="floatingTextarea">Description</label>
      </div>
      <div class="mb-3">
        <label for="website" class="form-label">Website:</label>
        <input v-model="newCard.website" type="text" class="form-control" id="website" placeholder="Enter website">
      </div>
      <button @click="addCard" type="button" class="btn btn-outline-success">SAVE</button>
      <button @click="cancelForm" type="button" class="btn btn-outline-secondary">Cancel</button>
      <br>
      <br>
    </div>
    <div v-if="showModifyForm" class="experience-form">
      <br>
      <br>
      <h2>Modify Card</h2>
      <div class="mb-3">
        <label for="modifiedTitle" class="form-label">Title:</label>
        <input v-model="modifiedCard.title" type="text" class="form-control" id="modifiedTitle" placeholder="Enter title">
      </div>
      <div class="mb-3">
        <label for="modifiedYear" class="form-label">Year:</label>
        <input v-model="modifiedCard.year" type="text" class="form-control" id="modifiedYear" placeholder="Enter year">
      </div>
      <div class="form-floating mb-3">
        <textarea v-model="modifiedCard.description" class="form-control" placeholder="Enter description" id="modifiedFloatingTextarea"></textarea>
        <label for="modifiedFloatingTextarea">Description</label>
      </div>
      <div class="mb-3">
        <label for="modifiedWebsite" class="form-label">Website:</label>
        <input v-model="modifiedCard.website" type="text" class="form-control" id="modifiedWebsite" placeholder="Enter website">
      </div>
      <button @click="saveModifiedCard" type="button" class="btn btn-outline-success">Save</button>
      <button @click="cancelForm" type="button" class="btn btn-outline-secondary">Cancel</button>
      <br>
      <br>
    </div>
  </div>
  <br>
  <br>
  `,
    data() {
        return {
            cards: [
                { title: 'Card 1',year:'20220', description: 'Description for Card 1',website:'test.com' },
                { title: 'Card 1',year:'20220', description: 'Description for Card 1',website:'test.com' },
                // Add more initial cards if needed
            ],
            showAddForm: false,
            showModifyForm: false,
            newCard: {
                title: '',
                description: '',
            },
            modifiedCard: {
                title: '',
                description: '',
            },
            selectedCardIndex: null,
        };
    },
    methods: {
        showForm() {
            this.showAddForm = true;
            this.showModifyForm = false;
            this.selectedCardIndex = null;
        },
        addCard() {
            if (this.newCard.title && this.newCard.description) {
                this.cards.push({
                    title: this.newCard.title,
                    description: this.newCard.description,
                });

                this.newCard.title = '';
                this.newCard.description = '';
                this.showAddForm = false;
            }
        },
        modifyCard(index) {
            this.selectedCardIndex = index;
            this.modifiedCard.title = this.cards[index].title;
            this.modifiedCard.description = this.cards[index].description;
            this.showAddForm = false;
            this.showModifyForm = true;
        },
        deleteCard(index) {
            if (confirm('Are you sure you want to delete this card?')) {
                this.cards.splice(index, 1);
            }
        },
        saveModifiedCard() {
            if (this.selectedCardIndex !== null) {
                this.cards[this.selectedCardIndex].title = this.modifiedCard.title;
                this.cards[this.selectedCardIndex].description = this.modifiedCard.description;

                this.modifiedCard.title = '';
                this.modifiedCard.description = '';
                this.showModifyForm = false;
            }
        },
        cancelForm() {
            this.newCard.title = '';
            this.newCard.description = '';
            this.modifiedCard.title = '';
            this.modifiedCard.description = '';
            this.showAddForm = false;
            this.showModifyForm = false;
        },
    },
    mounted() {
        this.$refs.cardList.scrollTop = this.$refs.cardList.scrollHeight;
    }
});
