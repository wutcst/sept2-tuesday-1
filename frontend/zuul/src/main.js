import { createApp } from 'vue'
import App from './App.vue'
import router from "../router";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from "axios";
const app=createApp(App)
app.config.globalProperties.$http = axios
for ( const [key, component] of
    Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}
app.use(ElementPlus)
app.use(router)
app.mount('#app')
