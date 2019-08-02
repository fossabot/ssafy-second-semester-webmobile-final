<template>
  <div class="container">
    <div style="height:100px"></div>
    <!-- Title -->
    <div class="row justify-content-center mt-5">
      <Title title="portfolios"></Title>
      <router-link :to="{ name: 'newportfolio', params: {} }" class="btn btn-sm btn-outline-info border-0 mt-1 ml-1" style="height: 28px">
        <i class="fas fa-pen"></i>
      </router-link>
    </div>
    <!-- List compo -->
    <div class="row justify-content-center">
      <HorizenItem></HorizenItem>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
import Title from '../components/Title'
import HorizenItem from '../components/HorizenItem'


export default {
  name: 'PortfolioListPage',
  components : {
    HorizenItem,
    Title
  },
  methods: {
    ...mapMutations('articles',['setCategory'])
  },
  created () { // 중요한 점: state mutation 은 항상 created 에 박아야 순서대로 박힌다. created는 부모부터 mounted는 자식부터
    this.setCategory({
      categoryName: "portfolios",
      categoryId: 1
    })
  }
}
</script>

<style>
</style>
