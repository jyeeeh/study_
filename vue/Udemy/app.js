// Global Vue 객체
Vue.createApp({
    data() {
        return{
            //Vue 앱이 인식해야 하는 데이터를 이 객체에서 정의
            // goals 배열은 초기에 비어 있는 키-값 쌍
            // enteredValue 는 초기에 비어 있는 문자열
            goals: [],
            enteredValue: ''
        };
    },
    // methods 키 <- 객체를 가짐
    methods : {
        addGoal(){
            this.goals.push(this.enteredValue);
            this.enteredValue='';
        }
    }
    // Vue 앱에 페이지 어느 부분을 조정할지 알려주기
}).mount('#app');

// ------------------ 기본 js 설정 ----------------------
// 
// // 페이지에 있는 버튼만 선택하도록
// const buttonEl = document.querySelector('button');
// const inputEl = document.querySelector('input');
// const listEl = document.querySelector('ul');

// function addGoal(){
//     const enteredValue = inputEl.value;
//     const listItemEl = document.createElement('li');
//     // textContent를 설정해서 목록 항목을 열고 닫는 텍스트가 enteredValue 와 같게 설정
//     listItemEl.textContent = enteredValue;
//     listEl.appendChild(listItemEl);
//     // 재설정 - input 요소의 값을 빈 텍스트로 설정해 초기화 시켜줌
//     inputEl.value='';
// }

// // 클릭 이벤트 수신
// // click 을 첫 번째 인수로 전달해서
// buttonEl.addEventListener('click' , addGoal);
