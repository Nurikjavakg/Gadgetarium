//package peaksoft.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//import java.time.ZonedDateTime;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "chat")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Chat {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    private Long senderName;
//    private String receiverName;
//    private String message;
//    private ZonedDateTime date;
//
//    @ManyToOne
//    private User user;
//}
