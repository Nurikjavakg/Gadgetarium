package peaksoft.configWebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://127.0.0.1:5500","http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }













//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/spring-boot-tutorial")
//                .setAllowedOriginPatterns("*")
//                .withSockJS();
//    }
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry
////                .setAllowedOrigins("http://localhost:3000") // not setAllowedOriginPatterns
////                .withSockJS();
////    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.setApplicationDestinationPrefixes("/app");
//        config.enableSimpleBroker("/topic", "/user");
//        config.setUserDestinationPrefix("/user");
//
//    }
//
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
//        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        converter.setContentTypeResolver(resolver);
//        messageConverters.add(converter);
//        return false;
//    }
}