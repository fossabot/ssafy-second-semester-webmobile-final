<template>
  
</template>

<script>
import mainServices from '@/apis/mainservice/mainServices'
export default {
  name: 'RestDocsPage',
  async created() {
    await mainServices.getRestDocs()
  }
}
</script>

<style>

</style>
