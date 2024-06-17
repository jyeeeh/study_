const app = Vue.createApp({
    data(){
        return {
            boxASelected : false,
            boxBSelected : false,
            boxCSelected : false
        };
    },
    // 연산 프로퍼티 사용 
    // 동적 클래스 코드 자주 사용 시 
    computed: {
        boxAClasses(){
            // 객체로 반환
            return {active: this.boxASelected };
        }
    },

    methods: {
        boxSelected(box){
            if(box==='A'){
                this.boxASelected=!this.boxASelected;
            } else if (box==='B'){
                this.boxBSelected=!this.boxBSelected;
            } else if (box==='C'){
                this.boxCSelected=!this.boxCSelected;
            }
        }
    }

});

app.mount('#styling');