package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.User;
import peaksoft.enums.Action;
import peaksoft.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebSocketUserService {
    private final UserRepository userRepository;
    public void saveUser(User user){
        user.setAction(Action.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user){
        var storedUser = userRepository.findById(user.getId()).orElseThrow(null);
        if(storedUser != null){
            storedUser.setAction(Action.OFFLINE);
            userRepository.save(storedUser);
        }
    }
    public List<User> getConnectedUsers(){
        return userRepository.findAllByAction(Action.ONLINE);
    }
}
