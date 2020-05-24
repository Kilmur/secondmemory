package maximstarikov.secondmemory.services.impl;

import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;
import maximstarikov.secondmemory.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// TODO : подумать стоит ли объединять этот класс с UserServiceImpl ?
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        ServiceResult<User> userByNameResult = userService.getByName(name);
        if (!userByNameResult.isOk()) {
            LOGGER.warn(userByNameResult.getErrorMessage());
            throw new UsernameNotFoundException(userByNameResult.getErrorMessage());
        }
        return userByNameResult.get();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
