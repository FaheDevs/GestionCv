import {Home} from "./HomeViewManagement/home.js";
import {login} from "./HomeViewManagement/Signin.js";
import {Dashboard} from "./AuthenticatedViewManagement/dashboard.js";
import {retrievePersonDetails} from "./utils/apiCalls.js";
import {AddPerson} from "./AuthenticatedViewManagement/AddPerson.js";
import {CvView} from "./HomeViewManagement/CvView.js";
import {Register} from "./HomeViewManagement/register.js";

const NotFound = { template: '<p>Page not found</p>' }

const About = { template: '<p>About page content</p>' }

const routes = {
    '/': Home,
    '/login': login,
    '/dashboard': Dashboard,
    '/addPerson': AddPerson,
    '/cvView': CvView,
    '/register': Register

}
var index = new Vue({
    el: '#main',
    data: {
        currentRoute: window.location.pathname,
        currentPath: window.location.hash,
        currentComponent: null,
        isLoggedIn:false,
        personEmail:'',
        personDetails:null,
        reload:false,
        cvViewData:null
    },
    methods: {
        handleLoggingSuccess(isLoggedIn){
            this.isLoggedIn = isLoggedIn;
            console.log('isLoggedIn:', isLoggedIn);
        },
        async handlePersonEmail(email) {
            this.personEmail = email;
            this.personDetails=await retrievePersonDetails(email);
        },
         checkLoginStatus() {
            if (this.isLoggedIn) {
                this.currentPath = '/dashboard';
            } else {
                this.currentPath = '/';
            }
        },
        handleRouteChange() {
            // If the user manually changes the URL to /dashboard but is not logged in, redirect to /
            if (this.currentPath === '#/dashboard' && !this.isLoggedIn) {
                window.location.hash = '/';
                const errorMessage = 'You are not logged in. Redirected to the home page.'
                alert(errorMessage.toUpperCase());
            }
        },
        async handleReloadPersonDetails(reload) {
            this.reload = reload;
            this.personDetails = await retrievePersonDetails(this.personEmail);
            console.log(this.personDetails)
        },
        handleLogout(loggedOut){
            this.isLoggedIn=loggedOut;
        },
        handleLoadingCvViewData(data){
        this.cvViewData=data;
            window.location.href='#/cvView'
        }
    },
    computed: {
        currentView() {
            return routes[this.currentPath.slice(1) || '/'] || NotFound
        }
    },
    mounted() {
        window.addEventListener('hashchange', () => {
            this.currentPath = window.location.hash;
            this.handleRouteChange();
        });

        // Check login status on page load
        this.checkLoginStatus();
        this.handleRouteChange();
    }
});
