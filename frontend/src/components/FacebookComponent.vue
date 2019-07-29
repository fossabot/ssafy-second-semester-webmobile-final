<template>
    <div id="fb-root">
        <div class="fb-login-button" 
        data-width="" 
        data-size="large"
        data-button-type="login_with" 
        data-auto-logout-link="false" 
        data-use-continue-as="false"
        @onlogin="checkLoginState();">
        </div>
    </div>
</template>

<script>
 
export default {
  name: 'FacebookComponent',
  props : {
    user :{
      id : "",
    }
  },
  mounted(){
    this.loadFbSDN()
    this.initFbLogin()
     
    console.log("test")
    console.log(FB);
    // 창 띄워주는 것 같은 함수
    console.log(FB.AppEvents.logPageView());
    FB.AppEvents.logPageView();    
    console.log(FB.AppEvents.getUserID());
    
    FB.getLoginStatus()
    // console.log(FB.Canvas.getHash() )
  },updated(){
  },
  methods : {
    loadFbSDN(){
        console.log("start loadFbSDN")
        (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "https://connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
        // console.log("end loadFbSDN")
    } ,
    initFbLogin(){
        console.log("start initFbLogin");
        window.fbAsyncInit = function() {
            FB.init({
                appId      : '371848510188221',
                cookie     : true,  // enable cookies to allow the server to access 
                                        // the session
                xfbml      : true,  // parse social plugins on this page
                version    : 'v2.7' // The Graph API version to use for the call
            });
        }
        console.log("end initFbLogin");
        
    }
    ,checkLoginState(){
        FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                // The user is logged in and has authenticated your
                // app, and response.authResponse supplies
                // the user's ID, a valid access token, a signed
                // request, and the time the access token 
                // and signed request each expire.
                var uid = response.authResponse.userID;
                var accessToken = response.authResponse.accessToken;
            } else if (response.status === 'not_authorized') {
                // The user hasn't authorized your application.  They
                // must click the Login button, or you must call FB.login
                // in response to a user gesture, to launch a login dialog.
            } else {
                // The user isn't logged in to Facebook. You can launch a
                // login dialog with a user gesture, but the user may have
                // to log in to Facebook before authorizing your application.
            }
        })
        },
    }
}
</script>