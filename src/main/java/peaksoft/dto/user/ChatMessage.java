package peaksoft.dto.user;

import lombok.*;
import peaksoft.enums.Action;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private Long id;
    private String content;
    private String sender;
    private Action type;
}
