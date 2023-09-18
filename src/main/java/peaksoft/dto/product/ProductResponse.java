package peaksoft.dto.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;
//@Getter
//@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse{
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
    private List<String>favoriteFromUser;
    private int countFavorite;
    private int countComment;

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
    }

    public ProductResponse(String brandName, String brandImage) {
        this.brandName = brandName;
        this.brandImage = brandImage;
    }

    public ProductResponse(String brandName) {
        this.brandName = brandName;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public void setCountFavorite(int countFavorite) {
        this.countFavorite = countFavorite;
    }

    public Long getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<String> getImages() {
        return images;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getComment() {
        return comment;
    }

    public int getCountFavorite() {
        return countFavorite;
    }

    public List<String> getFavoriteFromUser() {
        return favoriteFromUser;
    }

    public void setFavoriteFromUser(List<String> favoriteFromUser) {
        this.favoriteFromUser = favoriteFromUser;
    }
}

