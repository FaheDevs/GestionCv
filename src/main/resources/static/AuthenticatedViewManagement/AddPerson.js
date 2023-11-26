import {registerPerson} from "../utils/apiCalls.js";

export const AddPerson =Vue.component('add-person', {
    props: ['person'],
    template: `
    <div class="border border-white p-3 m-3 position-relative">
    <div v-if="showSpinner" class="spinner-border text-success" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    <br>
    <form @submit.prevent="saveChanges">
        <div class="row mb-3">
            <div class="col">
                <label for="firstName" class="form-label">First Name:</label>
                <input v-model="updatedPersonData.firstName"  class="form-control" id="firstName" placeholder="First name" aria-label="First name">
            </div>
            <div class="col">
                <label for="lastName" class="form-label">Last Name:</label>
                <input v-model="updatedPersonData.lastName"  class="form-control" id="lastName" placeholder="Last name" aria-label="Last name">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <label for="email" class="form-label">Email:</label>
                <input type="email" v-model="updatedPersonData.email"  class="form-control" id="email" placeholder="Email" aria-label="Email">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <label for="password" class="form-label">Password:</label>
                <input type="password" v-model="updatedPersonData.password"  class="form-control" id="password" placeholder="password" aria-label="password">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <label for="birthDay" class="form-label">Date of Birth:</label>
                <input v-model="updatedPersonData.birthDay"  class="form-control" id="birthDay" placeholder="YYYY-M-D" aria-label="Date of Birth">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col">
                <label for="webSite" class="form-label">Website:</label>
                <input v-model="updatedPersonData.webSite" class="form-control" id="webSite" placeholder="Website" aria-label="Website">
            </div>
        </div>

            <button class="btn btn-success" type="submit">ADD</button>
    </form>
    <br>
    <div v-if="showSuccessAlert" class="alert alert-success" role="alert">
        PERSON ADDED 
        </div>
</div>



 
     `,
    data() {
        return {
            updatedPersonData : {
                firstName:'',
                lastName: '',
                email: '',
                password: '',
                birthDay: 0,
                webSite: '',
                role: 'USER'
            },
            showSpinner: false,
            showSuccessAlert: false,
        };
    },
    methods: {
        async saveChanges() {
            try {
                // Set showSpinner to true to display the spinner
                this.showSpinner = true;

                const registeredPerson = await registerPerson(this.updatedPersonData);

                // Set showSuccessAlert to true to display the success alert
                this.showSuccessAlert = true;

                // Automatically hide the success alert after 2 seconds
                setTimeout(() => {
                    this.showSuccessAlert = false;
                    // Redirect to the dashboard after hiding the alert
                    window.location.href = '#/dashboard';
                }, 2000);
            } catch (error) {
                // Handle error
                console.error('Error registering person:', error.message);
            } finally {
                // Set showSpinner to false after the API call is complete
                this.showSpinner = false;
            }

        }
    }
});


