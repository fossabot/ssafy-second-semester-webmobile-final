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
  	// 온라인 상태일땐, 웹브라우져로 푸시 메세지를 받을 수 있음
		var firebaseConfig = {
			apiKey: "AIzaSyBUl8_AGThzw07WWeWQHnHlH509Rdev2j8",
		    authDomain: "icebear-3649e.firebaseapp.com",
		    databaseURL: "https://icebear-3649e.firebaseio.com",
		    projectId: "icebear-3649e",
		    storageBucket: "icebear-3649e.appspot.com",
		    messagingSenderId: "587823756968",
		    appId: "1:587823756968:web:f77edfc46b26c329"
		}

		firebase.initializeApp(firebaseConfig);

		const messaging = firebase.messaging();

		messaging.usePublicVapidKey("BLvhScc_Oi6mtvrxUhV4xUDWJwvCNZzuzoLMUPt0_wo3o_pnPc1t1ANeKJq38CljwU-ghUJanm21WRB1RAP9L1Q");

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
			console.log('onMessage', payload);
		})

		// Get Instance ID token. Initially this makes a network call, once retrieved
		// subsequent calls to getToken will return from cache.

		messaging.getToken().then((token) => {
			//save token to your server
			//if users allow permission, save token to their databases
			console.log(token);
		})

		messaging.onMessage(function(payload){
			console.log('onMessage, Message received', payload);
			console.log(payload.data.status)
		});
	}
}).$mount('#app')