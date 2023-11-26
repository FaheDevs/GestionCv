import {Card} from "./Card.js";
import {getAll} from "../utils/apiCalls.js";

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
        <div class="main-cards" ref="mainCards">
         <ul>
            <li style="list-style-type:none" v-for="item in filteredValues" :key="item.id">
                <card @show-cv="onShowCv" :value="item"></card>
                <br>
                <br>
            </li>
        </ul>
        </div>
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
        };
    },
    mounted() {
        this.fetchData().catch(error => {
            console.error(error);
        });
        this.$refs.mainCards.scrollTop = this.$refs.mainCards.scrollHeight;

    },
    methods: {
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
    },
    async created() {
        var list = await getAll()
        this.values = list;
    }
});