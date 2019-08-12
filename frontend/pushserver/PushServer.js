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


/* mapping controller methods list */
app.get('/send', function(req, res){
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "X-Requested-With");

  	const requestTopic = req.query.topic; // 포스트(post), 포트폴리오(portfolio), 댓글(postComment, portfolioComment) 구분
  	const requestBoardId = req.query.boardId; // 댓글 알림시, 포트폴리오(또는 포스트)의 id

  	if(requestTopic === 'post'){
  		sendPushAboutPost();

  	} else if(requestTopic === 'portfolio'){
  		sendPushAboutPortfolio();

  	} else if(requestTopic === 'postComment'){
  		sendPushAboutPostComment(requestBoardId);

  	} else if(requestTopic === 'portfolioComment'){
  		sendPushAboutPortfolioComment(requestBoardId);
  	}

  	res.send();

  	// sendPushAboutPortfolio(req, res);
})

app.get('/subscribe', function(req, res){
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "X-Requested-With");

	var registrationToken = req.query.token;
	var accountAuth = req.query.accountAuth; // 1,2,3,4 구분

	if(accountAuth === '1') {
		subscribeToAllusers(registrationToken);
		subscribeToAdmins(registrationToken);

	} else if(accountAuth === '2' || accountAuth === '3' || accountAuth === '4'){
		subscribeToAllusers(registrationToken);
	}

	res.send();
})

app.get('/unsubscribe', function(req, res){
	res.header("Access-Control-Allow-Origin", "*");
  	res.header("Access-Control-Allow-Headers", "X-Requested-With");

	var userToken = req.query.token;
	var accountAuth = req.query.accountAuth; // 1,2,3,4 구분

	if(accountAuth === '1') {
		unsubscribeFromAllusers(userToken);
		unsubscribeFromAdmins(userToken);

	} else if(accountAuth === '2' || accountAuth === '3' || accountAuth === '4'){
		unsubscribeFromAllusers(userToken);
	}

	res.send();
})

function sendPushAboutPost(){
	const topic = "Allusers"

	var message = {
		data : {
			title: 'New Post :)',
	  		contents: '포스트에 새로운 글이 등록되었습니다.'		
		},
		topic : topic
	};
	
	admin.messaging().send(message)
	  .then((response) => {
	    // Response is a message ID string.
	    console.log('Successfully sent message:');
	    console.log(response);

	    // res.send("success");
	  })
	  .catch((error) => {
	    console.log('Error sending message:', error);
	    // res.send("error")
	  });
}

function sendPushAboutPortfolio(){
	const topic = "Allusers"

	var message = {
		data : {
			title: 'New Portfolio :)',
	  		contents: '포트폴리오에 새로운 글이 등록되었습니다.'		
		},
		topic : topic
	};
	
	admin.messaging().send(message)
	  .then((response) => {
	    // Response is a message ID string.
	    console.log('send portfolio')
	    console.log('Successfully sent message, topic : ', topic);
	    console.log(response);
	  })
	  .catch((error) => {
	    console.log('Error sending message:', error);
	  });
}

function sendPushAboutPostComment(postId){
	const topic = "Admins"

	var message = {
		data : {
			title: 'New PostComment :)',
	  		contents: postId + '번 포스트에 새로운 댓글이 등록되었습니다.'		
		},
		topic : topic
	};
	
	admin.messaging().send(message)
	  .then((response) => {
	    // Response is a message ID string.
	    console.log('Successfully sent message:');
	    console.log(response);
	    // res.send("success");
	  })
	  .catch((error) => {
	    console.log('Error sending message:', error);
	    // res.send("error")
	  });
}

function sendPushAboutPortfolioComment(portfolioId){
	const topic = "Admins"

	var message = {
		data : {
			title: 'New PortfolioComment :)',
	  		contents: portfolioId + '번 포트폴리오에 새로운 댓글이 등록되었습니다.'		
		},
		topic : topic
	};
	
	admin.messaging().send(message)
	  .then((response) => {
	    // Response is a message ID string.
	    console.log('Successfully sent message:');
	    console.log(response);
	    // res.send("success");
	  })
	  .catch((error) => {
	    console.log('Error sending message:', error);
	    // res.send("error")
	  });
}

function subscribeToAllusers(fcmToken){
	const topic = "Allusers";

	console.log(fcmToken);

	admin.messaging().subscribeToTopic(fcmToken, topic)
	.then((response) => {
		console.log('Successfully subscribe to Allusers');
		if(response.errors.length == 0){
			console.log(response);
		} else {
			console.log(response.errors[0].error);	
		}
		
		// res.send("success");
	})
	.catch((error) => {
		console.log('Error subscribe to Allusers');
		console.log(error);
		// res.send('error');
	})
}

function subscribeToAdmins(fcmToken){
	const topic = "Admins";

	admin.messaging().subscribeToTopic(fcmToken, topic)
	.then((response) => {
		console.log('Successfully subscribe to Admins');
		console.log(response);
		// res.send("success");
	})
	.catch((error) => {
		console.log('Error subscribe to Admins', error);
		// res.send('error');
	})
}

function unsubscribeFromAllusers(userToken){
	const topic = "Allusers";

	admin.messaging().unsubscribeFromTopic(userToken, topic)
	.then((response) => {
		console.log('Successfully unsubscribe from Allusers');
		console.log(response.errors[0].error);
		// res.send("success");
	})
	.catch((error) => {
		console.log('Error unsubscribe from Allusers', error);
		// res.send('error');
	})
}

function unsubscribeFromAdmins(userToken){
	const topic = "Admins";

	console.log('unsubscribe : ' , userToken)

	admin.messaging().unsubscribeFromTopic(userToken, topic)
	.then((response) => {
		console.log('Successfully unsubscribe from Admins');
		console.log(response);

		// res.send("success");
	})
	.catch((error) => {
		console.log('Error unsubscribe from Admins', error);
		// res.send('error');
	})
}