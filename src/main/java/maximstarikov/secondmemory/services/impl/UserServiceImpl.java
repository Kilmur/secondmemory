package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.UserRepository;
import maximstarikov.secondmemory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public User getByName(String userName) {
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        return optionalUser.orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
