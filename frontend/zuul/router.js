import { createRouter , createWebHashHistory } from "vue-router";
import UserLogin from "@/components/UserLogin";
import UserRegister from "@/components/UserRegister";
import ForgetPassword from "@/components/ForgetPassword";
import Chat from '@/components/Chat';

const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {path:'/',redirect:'/login'},
        {path:'/login',component:UserLogin},
        {path:'/register',component:UserRegister},
        {path:'/forgetpwd',component:ForgetPassword},
        {path:'/chat',component:Chat}
    
    ]
})
// 导航守卫
router.beforeEach((to,from,next)=>{

 if(to.fullPath.indexOf('/chat')!=-1){
     if(localStorage.getItem('token')){
         next()
     }else{
         next('/login')
     }
 }else {
     next();
 }
})

export default router