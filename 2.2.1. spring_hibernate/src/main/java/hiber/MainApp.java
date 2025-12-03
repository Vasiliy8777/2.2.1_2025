package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


        carService.addCar(new Car("BMW", 5));
        carService.addCar(new Car("BMW", 7));

        User user5 = new User("User5", "Lastname5", "user5@mail.ru");
        User user6 = new User("User6", "Lastname6", "user6@mail.ru");

        Car car1 = new Car("BMW", 3);
        Car car2 = new Car("BMW", 4);

        user5.setCar(car1);
        user6.setCar(car2);

        userService.add(user5);
        userService.add(user6);

        User us = carService.findUser("BMW", 4);
        System.out.println("Результат поиска:");
        System.out.println("Id = " + us.getId());
        System.out.println("First Name = " + us.getFirstName());
        System.out.println("Last Name = " + us.getLastName());
        System.out.println("Email = " + us.getEmail());
        System.out.println();


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
