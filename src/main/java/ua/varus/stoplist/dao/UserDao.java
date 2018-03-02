package ua.varus.stoplist.dao;


import org.springframework.web.multipart.MultipartFile;
import ua.varus.stoplist.domain.User;

import java.util.List;

public interface UserDao {
    User findByUserName(String username);
    User createUser(User user);
    List<User> getAllUsers();
    byte[] getAvatar(String userName);
    boolean deleteUser(User user);
    User editUser(User user);
    boolean uploadUserImage(MultipartFile multipartFile, String user);

}
