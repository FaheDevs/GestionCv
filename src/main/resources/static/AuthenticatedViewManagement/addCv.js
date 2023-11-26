import { createCV } from "../utils/apiCalls.js";

export const AddCv = Vue.component('add-cv', {
    props: ['addcvp'],
    template: `
  <div>
    <button class="btn btn-secondary" type="button" :disabled="loading" @click="makeAPICall">
      <span v-if="loading" class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
      <span v-else>CREATE CV</span>
    </button>
    <span v-if="apiStatus === 'success'" class="badge bg-success">Success</span>
  </div>
  `,
    data() {
        return {
            loading: false,
            apiStatus: null,
            cvID: null,
        };
    },
    methods: {
        makeAPICall() {
            // Set loading state
            this.loading = true;

            // Use person.email from props
            createCV(this.addcvp.email)
                .then((cvId) => {
                    console.log('CV created successfully. CV ID:', cvId);
                    this.cvID = cvId;
                    this.apiStatus = 'success';
                    this.$emit('is-cv-added', true);
                })
                .catch((error) => {
                    console.error('Error creating CV:', error.message);
                })
                .finally(() => {
                    // Reset loading state after API call
                    this.loading = false;
                });
        }
    }
});
