import Vue from 'vue'
import App from './App.vue'
import router from './router/router.js'
import store from './store/store.js'
import './registerServiceWorker'
import axios from 'axios'
import firebase from 'firebase'
import VueFullPage from 'vue-fullpage.js'

Vue.use(VueFullPage);

Vue.config.productionTip = false;
Vue.prototype.$http = axios;

new Vue({
  router,
  store,
  render: h => h(App),
  created(){
		const messaging = firebase.messaging();
		messaging.usePublicVapidKey("BDhO-XBNdHNDOrb2HRPcpGWZT2BUubsKqwckfoqbWyABdj2osj5_5DHxS1tiyFQ0lUVzKomeuwdxPMm3agLJZJ0");

		messaging.onMessage(function(payload){
			console.log('onMessage from main.js', payload);
			
			var title = payload.data.title
			var options = {
				body : payload.data.contents
			}
			var notification = new Notification(title, options);
		});
	}
}).$mount('#app')