package com.ecommerce.blossom.api.blossom_api.mappers;

import com.ecommerce.blossom.api.blossom_api.dtos.OrderHistoryDTO;
import com.ecommerce.blossom.api.blossom_api.dtos.ProductSummaryDTO;
import com.ecommerce.blossom.api.blossom_api.entities.Order;
import com.ecommerce.blossom.api.blossom_api.entities.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "orderProducts", target = "products")
    OrderHistoryDTO orderToDTO(Order order);

    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.price", target = "price")
    ProductSummaryDTO orderProductToDTO(OrderProduct orderProduct);

    List<ProductSummaryDTO> orderProductsToDTO(List<OrderProduct> orderProducts);
}
