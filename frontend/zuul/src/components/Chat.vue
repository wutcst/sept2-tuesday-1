<template>
    <div class="container">
        <div class="list" id="list" ref="list" >
            <ul>
                <li v-for="(item,index) in msglist" :key="index">
                    <RightItem :id="item.id"  :content="item.content" v-if="item.me"></RightItem>
                    <LeftItem :id="item.id" :content="item.content" v-else></LeftItem>
                    <div v-scroll style="height: 0"></div>
                </li>
            </ul>
        </div>
        <div class="bottom">
            <div class="line"></div>
            <div class="input-send">
                <van-field v-model="text" placeholder="请输入命令" class="input" @keyup.enter="send"/>
                <van-button plain type="info" class="send" @click="send">发送</van-button>
            </div>

        </div>

    </div>
</template>

<script>
    import LeftItem from "@/components/LeftItem";
    import RightItem from "@/components/RightItem";



    export default {
        name: "Chat",
        components: {LeftItem, RightItem},
        data: () => {
            return {
                text: '',
                msglist: [{
                    id: 1,
                    content: 'Welcome to the World of Zuul!',
                    me: false
                },
            {
                id: 2,
                content: 'World of Zuul is a new, incredibly boring adventure game.',
                me: false   
            },
            {
                id: 3,
                content: 'Type "help" if you need help.',
                me: false   
            },
            {
                id: 4,
                content: 'You are outside the main entrance of the university.Exits: east south west',
                me: false   
            },
        
        ]
            }
        },
        methods: {
            send() {
                if (this.text) {
                    this.msglist.push({
                        id: this.msglist[this.msglist.length - 1].id + 1,
                        content: this.text,
                        me: true
                    })
                    
                    this.$http.post('http://localhost:80/cmd',{'command':this.text}).then(res =>{
                        if(res.data.flag == true){
                            this.msglist.push({
                            id: this.msglist[this.msglist.length - 1].id + 1,
                            content: res.data.data,
                            me: false
                    })
                        }
                        else{
                            alert(""+res.data.message)
                        }
                    })
                    this.text = ''
                }
                else{
                    alert("输入不能为空")
                }
            },
    
        }
    }
</script>

<style scoped lang="scss">
    .container {
        ul {
            padding: 0;
            margin: 0;
        }

        li {
            list-style: none;

        }

        .list {
            width: 100%;
            height: 80%;
            margin-bottom: 110px;
        }

        .bottom {
            width: 100%;
            position: fixed;
            bottom: 70px;

            .line {
                width: 100%;
                height: 1px;
                background-color: #ddd;
            }

            .input-send {
                display: flex;
                justify-content: space-between;
                background-color: #fff;

                .input {
                    padding-right: 10px;
                }

                .send {
                    width: 80px;
                    height: 30px;
                    margin-top: 7px;
                    margin-right: 10px;
                    border-color: rgba(0, 255, 255, 0.803);
                }
            }

        }
    }


</style>