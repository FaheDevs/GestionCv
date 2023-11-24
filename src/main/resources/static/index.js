import {Home} from "./home.js";
import {login} from "./Signin.js";
import {Dashboard} from "./dashboard.js";
import {retrievePersonDetails} from "./utils/apiCalls.js";

const NotFound = { template: '<p>Page not found</p>' }

const About = { template: '<p>About page content</p>' }

const routes = {
    '/': Home,
    '/login': login,
    '/dashboard': Dashboard
}
var index = new Vue({
    el: '#main',
    data: {
        currentRoute: window.location.pathname,
        currentPath: window.location.hash,
        currentComponent: null,
        isLoggedIn:false,
        personEmail:'',
        personDetails:null
    },
    // watch: {
    //     currentRoute: 'handleRouteChange'
    // },
    methods: {
        // handleRouteChange() {
        //     this.currentComponent = routes[this.currentRoute] || NotFound;
        // },
        handleLoggingSuccess(isLoggedIn){
            this.isLoggedIn = isLoggedIn;

            console.log('isLoggedIn:', isLoggedIn);
            // retrievePersonDetails(email).then(personDetails => {
            //     this.personDetails = personDetails;
            // }).catch(error => {
            //     console.error('Error retrieving person details:', error.message);
            // });

        },
        async handlePersonEmail(email) {
            this.personEmail = email;

            // retrievePersonDetails(email).then(personDetails => {
            //     this.personDetails = personDetails;
            // }).catch(error => {
            //     console.error('Error retrieving person details:', error.message);
            // });
            this.personDetails=await retrievePersonDetails(email);
        }
    },
    computed: {
        currentView() {
            return routes[this.currentPath.slice(1) || '/'] || NotFound
        }
    },
    mounted() {
        window.addEventListener('hashchange', () => {
            this.currentPath = window.location.hash
        })

        console.log("********",this.currentPath)
    }
});

// window.addEventListener('hashchange', () => {
//     index.currentRoute = window.location.pathname;
// });