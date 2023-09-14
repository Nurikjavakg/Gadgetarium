package peaksoft.dto.product;


import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(

         String name,
         BigDecimal price,
         List<String>images,
         String characteristic,
         String madeIn,
         Category category



) {
    public ProductRequest {
    }
}
