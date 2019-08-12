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
		
		Notification.requestPermission()
		.then((permission) => {
			console.log(permission)
			if(permission === 'granted'){
				console.log('Notification permission granted');

			} else {
				console.log('Unable to get permission to notify');
			}
		});

		messaging.onMessage(function(payload){
			console.log('onMessage from main.js', payload);
			
			var title = payload.data.title
			var options = {
				body : payload.data.contents
			}
			var notification = new Notification(title, options);
		});

		let targetURL = "https://70.12.246.109:3000/send";

    axios({
    	url : targetURL,
      method : 'get',
      params : {
        token : window.sessionStorage.getItem('firebaseToken')
      }
    })
    .then((res) => {
      console.log("send message success")
      console.log(res);
    }).catch((error) => {
      console.log("send message error")
      console.log(error);
    })
	}
}).$mount('#app')