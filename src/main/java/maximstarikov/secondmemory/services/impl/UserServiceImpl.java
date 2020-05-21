package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.Role;
import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.repository.UserRepository;
import maximstarikov.secondmemory.services.BookService;
import maximstarikov.secondmemory.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private BookService bookService;


    public ServiceResult<User> addNewUser(User user) {
        ServiceResult userByNameResult = getByName(user.getUsername());
        if (userByNameResult.isOk()) {
            LOGGER.error(userByNameResult.getErrorMessage());
            return ServiceResult.error("Пользователь с именем " + user.getUsername() + "уже существует");
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        save(user);
        return ServiceResult.success(user);
    }


    public ServiceResult<User> getByName(String userName) {
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (!optionalUser.isPresent()) {
            return ServiceResult.error("Не удалось получить пользователя с именем " + userName);
        }
        return ServiceResult.success(optionalUser.get());
    }

    public ServiceResult<User> getCurrentUser() {
        return getByName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void save(User user) {// TODO : обработать исключения во всех save()
        userRepository.save(user);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRepository(BookService bookService) {
        this.bookService = bookService;
    }
}
