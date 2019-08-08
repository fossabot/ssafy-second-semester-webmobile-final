/*
메세지 푸시를 위한 node.js 서버
http://localhost:8080/send로 메세지 푸시 가능.
서버구동은 터미널에서 지금 경로에서 node PushServer.js 로 run
*/

const express = require('express');
const cors = require('cors');
const {google} = require('googleapis');
const admin = require('firebase-admin');
const fs = require('fs');
const https = require('https');
const app = express();
const serviceAccount = require('../ssafy-barebears-firebase-adminsdk-toal3-509eeb50b7.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://ssafy-barebears.firebaseio.com"
});

const sslOptions = {
	key : fs.readFileSync('./private.pem'),
	cert : fs.readFileSync('./public.pem')
};

app.use(cors());

https.createServer(sslOptions, app).listen(3000, function(){
	console.log("HTTPS  server is listening on port " + 3000);
})

app.get('/send', function(req, res){
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "X-Requested-With");

	var registrationToken = req.query.token;

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