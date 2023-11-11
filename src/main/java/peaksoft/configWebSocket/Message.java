package peaksoft.configWebSocket;

import peaksoft.enums.Action;

import java.time.Instant;

public record Message(peaksoft.entities.User user, String receiverId, String comment, Action action, Instant timeStamp) {
}
