<template>
<div>
    <div class="gitchart">
        <canvas id="myChart"></canvas>
    </div>
</div>
</template>

<script>
import Inner from '../apis/Inner'
import axios from 'axios'

export default {
    name: "repository",
    props :{
        user :{}
    },
    data (){
        return{
            repoUrl:"https://lab.ssafy.com/api/v4/projects/6029/events?targettype=commit",
            repoData : [],
            sortData : [{
                "최재형" : 149,
                "count" : 0
            },{
                "강성진" : 439,
                "count" : 0
            },{
                "이기문" : 419,
                "count" : 0
            }]
        }
    },
    async mounted() {
        await this.getRepo()
        // 함수
        console.log("비동기삐용삐용")
        console.log(this.sortData)
        console.log("차트 그리기");
        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'bar',

            // The data for our dataset
            data: {
                labels: ["최재형","강성진","이기문"],
                datasets: [{
                    label: '총 Commit 수',
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(255, 99, 132)',
                    data: [this.sortData[0].count,this.sortData[1].count,this.sortData[2].count]
                }]
            },

            // Configuration options go here
            options: {
                scales: {
                    xAxes: [{
                        barPercentage: 0.5,
                        barThickness: 60,
                        maxBarThickness: 30,
                        minBarThickness :2,
                        minBarLength: 2,
                        gridLines: {
                            offsetGridLines: true
                        }
                    }],
                     yAxes: [
                        {
                            ticks: {
                                beginAtZero: true,
                                steps: 10,
                                stepValue: 10,
                            }
                        }
                    ]
                }
            }
        });
    },
    methods :{
        async getRepo() {
            let starttime = new Date(2019,7,6)
            let endtime = new Date(2019,7,8)
            
            for(let i=0;i<=14;i++){
                starttime.setDate(starttime.getDate()+1)
                endtime.setDate(endtime.getDate()+1)

                const headers = { 
                    params:{
                    'actiontype':'push',
                    'after':this.typeCast(starttime),
                    'before':this.typeCast(endtime),}
                    , headers: { 
                        'PRIVATE-TOKEN': "PujYy-3YEsd9G-yPZzMx" } 
                        }  
                
                await axios.get(this.repoUrl,headers)
                    .then((res)=>{
                        //? 데이터 불러오는 방법 까먹음
                        let arr = []
                        for(let i=0;i<res.data.length;i++){
                            arr[i]={"id":res.data[i].author_id, "date": res.data[i].created_at.substring(0,10)}
                            if(arr[i].id==149){
                                this.sortData[0].count++
                            }else if(arr[i].id==419){
                                this.sortData[1].count++
                            }else if(arr[i].id==439){
                                this.sortData[2].count++
                            }
                        }
                        this.repoData=this.repoData.concat(arr)
                        this.repoData.sort(function(a, b) { // 오름차순
                            
                            return a.date < b.date ? -1 : a.date > b.date ? 1 : 0;
                            // 광희, 명수, 재석, 형돈
                        
                        });
                        console.log("getRepo 안입니다람")
                    })

            }

        },
        typeCast(selectDate){
            return "2019-"+selectDate.getMonth()+"-"+selectDate.getDate()
        },
    }
}
</script>

<style>
@media (max-width: 720px){
    .gitchart{
        display:none;
    }
}

</style>
