export  const userStatus = (state,user)=>{
  if(user){
    state.loginId = user
    state.isLogin = true
  }else if(user == null){
    sessionStorage.setItem("loginId",null)
    sessionStorage.setItem("userToken","")
    state.loginId = null
    state.isLogin = false
    state.token = ""
  }
}

export  const setUserInfo= (state,info)=>{
  if(info){
    state.userInfo = info
  }else if(info == null){
    sessionStorage.setItem("userInfo",null)
    state.userInfo = null
  }
}

