import {Card} from "./Card.js";
import {
    getAllPaginated,
    getPaginationExperience,
    getPaginationFirstName,
    getPaginationLastName
} from "../utils/apiCalls.js";

export const Home = Vue.component('home', {
    props: ['value'],
    template: `
    <div id="contents-main">
        <div id="contents" class="d-flex flex-column justify-content-center align-self-center mt-5 mx-5 border rounded p-5" style="border-color: grey;">
            <div class="d-flex flex-column justify-content-center align-self-center mb-3">
                     <div class="mb-4">
                        <div class="input-group">
                            <input class="form-control mr-sm-2" type="text" placeholder="firstName" v-model="inputField1" :disabled="inputField2.length > 0 || inputField3.length > 0" @input="validateInput($event.target.value, 'inputField1')" >
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" @click="searchField(1)">Search</button>
                            </div>
                        </div>
                    </div>
                    <div class="mb-4">
                        <div class="input-group">
                            <input class="form-control mr-sm-2" type="text" placeholder="last Name" v-model="inputField2" :disabled="inputField1.length > 0 || inputField3.length > 0">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" @click="searchField(2)">Search</button>
                            </div>
                        </div>
                    </div>
                        <div class="mb-4">
                            <div class="input-group">
                                <input class="form-control mr-sm-2" type="text" placeholder="experience" v-model="inputField3" :disabled="inputField1.length > 0 || inputField2.length > 0">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="button" @click="searchField(3)">Search</button>
                                </div>
                            </div>
                        </div>
                        <div style="margin-bottom: 20px;"> <!-- Adjust the margin as needed -->
                            <button class="btn btn-primary" @click="clearFields">Clear</button>
                         </div>
                            <form class="form-inline my-2 my-lg-0">
                                <input class="form-control mr-sm-2" type="search" placeholder="find in the list" aria-label="Search" v-model="searchText" style="width: 600px;">
                                <i class="bi bi-search"></i>
                            </form>
            </div>
            
            <div class="main-cards" ref="mainCards">
                <ul>
                    <li style="list-style-type:none" v-for="item in filteredValues" :key="item.id">
                        <card @show-cv="onShowCv" :value="item"></card>
                        <br><br>
                    </li>
                </ul>
            </div>
            <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
             <div class="mb-4">Total Pages: {{ totalPages }}</div> 
             <br>
             <br>
                <ul class="pagination">
                    <li class="page-item" :class="{ disabled: currentPage === 0 }">
                        <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">Previous</a>
                    </li>
                    <li class="page-item" v-for="page in paginateRange()" :key="page" :class="{ active: page - 1 === currentPage }">
                        <a class="page-link" href="#" @click.prevent="changePage(page - 1)">{{ page }}</a>
                    </li>
                    <li class="page-item" :class="{ disabled: currentPage === totalPages - 1 }">
                        <a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    `,
    components: {
        'card': Card
    },
    data() {
        return {
            values: [],
            searchText: '',
            currentPage: 0,
            totalPages: 0,
            pageSize: 10,
            inputField1: '', // Add three new properties for the input fields
            inputField2: '',
            inputField3: '',
            activeSearchField: null,
        };
    },
    mounted() {
        this.fetchData(this.currentPage, this.pageSize).catch(error => {
            console.error(error);
        });
        this.$refs.mainCards.scrollTop = this.$refs.mainCards.scrollHeight;
    },
    methods: {
        async fetchData(page, size) {
            try {
                const data = await getAllPaginated(page, size);
                this.values = data.content; // assuming the response has a 'content' field
                this.totalPages = data.totalPages; // assuming the response has a 'totalPages' field
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        },
        async changePage(page) {
            if (page >= 0 && page < this.totalPages) {
                this.currentPage = page;
                let data;
                switch(this.activeSearchField) {
                    case 1:
                        data = await getPaginationFirstName(this.currentPage, this.pageSize, this.inputField1);
                        break;
                    case 2:
                        data = await getPaginationLastName(this.currentPage, this.pageSize, this.inputField2);
                        break;
                    case 3:
                        data = await getPaginationExperience(this.currentPage, this.pageSize, this.inputField3);
                        break;
                    default:
                        data = await this.fetchData(this.currentPage, this.pageSize);
                        break;
                }
                if (data) {
                    this.values = data.content;
                    this.totalPages = data.totalPages;
                }
            }
        },
        onShowCv(data){
            this.$emit('load-cv-view-data', data);
        },
        paginateRange() {
            const totalDisplayed = 6;
            let start = this.currentPage - Math.floor(totalDisplayed / 2);
            start = Math.max(start, 0);
            let end = start + totalDisplayed;
            end = Math.min(end, this.totalPages);

            if (this.totalPages - end < totalDisplayed / 2) {
                start = this.totalPages - totalDisplayed;
            }
            start = Math.max(start, 0);

            return Array.from({ length: (end - start) }, (_, i) => start + i + 1);
        },
        logInputValues() {
            this.fetchData(this.inputField1, this.inputField2, this.inputField3,this.currentPage, this.pageSize).catch(error => {
                console.error(error);
            });
            // Add any additional logic you want to perform with these values
        },
        async searchField(fieldNumber) {
            this.activeSearchField = fieldNumber;
            try {
                let data;
                switch(fieldNumber) {
                    case 1:
                        data = await getPaginationFirstName(this.currentPage, this.pageSize, this.inputField1);
                        break;
                    case 2:
                        data = await getPaginationLastName(this.currentPage, this.pageSize, this.inputField2);
                        break;
                    case 3:
                        data = await getPaginationExperience(this.currentPage, this.pageSize, this.inputField3);
                        break;
                }
                this.values = data.content;
                this.totalPages = data.totalPages;
            } catch (error) {
                console.error('Error in search:', error);
            }
        },

        async clearFields() {
            this.inputField1 = '';
            this.inputField2 = '';
            this.inputField3 = '';
            this.activeSearchField = null; // Reset the active search field
            await this.fetchData(this.currentPage, this.pageSize);
        },

        validateInput(value, field) {
            // Basic validation: remove common SQL injection characters
            const sanitizedValue = value.replace(/['";\-]+/g, '');

            // Update the appropriate data property
            this[field] = sanitizedValue;
        },

    },
    computed: {
        filteredValues() {
            const searchText = this.searchText.toLowerCase();
            return this.values.filter(value =>
                value.firstName.toLowerCase().includes(searchText) ||
                value.lastName.toLowerCase().includes(searchText)
            );
        }
    }
});
