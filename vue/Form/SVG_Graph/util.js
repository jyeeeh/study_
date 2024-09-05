// 주어진 값을 원형으로 분포시키기 위해 좌표를 계산하는데 사용
// value: 원의 반지름, index: 원형 분포를 위해 점 위치 결정, total: 점의 개수
// ex) valueToPoint(10, 2, 6) 이면
// 이 점은 중심에서 10만큼 떨어진 곳에 위치하고
// 세 번째 점의 좌표를 계산하고
// 원 주위에 6개의 점이 균등하게 배치된다(360도를 6등분한 각도로 배치)
export function valueToPoint(value, index, total) {
    // 원의 중심을 기준으로 점의 위치를 초기화
    const x = 0
    // 원의 외곽에 가까운 위치를 갖기 위함 조정
    const y = -value * 0.8
    // 각도 계산 (Math.PI*2) <- 360도를 라디안으로 변환한 값
    const angle = ((Math.PI * 2) / total) * index
    // 회전 변환(회전 행렬 계산하는데 사용, 점의 좌표를 회전시킴)
    const cos = Math.cos(angle)
    const sin = Math.sin(angle)
    // 좌표 변환( +100 <- 좌표를 화면의 중앙으로 이동시키기 위한 오프셋)
    const tx = x * cos - y * sin + 100
    const ty = x * sin + y * cos + 100
    return {
        // 좌표 제공
        x: tx,
        y: ty
    }
}
