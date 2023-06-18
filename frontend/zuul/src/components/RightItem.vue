<template>
    <div class="container">
        <img class="head"
             src="../assets/image_me.jpg"/>
        <div class="content">
            <div class="text" style="word-break: break-all;">
                {{content}}
            </div>
            
        </div>
    </div>
</template>

<script>

    export default {
        name: "RightItem",
        props: ['id',  'content']
    }
</script>

<style scoped lang="scss">
    .container {
        display: flex;
        padding: 10px 15px;
        margin-left: 60px;
        flex-direction: row-reverse;

        .head {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            border: 1px solid #eee;
        }

        .content {
            margin-right: 10px;
            margin-top: 10px;

            .text {
                background-color: #eee;
                border-bottom-left-radius: 10px;
                padding: 5px 5px;
                font-size: 18px;
                color: #000;
            }

            .img {
                width: 100px;
                border-bottom-right-radius: 10px;
                border-top-right-radius: 10px;
                border-bottom-left-radius: 10px;
            }
        }

    }
</style>