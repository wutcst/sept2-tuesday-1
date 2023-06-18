<template>

  <router-view :scn_width="screenSize.scn_width"></router-view>

</template>
<script>



export default {
  name: 'App',
  components: {


  },
  data(){
    return{
      screenSize:{
        scn_width: document.documentElement.clientWidth,
        scn_height: document.documentElement.clientHeight
      }
    }
  },

  mounted() {
    window.onresize = ()=>{
      this.screenSize.scn_width = document.documentElement.clientWidth;
      this.screenSize.scn_height = document.documentElement.clientHeight;
    }
  }
  
}
</script>

<style scoped>

#app {
  
  margin-top: 0px;
}
body{
  height : 100%;
}
a{
  text-decoration: none;
}
</style>
