import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import ValetRequestPickup from '../views/ValetRequestPickup.vue';
import CheckInForm from '../views/CheckInForm.vue'
import Slips from '../views/Slip.vue';
import LotAvailability from '../views/LotAvailability.vue';
import RequestPickup from '../views/RequestPickup.vue';
import TotalCost from '../views/TotalCost.vue';
import ArriveTime from '../views/ArriveTime.vue';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import RegisterValetView from '../views/RegisterValetView.vue';
import ParkedCarsView from '../views/ParkedCarsView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [

  {
    path: '/parked-cars',
    name: 'ParkedCars',
    component: ParkedCarsView,
    meta: {
      requiresAuth: true
    }
  },
 
  {
    path: '/valet/request-pickup',
    name: 'valet-request-pickup',
    component: ValetRequestPickup,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/check-in',
    name: 'check-in',
    component: CheckInForm,
    meta: {
      requiresAuth: true
    }
  },
  
  {
    path: '/slips',
    name: 'slips',
    component: Slips,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/lot-availability',
    name: 'lot-availability',
    component: LotAvailability,
    meta: {
      requiresAuth: true 
    }
  },
  {
    path:'/request-pickup',
    name: 'request-pickup',
    component: RequestPickup,
    meta: {
      requiresAuth: true
    } 
  },
  {
    path: '/total-cost',
    name: 'total-cost',
    component: TotalCost,
    meta: {
      requiresAuth: false 
    }
  },

  {
    path: '/arrive-time',
    name: 'arrive-time',
    component: ArriveTime,
    meta: {
      requiresAuth: true 
    }
  },

  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },

  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
   },
  {
    path: "/registervaletview",
    name: "registervaletview",
    component: RegisterValetView,
    meta: {
      requiresAuth: false
    },
  },

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

// make a forbiden view to say dude you are not allowed to see this 
// also redirect someone to their correct role if then are on a path to one they shouldnt be going to

export default router;
