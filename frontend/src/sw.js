self.addEventListener('push', function(event) {
  //푸시 리스너
	var payload = event.data.json();
	const title = payload.title;
	const options = {
		body: payload.body,
		icon: 'images/fav.ico',
		badge: 'images/badge.png',
		vibrate: [200, 100, 200, 100, 200, 100, 400],
		data : payload.params
	};
	event.waitUntil( self.registration.showNotification(title, options) );
});

self.addEventListener('notificationclick', function(event) {
  //푸시 노티피케이션 에서 클릭 리스너
	var data = event.notification.data;
	event.notification.close();
	event.waitUntil( clients.openWindow( data.url ) );
});