package com.etiya.ecommercedemopair1.business.dtos.response.categoryproduct;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductCategoryResponse {
    private String productName;
    private String categoryName;
}
