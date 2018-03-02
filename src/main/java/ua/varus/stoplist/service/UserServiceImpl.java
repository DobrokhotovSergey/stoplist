package ua.varus.stoplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.varus.stoplist.dao.UserDao;
import ua.varus.stoplist.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDao userDao;



    @Override
    public List<Object> getAllPrincipals(){
        return sessionRegistry.getAllPrincipals();
    }



    @Override
    public List<User> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(u-> (User)u)
                .collect(Collectors.toList());
    }

    @Override
    public String getUser() {
        return request.getRemoteUser();
    }

    @Override
    public boolean hasRole(String role) {
        return request.isUserInRole(role);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.createUser(user);
    }

    @Override
    public boolean killSessionUser(String login) {

        UserDetails userName = userDetailsService.loadUserByUsername(login);
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object user: users) {
            if(userName.equals(user)){
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(user, false);
                sessionRegistry.getSessionInformation(sessions.get(0).getSessionId()).expireNow();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getOnlineUsers() {
        List<User> list = userDao.getAllUsers();
        for(User emp: list){
            for(Object user: sessionRegistry.getAllPrincipals()){
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(user, false);
                if(emp.getUsername().equals(((UserDetails)user).getUsername())){
                    emp.setOnline(true);
                }
            }
        }
        return list;
    }

    @Override
    public byte[] getAvatar(String userName) {
        return userDao.getAvatar(userName);
    }

    private boolean checkEditByRoles(User user){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Collection<? extends GrantedAuthority> authorities = ((UserDetails)principal).getAuthorities();
            String roleInitiator = authorities.toArray()[0].toString();

            String roleEdit = userDao.findByUserName(user.getUsername()).getUserRole().getRole();

            if("ROLE_RADMIN".equals(roleInitiator) && "ROLE_RADMIN".equals(roleEdit)){
                return false;
            }else if("ROLE_RADMIN".equals(roleInitiator) && "ROLE_SADMIN".equals(roleEdit)){
                return true;
            }else if("ROLE_SADMIN".equals(roleInitiator) && "ROLE_SADMIN".equals(roleEdit)){
                return false;
            }else if("ROLE_RADMIN".equals(roleInitiator) || "ROLE_SADMIN".equals(roleInitiator)){
                return true;
            }
        }


        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        if(checkEditByRoles(user)){
           killSessionUser(user.getUsername());
           return userDao.deleteUser(user);
        }

        return false;
    }

    @Override
    public User editUser(User user) {
        if(checkEditByRoles(user)){
            killSessionUser(user.getUsername());
            return userDao.editUser(user);
        }
        return null;
    }

    @Override
    public boolean uploadUserImage(MultipartFile multipartFile, String user) {
        return userDao.uploadUserImage(multipartFile, user);
    }

}