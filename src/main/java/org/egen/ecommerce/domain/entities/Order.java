package org.egen.ecommerce.domain.entities;

import lombok.*;
import org.egen.ecommerce.enums.DeliveryMethodEnum;
import org.egen.ecommerce.enums.OrderStatusEnum;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order extends AbstractAuditableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_id", nullable = false)
  private String orderId;

  @Enumerated(EnumType.STRING)
  @Column(name="status", nullable = false)
  private OrderStatusEnum status;

  @Column(name="customer_id", nullable = false)
  private Long customerId;

  @Column(name = "sub_total", nullable = false)
  private Double subTotal;

  @Column(name = "tax", nullable = false)
  private Double tax;

  @Column(name= "shipping_charges")
  private Double shippingCharges;

  @Column(name = "total", nullable = false)
  private Double total;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipping_address_id", nullable = true)
  private Address shippingAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", nullable = false)
  private Set<Payment> payments;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", nullable = false)
  private Set<Item> items;

  @Enumerated(EnumType.STRING)
  @Column(name="delivery_method")
  private DeliveryMethodEnum deliveryMethod;

  @Column(name = "completed_at")
  private Instant completedAt;

  @Column(name = "cancelled_at")
  private Instant cancelledAt;
}
