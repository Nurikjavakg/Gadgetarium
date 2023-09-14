package peaksoft.dto.product;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;


public record GetAllProductRequest(
        String name,
        BigDecimal price,
        List<String>images,
        String characteristic,
        String madeIn,
        Category category,
        BigDecimal startPrice,
        BigDecimal endPrice
) {



}
