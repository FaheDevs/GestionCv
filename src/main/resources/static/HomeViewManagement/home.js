import {Card} from "./Card.js";
import {getAllPaginated} from "../utils/apiCalls.js";

export const Home = Vue.component('home', {
    props: ['value'],
    template: `
    <div id="contents-main">
        <div id="contents" class="d-flex flex-column justify-content-center align-self-center mt-5 mx-5 border rounded p-5" style="border-color: grey;">
            <div class="d-flex flex-column justify-content-center align-self-center mb-3">
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="filter by name" aria-label="Search" v-model="searchText" style="width: 600px;">
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
                <ul class="pagination">
                    <li class="page-item" :class="{ disabled: currentPage === 0 }">
                        <a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">Previous</a>
                    </li>
                    <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page - 1 === currentPage }">
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
            pageSize: 3 // Adjust the page size as needed
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
                await this.fetchData(this.currentPage, this.pageSize);
            }
        },
        onShowCv(data){
            this.$emit('load-cv-view-data', data);
        }
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
