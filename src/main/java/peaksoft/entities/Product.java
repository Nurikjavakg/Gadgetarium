package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private BigDecimal price;
    @ElementCollection
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Basket>baskets;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Favorite>favorites;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Comment>comments;


    public void add(Basket basket) {
        if (baskets == null) baskets = new ArrayList<>();
        else baskets.add(basket);
    }
}
