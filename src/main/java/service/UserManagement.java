package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagement implements IUserManagement {

    @Autowired
    List<User> userList;

    @Override
    public User findByUserName(String username) {
        User user = new User();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAccount().equals(username)){
                user = userList.get(i);
            }
        }
        return user;
    }

    @Override
    public String checkLogin(String username, String password) {
        String conclude = "";
        User user = findByUserName(username);
        if (user == null){
            conclude = "Not found account";
        } else {
            if (user.getPassword().equals(password)){
                conclude = "Login Ok";
            } else {
                conclude = "Wrong password";
            }
        }
        return conclude;
    }
}
