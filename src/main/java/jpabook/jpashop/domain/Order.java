package jpabook.jpashop.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //오더입장에선 멤버한사람이 주문은 여러번 가능하니 오더입장에선 manytoone 임ㅎ
    @JoinColumn(name = "MEMBER_ID") // 기본키 멤버 테이블 연결해줌
    private Member member;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) //cascade 는 order 저장하면 delivery 도 같이 저장됨
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)  // orderitems 양방향
    private List<OrderItem> OrderItems = new ArrayList<>(); // 양방향

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public void addOrderItem(OrderItem orderItem) {
        OrderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


}
