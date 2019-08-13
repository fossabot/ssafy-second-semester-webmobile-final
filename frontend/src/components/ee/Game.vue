<template>
    <div class="w-100">
        <div v-for="(data,i) in table" class="row" style="height : 10vw">
            <div class="col p-3" v-for="(data,j) in data">
                <img :src='table[i][j].display' :id="i*5+j" onerror="this.src='https://source.unsplash.com/random/1600x600'" @click="action(i*5+j)" width="185px" height="100%"/> 
            </div>
        </div>
        <template v-for="data in srcarray">
            <img :src='data' width="1px" height="1px"/>
        </template>

    </div>
</template>

<script>
import { mapState, mapActions,mapMutations } from 'vuex'
import firebase from "../../apis/firebase/firebase"
import { functions } from 'firebase';

export default{
    name : 'Game',
    data(){
        return{
            table :[[],[],[],[]],
            count:0,
            selcard:"",
            cur : 0,
            srcarray :[]
        }
    },
    components : {
    },
    computed : {
        ...mapState('account',['accountName'])
    },
    created(){
        let srcarr = []
        for(let i =0;i<20;i++){
            if(i%2==1)
            srcarr[i]="https://source.unsplash.com/random/1600x"+ (i+699)
            else
            srcarr[i]="https://source.unsplash.com/random/1600x"+ (i+700)
        }
        this.srcarray=srcarr
        this.shuffle(srcarr)
        console.log(srcarr);
        
        for(let i=0;i<4;i++){
            for(let j=0;j<5;j++){
                this.table[i][j]=this.initTable(srcarr[(i*5)+j],(i*5)+j)
            }
        }
    }
    ,
    mounted() {
        let t = this.table
        setTimeout(function(){
            for(let i=0;i<20;i++){
                document.getElementById(i).setAttribute("src","")
            }
        },4000)
    },
    methods:{
        initTable(src,dif) {
            let data = {}
            data.display = src
            data.imgSrc = src
            data.index = dif
            return data
        },
        shuffle(a) {
            for (let i = a.length - 1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [a[i], a[j]] = [a[j], a[i]];
            }
            return a;
        },
        async action(i){
            console.log(document.getElementById(i).getAttribute("src"));
            console.log(this.table[Math.floor(i/5)][i%5].imgSrc);
            
            if( document.getElementById(i).getAttribute("src")!=this.table[Math.floor(i/5)][i%5].imgSrc){
                document.getElementById(i).setAttribute("src",this.table[Math.floor(i/5)][i%5].imgSrc)
                if(this.selcard!==""){
                    let slt = this.selcard
                    this.selcard=""
                    let before=this.table[Math.floor(slt/5)][slt%5].imgSrc
                    let after = this.table[Math.floor(i/5)][i%5].imgSrc
                    console.log(before + "\n" + after)
                    if(before!=after){
                        this.table[Math.floor(slt/5)][slt%5].display=""
                        this.table[Math.floor(i/5)][i%5].display=""
                        
                        let t = this.table
                        setTimeout(function(){
                            document.getElementById(i).setAttribute("src",t[Math.floor(i/5)][i%5].display)
                            document.getElementById(slt).setAttribute("src",t[Math.floor(slt/5)][slt%5].display)
                            t[Math.floor(i/5)][i%5].display=t[Math.floor(i/5)][i%5].imgSrc
                            t[Math.floor(slt/5)][slt%5].display=t[Math.floor(slt/5)][slt%5].imgSrc
                        },500)
                    }else{
                        this.cur = this.cur+1
                    }
                    this.count = this.count+1
                }
                else 
                    this.selcard=i
            }
            if(this.cur==10){
                let c =this.count
                await firebase.postRanking(this.count,this.accountName)
                let res = await firebase.getRanking()
                console.log(res);
                let arr =[]
                for(let score in res){
                    console.log(score)
                    arr[score]=res[score].score
                }
                arr.sort(function(a,b){
                    return a-b
                })
                console.log(arr);
                let str =""
                for(let i in arr){
                    if(arr[i]==c){
                        if(i==0){
                            alert("complete\n축하합니다 1등 입니다.\n\n클릭 횟수 : "+this.count+"회\n\n ")
                        }else {
                            alert("complete\n등수 :"+((i*1)+1)+"등\n클릭 횟수 : "+this.count+"회\n\n"+str)
                        }
                        break
                    }
                    str += ((1*i)+1)+"등 :"+arr[i]+"개\n"
                }
            }
        }
    }
}
</script>
