package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.ChatMessage;
import peaksoft.entities.ChatRoom;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatId(String chatId);
}
