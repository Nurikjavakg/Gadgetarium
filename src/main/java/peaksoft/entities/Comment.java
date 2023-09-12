package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;
    private ZonedDateTime createdDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    @PrePersist
    public void prePersist() {
        this.createdDate = ZonedDateTime.now();

    }

}