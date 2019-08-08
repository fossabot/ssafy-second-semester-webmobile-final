/*
메세지 푸시를 위한 node.js 서버
서버구동은 터미널에서 지금 경로에서 node PushServer.js 로 run
*/

const express = require('express');
const app = express();
const cors = require('cors');
const fs = require('fs');
const https = require('https');
const http = require('http');

const {google} = require('googleapis');
const admin = require('firebase-admin');


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

process.env.NODE_TLS_REJECT_UNAUTHORIZED = '0';

https.createServer(sslOptions, app).listen(3000, function(){
	console.log("HTTPS  server is listening on port " + 3000);
})

// http.createServer(app).listen(3000, function(){
// 	console.log("Http server is listening on port " + 3000);
// })

app.get('/send', function(req, res){
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "X-Requested-With");

	const topic = "Allusers"

	var message = {
		data : {
			title: '저 왔어요 !!!',
	  		contents: '하이요!!'		
		},
		topic : topic
	};
	
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

app.get('/subscribe', function(req, res){
	var registrationToken = req.query.token;
	const topic = "Allusers";

	admin.messaging().subscribeToTopic(registrationToken, topic)
	.then((response) => {
		console.log("Successfully add subscribe", response);
		res.send("success");
	})
	.catch((error)=>{
		console.log("Error add subscribe");
		res.send("error");
	});
})