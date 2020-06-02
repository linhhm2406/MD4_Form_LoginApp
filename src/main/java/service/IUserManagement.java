package service;

import model.User;

public interface IUserManagement {

    String checkLogin(String username, String password);
    User findByUserName(String username);
}
