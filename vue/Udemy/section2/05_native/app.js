const app = Vue.createApp({
  data() {
    return {
      counter: 0,
      name: '',
      lastName:'',
      fullname: ''
    };
  },
  watch: {
    name(value){
      if(value===''){
        this.fullname='';
      } else {
        this.fullname=value+' '+this.lastName;
      }
    },
    lastName(value){
      if(value===''){
        this.fullname='';
      } else {
        this.fullname=this.name+' '+value;
      }
    }
  },
  // 연산
  // 연산 프로퍼티 이름은 데이터 프로퍼티처럼 정하기
computed: {
  //computed 프로퍼티에 있는 fullname HTML 코드에 사용할 수 있음
  // fullname(){
  //   console.log("eeee");
  //   if (this.name===''){
  //     return '';
  //   } else {
  //   return this.name + ' ' + "Choi";
  //   }
  // }
  
},

methods: {
    outputFullname(){
      console.log('running');
      if (this.name===''){
        return '';
      } else {
      return this.name + ' ' + "Choi";
      }
    },
    setName(event, lastName) {
      this.name = event.target.value;
    },
    add(num) {
      this.counter = this.counter + num;
    },
    reduce(num) {
      this.counter = this.counter - num;
      // this.counter--;
    },
    resetInput(){
      //초기화
      this.name='';
      
    }
  }
});

app.mount('#events');
