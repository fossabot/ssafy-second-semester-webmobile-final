import mainServices from '@/apis/mainservice/mainServices.js'

const state = {
  posts: [],
  post: {},
}

const getters = {
  totalComments(state) {
    return state.post.postComments ? state.post.postComment.length : 0
  }
}

const actions = {
  getPosts({commit}) {
    mainServices.getPosts()
                .then((posts) => {
                  commit('setPosts', posts)
                })
  },
  getPost({commit},postId) {
    mainServices.getPost(postId) 
                .then((post) => {
                  commit('setPost',post)
                })
  },
}

const mutations = {
  setPosts(state, posts) {
    state.posts = posts
  },
  setPost(state, post) {
    state.post = post
  },

  setNewPost(state) { 
    state.post = {
      accountEmail: "",
      accountName: "",
      postTitle: "",
      postContent: "",
      postThumbnailUrl: "",
    }
  },

}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
