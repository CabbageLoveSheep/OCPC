export const setUser = ({commit},user)=>{
  commit("userStatus",user)
}

export const setUserInfo = ({commit},info) => commit('setUserInfo', info)

