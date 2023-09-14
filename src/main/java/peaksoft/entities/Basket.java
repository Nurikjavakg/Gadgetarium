package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> products;
    @OneToOne
    private User user;

    public void addProduct(Product product){
        if (products == null) products = new ArrayList<>();
         else products.add(product);
    }

}