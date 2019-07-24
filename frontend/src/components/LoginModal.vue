<template>
  <transition name="modal">
    <div v-if="showModal" class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              로그인
              <a class="modal-default-button" @click="$emit('close')">
                X
              </a>
            </slot>
          </div>

          <form class="login-form" action="/login" method="POST">
          <input type ="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/> 
          <div class="modal-body">
            <slot name="body">
              <div class="d-inline-flex">
                <span class="col-md-4 col-xs-12" style="width:90px">ID</span>
                <span class="col-md-8 col-xs-12 ml-auto">
                  <input type="text" name="username" v-model="user.id" class="validate">
                </span>
              </div>
              <div class="d-inline-flex mt-2">
              <span class="col-md-4 col-xs-12" style="width:90px">PW</span>
              <span class="col-md-8 col-xs-12 ml-auto">
                <input type="password" name="password" v-model="user.password" class="validate">
              </span>
              </div>
              <div class="d-inline-flex w-100">
              <button class="modal-default-button btn-primary btn btn-rounded w-100 mt-3" type="submit" value="로그인" >
                Login
              </button>
                
              </div>
            </slot>
          </div>
          </form>

          <div class="modal-footer row">
            <slot name="footer">
              <!-- 네이버아이디로로그인 버튼 노출 영역 -->
              <div id="naverIdLogin" class="w-100 ml-3 d-inline-flex"></div>
              <!-- 페북 로그인 버튼 노출 영역
              <FBC :user="user" class="w-100 ml-3 mt-3 d-inline-flex"/> -->
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
// import FBC from './FacebookComponent'
 
export default {
  name: 'LoginModal',
  components: {
    // FBC
  },
  props : {
    showModal : {type: Boolean},
    user :{
      id : "",
    },
  }
}
</script>

<style>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 400px;
  padding: 20px 30px;
  margin: 0px auto;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.modal-body {
  margin: 20px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
