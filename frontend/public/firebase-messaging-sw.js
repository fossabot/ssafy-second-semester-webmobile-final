// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

// Retrieve an instance of Firebase Messaging so that it can handle background
var firebaseConfig = {
	apiKey: "AIzaSyDb1Lugdea1Bol0aNkYETqB4W-OKw8CCNI",
  authDomain: "ssafy-barebears.firebaseapp.com",
  databaseURL: "https://ssafy-barebears.firebaseio.com",
  projectId: "ssafy-barebears",
  storageBucket: "",
  messagingSenderId: "380624918537",
  appId: "1:380624918537:web:6d65b693b9cd1fa4"
}

firebase.initializeApp(firebaseConfig);

// messages.
const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload){
	console.log("message from sw.js", payload);

	const title = payload.data.title; //푸시 메세지 제목
	const options = {
		body : payload.data.contents // 푸시 메세지 내용
	};

	return self.registration.showNotification(title, options);
})
