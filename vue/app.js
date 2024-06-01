// 페이지에 있는 버튼만 선택하도록
const buttonEl = document.querySelector('button');
const inputEl = document.querySelector('input');
const listEl = document.querySelector('ul');

function addGoal(){
    const enteredValue = inputEl.value;
    const listItemEl = document.createElement('li');
    // textContent를 설정해서 목록 항목을 열고 닫는 텍스트가 enteredValue 와 같게 설정
    listItemEl.textContent = enteredValue;
    listEl.appendChild(listItemEl);
    // 재설정 - input 요소의 값을 빈 텍스트로 설정해 초기화 시켜줌
    inputEl.value='';
}

// 클릭 이벤트 수신
// click 을 첫 번째 인수로 전달해서
buttonEl.addEventListener('click' , addGoal);
