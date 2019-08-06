/*
메세지 푸시를 위한 node.js 서버
http://localhost:8080/sendpush로 메세지 푸시 가능.
서버구동은 터미널에서 지금 경로에서 node PushServer.js 로 run
*/

const express = require('express');
const {google} = require('googleapis');
const admin = require('firebase-admin');
const app = express();
const serviceAccount = require('../icebear-3649e-firebase-adminsdk-ksrm4-c7a437eb8d.json');
admin.initializeApp({
	credential: admin.credential.cert(serviceAccount),
  	databaseURL: "https://icebear-3649e.firebaseio.com"
})

var server = app.listen(3000, function(){
    console.log("Express server has started on port 3000")
})

app.get('/send', function(req, res){
	// This registration token comes from the client FCM SDKs.
	var registrationToken = 'ex1MUpNqD-Q:APA91bH88b0Um46BATs2iFdMCPdQ8rhDztc9ARs_1xN1V_j3LE5VElqPgrCLrIq8hoT4yWKnQijXVUkyDIAkost1dtX3lMeHcyiFwwZpYOHXbYHo1OV_n-wvaj394nuCRcpxc6UstiyS';
	/*var registrationToken = req.body.token;*/

	var message = {
		data : {
			title: '새 글이 등록되었어요!',
	  		contents: '빨리 확인하세욧!!'		
		},
		token: registrationToken
	};

	// Send a message to the device corresponding to the provided
	// registration token.
	admin.messaging().send(message)
	  .then((response) => {
	    // Response is a message ID string.
	    console.log('Successfully sent message:', response);
	    res.send("success");
	  })
	  .catch((error) => {
	    console.log('Error sending message:', error);
	    res.send("error")
	  });
})