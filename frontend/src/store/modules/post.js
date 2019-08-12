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
  loadPosts({commit}, pageNo) {
    mainServices.loadPosts(pageNo)
                .then((posts) => { 
                  if (pageNo === 1) {
                    commit('setPosts', posts)
                  } else {
                    commit('addPosts', posts)
                  }
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
  addPosts(state, posts) {
    state.posts = state.posts.concat(posts)
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
