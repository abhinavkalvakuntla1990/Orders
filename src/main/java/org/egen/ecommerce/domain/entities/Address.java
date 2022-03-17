package org.egen.ecommerce.domain.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "address")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String line1;

  @Column
  private String line2;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private String zip;

}
