//package peaksoft.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import peaksoft.entities.ChatRoom;
//import peaksoft.entities.Comment;
//
//import java.util.Optional;
//
//public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
//    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
//}
