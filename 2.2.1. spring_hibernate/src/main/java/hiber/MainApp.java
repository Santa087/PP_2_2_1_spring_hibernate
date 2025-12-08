package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

       Car car1 = new Car("Volvo", 1);
       Car car2 = new Car("Ford", 2);
       Car car3 = new Car("BMW", 7);
       Car car4 = new Car("Chevy", 8);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

       User userWithCar = userService.getUserByCar("BMW", 7);
       System.out.println("Пользователь с BMW " + userWithCar.getFirstName());
       User  userWithCar2 = userService.getUserByCar("Ford", 2);
       System.out.println("Пользователь с Ford  " + userWithCar2.getFirstName());


       List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();

         Car car = user.getCar();
         if  (car != null) {
            System.out.println("Car model = "+car.getModel());
             System.out.println("Car series = "+car.getSeries());
         } else {
             System.out.println("Car = null");
         }
      }

      context.close();
   }
}
