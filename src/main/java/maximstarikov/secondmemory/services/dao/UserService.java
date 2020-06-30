package maximstarikov.secondmemory.services.dao;

import maximstarikov.secondmemory.model.ServiceResult;
import maximstarikov.secondmemory.model.User;

public interface UserService {

    ServiceResult<User> addNewUser(User user);

    ServiceResult<User> getCurrentUser();

    ServiceResult<User> getByName(String userName);

    void save(User user);

}
