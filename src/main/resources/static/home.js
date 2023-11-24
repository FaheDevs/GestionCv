import {Card} from "./Card.js";

export const Home=  Vue.component('home', {
    props: ['value'],
    template: `
  <div id="contents-main">
    <div id="contents" class="d-flex flex-column justify-content-center align-self-center mt-5 mx-5 border rounded p-5" style="border-color: grey;">
        <div class="d-flex flex-column justify-content-center align-self-center mb-3" >
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="filter by name" aria-label="Search" v-model="searchText" style="width: 600px;">
                <i class="bi bi-search"></i>
            </form>
        </div>

        <ul>
            <li style="list-style-type:none" v-for="item in filteredValues" :key="item.id">
                <card :value="item"></card>
                <br>
                <br>
            </li>
        </ul>

        <table class="table">
            <thead>
            <tr>
                <th scope="col">Post ID</th>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Body</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="item in items" :key="item.id">
                    <th scope="row">{{ item.postId }}</th>
                    <td>{{ item.id }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.email }}</td>
                    <td>{{ item.body }}</td>
                </tr>
            </tbody>
        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item" :class="{ disabled: currentPage === 1 }">
                    <a class="page-link" href="#" aria-label="Previous" @click.prevent="changePage(currentPage - 1)">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item" v-for="page in Math.ceil(totalItems / perPage)" :key="page" :class="{ active: currentPage === page }">
                    <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
                </li>
                <li class="page-item" :class="{ disabled: currentPage === Math.ceil(totalItems / perPage) }">
                    <a class="page-link" href="#" aria-label="Next" @click.prevent="changePage(currentPage + 1)">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
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
            players: [
                { id: "1", name: "Lionel Messi", description: "Argentina's superstar" },
                { id: "2", name: "Christiano Ronaldo", description: "Portugal top-ranked player" }
            ],
            values: [
                { id: "1", fullName: "Lionel", text: "Argentina's superstar" },
                { id: "2", fullName: "test", text: "Argentina's superstar" },
                { id: "3", fullName: "ey", text: "Argentina's superstar" },
                { id: "4", fullName: "wesg", text: "Argentina's superstar" },
            ],
            items: [],
            currentPage: 1,
            perPage: 2,
            totalItems: 0,
            searchText: '',
        };
    },
    mounted() {
        this.fetchData().catch(error => {
            console.error(error);
        });
    },
    methods: {
        async fetchData() {
            const res = await fetch(`https://jsonplaceholder.typicode.com/comments?_page=${this.currentPage}&_limit=${this.perPage}`);
            this.totalItems = parseInt(res.headers.get('x-total-count'), 10);
            this.items = await res.json();
        },
        changePage(page) {
            if (page >= 1 && page <= Math.ceil(this.totalItems / this.perPage)) {
                this.currentPage = page;
                this.fetchData().catch(error => {
                    console.error(error);
                });
            }
        },
    },
    computed: {
        filteredValues() {
            const searchText = this.searchText.toLowerCase();
            return this.values.filter(value =>
                value.fullName.toLowerCase().includes(searchText) ||
                value.text.toLowerCase().includes(searchText)
            );
        }
    }
});