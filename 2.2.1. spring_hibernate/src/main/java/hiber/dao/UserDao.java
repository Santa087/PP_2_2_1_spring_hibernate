package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void updateUsers(List<User> users);
   User getUserByCar(String model, int series);
}
