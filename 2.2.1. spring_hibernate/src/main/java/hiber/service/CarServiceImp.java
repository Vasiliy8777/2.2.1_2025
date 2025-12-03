package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    String findHql =
            "SELECT car.user FROM Car car " +
                    "WHERE car.model = :model AND car.series = :series";

    private final CarDao carDao;

    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void addCar(Car car) {
        carDao.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUser(String model, int series) {
        return carDao.findUser(model, series);
    }

}
