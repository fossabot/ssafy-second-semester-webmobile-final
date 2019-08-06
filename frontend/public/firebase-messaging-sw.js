// 오프라인 상태나 브라우져가 닫힌 상태에서는 서비스워커가 대신 메세지를 받게 됨.

// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

// Retrieve an instance of Firebase Messaging so that it can handle background
var firebaseConfig = { // 테스트계정, 나중에 바꿀거임.
		apiKey: "AIzaSyBUl8_AGThzw07WWeWQHnHlH509Rdev2j8",
	    authDomain: "icebear-3649e.firebaseapp.com",
	    databaseURL: "https://icebear-3649e.firebaseio.com",
	    projectId: "icebear-3649e",
	    storageBucket: "icebear-3649e.appspot.com",
	    messagingSenderId: "587823756968",
	    appId: "1:587823756968:web:f77edfc46b26c329"
	}

	firebase.initializeApp(firebaseConfig);

// messages.
const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function(payload){
	const title = payload.data.title; //푸시 메세지 제목
	const options = {
		body : payload.data.contents // 푸시 메세지 내용
	};

	console.log(payload)
	return self.registration.showNotification(title, options);
})