var app = new Vue({
    el: '#main',
    data: {
        players: [
            {id: "1", name: "Lionel Messi", description: "Argentina's superstar"},
            {id: "2", name: "Christiano Ronaldo", description: "Portugal top-ranked player"}
        ],
        values: [
            {id: "1", fullName: "Lionel", text: "Argentina's superstar"},
            {id: "2", fullName: "test", text: "Argentina's superstar"},
            {id: "3", fullName: "ey", text: "Argentina's superstar"},
            {id: "4", fullName: "wesg", text: "Argentina's superstar"},
        ],
        items: [],
        currentPage: 1,
        perPage: 2,
        totalItems: 0,
        searchText: ''
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
            console.log(this.totalItems,"hereeee")
            this.items = await res.json();
        },
        changePage(page) {
            if (page >= 1 && page <= Math.ceil(this.totalItems / this.perPage)) {
                this.currentPage = page;
                this.fetchData().catch(error => {
                    console.error(error);
                });
            }
        }
    },
    computed: {
        filteredValues() {
            const searchText = this.searchText.toLowerCase();
            return this.values.filter(value =>
                value.fullName.toLowerCase().includes(searchText) ||
                value.text.toLowerCase().includes(searchText)
            );
        }
    },
});