<template>
<div>
    <div style="height:100px"></div>
    <!-- Editor compo -->
    <Editor></Editor>  
</div>
</template>

<script>
import Editor from "../components/Editor"

export default {
    name: "WritePostPage",
    components: {
        Editor,
    },

    methods: {
        // TODO : Editor 에 있는 post get put 로직 다 빼내오기 및 post/ portfolio 분기 처리
    }
}
</script>

<style>

</style>
