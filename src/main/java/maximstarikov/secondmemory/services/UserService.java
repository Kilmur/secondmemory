package maximstarikov.secondmemory.services;

import maximstarikov.secondmemory.model.User;

public interface UserService {

    User getByName(String userName);

    void save(User user);

}
