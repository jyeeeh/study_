package hello.core.order;

public interface OrderService {

    // 주문생성
    // 회원id, 상품명, 상품 가격 (최종order 반환)
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
