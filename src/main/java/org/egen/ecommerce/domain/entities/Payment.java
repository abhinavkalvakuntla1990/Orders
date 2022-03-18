package org.egen.ecommerce.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.egen.ecommerce.enums.PaymentMethodEnum;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "payments")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method")
  private PaymentMethodEnum paymentMethod;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "billing_address_id", nullable = true)
  private Address billingAddress;

  @Column(name="confirmation_number")
  private Long confirmationNumber;

  @Column(name = "payment_date")
  private Instant paymentDate;

  @Column(name = "amount", nullable = false)
  private Double amount;

}
