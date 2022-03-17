package org.egen.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egen.ecommerce.enums.ItemStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
  private String name;
  private Integer quantity;
  private ItemStatusEnum status;
}
