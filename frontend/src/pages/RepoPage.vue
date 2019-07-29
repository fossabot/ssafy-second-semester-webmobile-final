<template>
<div class="container">
    <div style="height:100px"></div>
    <!-- Title -->
    <div class="row justify-content-center mt-5">
      <Title title="Repository"></Title>
    </div>
    <div>
        <Repository />
    </div>
    <div class ="row justify-content-center">
        <a href="https://lab.ssafy.com/ksj0206/webmobile-sub2">Repository 바로가기</a>
    </div>
</div>
</template>

<script>
import Inner from '../apis/Inner'
import axios from 'axios'
import Repository from '../components/Repository'
import Title from '../components/Title'

export default {
    name: "repopage",
    data (){
        return{

        }
    },
    components : {
        Repository,
        Title
    }
}
</script>

<style>

</style>
