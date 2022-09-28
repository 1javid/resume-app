package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {

    List<User> getAllUsers();

    User getById(int userId);

    boolean addUser(User u);

    boolean updateUser(User u);

    boolean deleteUser(int userId);
}
