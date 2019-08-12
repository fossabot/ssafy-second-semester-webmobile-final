<template>
  <!-- Card List -->
  <div class="container my-5">
    <transition-group name="portfoliolist" tag="p">
      <!-- Card -->
      <div v-for="(portfolio,idx) in portfolios" v-bind:key="portfolio.portfolioId" class="portfoliolist-item card mx-3 my-3" style="width: 15rem;">
        <!-- Card Image -->
        <img :src="portfolio.portfolioThumbnailUrl ? portfolio.portfolioThumbnailUrl : 'https://source.unsplash.com/random/500x500'" class="card-img-top" style="width: 100%; height: 15rem" alt="...">
        <!-- Card Body -->
        <div class="card-body">
          <h5 class="card-title card-text-ellipsis">{{ portfolio.portfolioTitle }}</h5>
          <p class="card-text card-text-ellipsis" style="color: gray;">by {{ portfolio.accountName }}</p>
          <!-- Buttons -->
          <div class="row justify-content-end">
            <!-- 더 보기 -->
            <i class="far fa-eye py-2" style="color: rgb(100,100,100)"></i> &nbsp; 
            <span class="py-1" style="color: rgb(100,100,100)">{{ portfolio.portfolioViews}} </span> &nbsp;
            <router-link :to="{ name: 'PortfolioDetailPage', params: { portfolioId: portfolio.portfolioId }}" class="btn btn-sm btn-outline-info mx-1">
              <i class="fas fa-plus"></i>
            </router-link>
          </div>        
        </div>
      </div>
    </transition-group>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'PortfolioList',
  computed: {
    ...mapState('portfolio', ['portfolios']),
  },
}
</script>

<style>
.card {
    display: inline-block;
}

.card-text-ellipsis {
    width: 13rem;
    overflow:hidden; 
    white-space:nowrap;
    text-overflow:ellipsis;
}

.portfoliolist-item {
  display: inline-block;
  margin-right: 10px;
}
.portfoliolist-enter-active {
  transition: all 1s;
}
.portfoliolist-enter  {
  opacity: 0;
  transform: translateY(30px);
}
</style>
