package service;

import dao.UserDao;
import model.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public User login(String username,String password){

        try{

            User user = userDao.findByUsername(username);

            if(user != null &&
                    user.getPassword().equals(password)){

                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}