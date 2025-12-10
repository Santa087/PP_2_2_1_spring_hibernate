package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        List<User> users = new ArrayList<>();
        users.add(new User("User1", "LastName1", "user1@mail.ru"));
        users.add(new User("User2", "LastName2", "user2@mail.ru"));
        users.add(new User("User3", "LastName3", "user3@mail.ru"));
        users.add(new User("User4", "LastName4", "user4@mail.ru"));

        for (User user : users) {
            userService.add(user);
        }

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Volvo", 1));
        cars.add(new Car("Ford", 2));
        cars.add(new Car("BMW", 7));
        cars.add(new Car("Chevy", 8));

        for (Car car : cars) {
            carService.addCar(car);
        }

        List<User> usersDb = userService.listUsers();
        List<Car> carsDb = carService.listCars();

        for (int i = 0; i < usersDb.size() && i < carsDb.size(); i++) {
            usersDb.get(i).setCar(carsDb.get(i));
        }

        userService.updateUsers(usersDb);

        List<User> result = userService.listUsers();
        for (User user : result) {
            System.out.println("Id: " + user.getId());
            System.out.println("FirstName: " + user.getFirstName());
            System.out.println("LastName: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());

            Car car = user.getCar();
            if (car != null) {
                System.out.println("Car model: " + car.getModel());
                System.out.println("Car series: " + car.getSeries());
            } else {
                System.out.println("Car is null");
            }

        }

        User userCar = userService.getUserByCar("BMW", 7);
        if (userCar != null) {
            System.out.println("User с BMW: " + userCar.getFirstName() + " " + userCar.getLastName());
        }

        context.close();
    }
}
