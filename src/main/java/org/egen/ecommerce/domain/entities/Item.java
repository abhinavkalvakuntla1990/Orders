package org.egen.ecommerce.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.egen.ecommerce.enums.ItemStatusEnum;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "items")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private Integer quantity;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ItemStatusEnum status;

  @Column(name="cancelled_at")
  private Instant cancelledAt;

}
