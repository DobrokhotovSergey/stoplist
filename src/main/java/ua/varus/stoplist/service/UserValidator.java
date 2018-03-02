package ua.varus.stoplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.varus.stoplist.dao.UserDao;
import ua.varus.stoplist.domain.User;

@Service
public class UserValidator implements Validator {
    @Autowired
    private UserDao userDao;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userDao.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

    }
}
