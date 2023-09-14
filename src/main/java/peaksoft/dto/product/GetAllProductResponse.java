package peaksoft.dto.product;

import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class GetAllProductResponse{
        private Long id;
        private String brandName;
        private String brandImage;
        private String name;
        private BigDecimal price;
        private List<String> images;
        private String characteristic;
        private String madeIn;
        private Category category;
        private List<String> comment;
        private int countFavorite;

        public GetAllProductResponse(Long id, String name, BigDecimal price, String characteristic, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
}

    public GetAllProductResponse(String brandName, String brandImage) {
        this.brandName = brandName;
        this.brandImage = brandImage;
    }



}
