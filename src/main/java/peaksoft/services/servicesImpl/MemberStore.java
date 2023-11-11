package peaksoft.services.servicesImpl;

import org.springframework.stereotype.Service;
import peaksoft.entities.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MemberStore {

    private static List<User> store = new LinkedList<>();


    public List<User> getMembersList() {
        AtomicInteger serialId = new AtomicInteger(1);
        return store.stream()
                .map(user -> new User(user.getId(), serialId.getAndIncrement() + "", user.getEmail()))
                .toList();
    }

    public List<User> filterMemberListByUser(List<User> memberList, peaksoft.entities.User user) {
        return memberList.stream()
                .filter(filterUser -> !Objects.equals(filterUser.getId(), user.getId()))
                .map(sendUser -> new User(null, sendUser.getSerialId(), sendUser.getUsername()))
                .toList();
    }

    public User getMember(String id) {
        return store.get(Integer.parseInt(id) - 1);
    }

    public void addMember(User member) {
        store.add(member);
    }

    public void removeMember(User member) {
        store.remove(member);
    }
}
