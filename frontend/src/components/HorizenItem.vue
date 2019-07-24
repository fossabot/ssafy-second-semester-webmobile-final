<template>
  <!-- Card List -->
  <div class="scrolling-wrapper my-5">
    <!-- Card -->
    <div v-for="article in cardData" class="card mx-3" style="width: 15rem;">
      <!-- Card Image -->
      <img :src="article.article_imgsrc" class="card-img-top" style="width: 100%; height: 15rem" alt="...">
      <!-- Card Body -->
      <div class="card-body">
        <h5 class="card-title">{{ article.article_title }}</h5>
        <p class="card-text card-text-ellipsis" style="color: gray">{{ article.article_content }}</p>
        <!-- Buttons -->
        <div class="row justify-content-end">
          <!-- 더 보기 -->
          <router-link :to="{ name: 'portfoliodetail', params: { article_no : article.article_no }}" class="btn btn-sm btn-outline-secondary mx-1">
            <i class="fas fa-plus"></i>
          </router-link>
          <!-- 수정 -->
          <router-link :to="{ name: 'editarticle', params: { article_no: article.article_no } }" class="btn btn-sm btn-outline-info mx-1 mr-3">
            <i class="far fa-edit"></i>
          </router-link>
        </div>        
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HorizenItem',
  props: {
    cardData : { type : Array }
  }
}
</script>

<style>
.scrolling-wrapper {
  overflow-x: scroll;
  overflow-y: hidden;
  white-space: nowrap;
  width: 80%;
  height: 420px;
}

.scrolling-wrapper::-webkit-scrollbar {
  height: 2px;
}

.scrolling-wrapper::-webkit-scrollbar-track {
  width: 5px;
}

.scrolling-wrapper::-webkit-scrollbar-thumb {
  background:  #8e97ab;
  border-radius: 10px;
}

.card {
    display: inline-block;
}

.card-text-ellipsis {
    width: 13rem;
    overflow:hidden; 
    white-space:nowrap;
    text-overflow:ellipsis;

}



</style>
