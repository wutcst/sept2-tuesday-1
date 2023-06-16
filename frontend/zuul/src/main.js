import { createApp } from 'vue'
import App from './App.vue'
import Vant from 'vant';
import 'vant/lib/index.css';
import {Lazyload} from 'vant';
import axios from 'axios';

const app = createApp(App) 
app.directive('scroll', {

    inserted(el) {
  
        el.scrollIntoView()
        
    }
  
  })
  app.config.globalProperties.$http = axios
app.use(Lazyload).use(Vant).mount('#app')




