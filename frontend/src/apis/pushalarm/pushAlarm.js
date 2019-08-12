/* params list
 *
 * accountAuth = {1,2,3,4}
 * userToken = firestore(파이어베이스 db)에 있던 유저의 기존 토큰값 (data.isPush)
*/

import axios from 'axios';
import firebase from 'firebase'

const messaging = firebase.messaging();

export default {
	pushAlarmSubscribe(accountAuth){
		const targetURL = "https://70.12.246.109:3000/subscribe"
		
		console.log("subscribe");
		axios.get(targetURL, {
			params : {
				token : window.sessionStorage.getItem('firebaseToken'),
				accountAuth : accountAuth
			}
		})
		.then((response)=>{
			console.log('pushAlarmSubscribe success');
		})
		.catch((error)=>{
			console.log('pushAlarmSubscribe error', error);
		});

	},

	pushAlarmUnSubscribe(accountAuth, userToken){
		const targetURL = "https://70.12.246.109:3000/unsubscribe"
		
		console.log('unsub ', userToken);
		axios.get(targetURL, {
			params : {
				token : userToken,
				accountAuth : accountAuth
			}
		})
		.then((response)=>{
			console.log('pushAlarmUnSubscribe success');
		})
		.catch((error)=>{
			console.log('pushAlarmUnSubscribe error', error);
		});
	},

	pushAlarmSend(topic, boardId){
		const targetURL = "https://70.12.246.109:3000/send";

		axios.get(targetURL, {
			params : {
				topic : topic,
				boardId : boardId
			}
		})
		.then((response)=>{
			console.log('pushAlarmSend success');
		})
		.catch((error)=>{
			console.log('pushAlarmSend error', error);
		});
	},

	refreshToken(){
		messaging.getToken()
     .then((fcmToken)=>{
     	console.log(fcmToken);
      window.sessionStorage.setItem('firebaseToken', fcmToken);
      
      console.log("pushAlarmSubscribe getToken success", fcmToken);
    })
    .catch((error)=>{
      console.log("pushAlarmSubscribe getToken error", error);
    })
	}
}