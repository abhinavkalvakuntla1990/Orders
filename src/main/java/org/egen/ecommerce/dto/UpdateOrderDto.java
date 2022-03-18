package org.egen.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egen.ecommerce.enums.OrderStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private OrderStatusEnum status;
}
