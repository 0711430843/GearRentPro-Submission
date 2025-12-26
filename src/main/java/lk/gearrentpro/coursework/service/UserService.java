package lk.gearrentpro.coursework.service;

import lk.gearrentpro.coursework.dao.UserDAO;
import lk.gearrentpro.coursework.entity.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) throws Exception {
        if (username.isEmpty() || password.isEmpty()) {
            throw new Exception("Username and Password are required!"); 
        }
        User user = userDAO.validateUser(username, password);
        if (user == null) {
            throw new Exception("Invalid credentials!"); 
        }
        return user;
    }
}