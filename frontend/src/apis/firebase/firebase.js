import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'

// Setup Firebase
const config = {
  apiKey: "AIzaSyDb1Lugdea1Bol0aNkYETqB4W-OKw8CCNI",
  authDomain: "ssafy-barebears.firebaseapp.com",
  databaseURL: "https://ssafy-barebears.firebaseio.com",
  projectId: "ssafy-barebears",
  storageBucket: "",
  messagingSenderId: "380624918537",
  appId: "1:380624918537:web:6d65b693b9cd1fa4"
}

let app = firebase.initializeApp(config)

firebase.firestore().settings({
  cacheSizeBytes: firebase.firestore.CACHE_SIZE_UNLIMITED
});

firebase.firestore().enablePersistence()
  .catch(function(err) {
    if (err.code == 'failed-precondition') {
      // Multiple tabs open, persistence can only be enabled
      // in one tab at a a time.
      // ...
    } else if (err.code == 'unimplemented') {
      // The current browser does not support all of the
      // features required to enable persistence
      // ...
    }
  });

firebase.firestore().disableNetwork()
  .then(function() {
    // Do offline actions
    // ...
    console.log("offline test");    
  });

firebase.firestore().enableNetwork()
  .then(function() {
    // Do online actions
    // ...
    console.log("online test");
  });


export default {
  getAccounts(){
    const accounts = firebase.firestore(app).collection("accounts")
    return accounts.get()
                  .then((docSnapshots) => {
                    return docSnapshots.docs.map((doc) => {
                      let data = doc.data()
                      return data
                    })
                  })
  },

  getToken(email) {
    const accounts = firebase.firestore(app).collection("accounts")
    return accounts.get()
                  .then((docSnapshots) => {
                    let doc = docSnapshots.docs
                    for(var i in doc) {
                      if(doc[i].data().email==email)
                        return doc[i].id
                    }
                  })
  },

  getNaverLogin(email){
    const accounts = firebase.firestore(app).collection("accounts")
    return accounts.get()
                  .then((docSnapshots) => {
                    let doc = docSnapshots.docs
                    for(var i in doc) {
                      if(doc[i].data().email==email)
                       return doc[i].data()
                    }
                  })
  },

  getLogin(email,password){
    //애초에 포이치문이 돎
    const accounts = firebase.firestore(app).collection("accounts")
    return accounts.get()
                  .then((docSnapshots) => {
                    let doc = docSnapshots.docs
                    for(var i in doc) {
                      if(doc[i].data().email==email && doc[i].data().password == password)
                        return doc[i].data()
                    }
                  })
  },

  //키를 가지고, 정보 가져오기
  getInfo(key){
    //애초에 포이치문이 돎
    const accounts = firebase.firestore(app).collection("accounts")    
    return accounts.get()
                  .then((docSnapshots) => {
                    let doc = docSnapshots.docs
                    for(var i in doc) {
                      if(doc[i].id==key)
                        return doc[i].data()
                    }
                  })
  },
  
  //회원가입
  postSignUp(email,password,gitlabemail,name){
    const accounts = firebase.firestore(app).collection("accounts")
    return accounts.add({
      auth : "3",
      email : email,
      gitlab_email : gitlabemail,
      name : name,
      password : password
    })
  }
}