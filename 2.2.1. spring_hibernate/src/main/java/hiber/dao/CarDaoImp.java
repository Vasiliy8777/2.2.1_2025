package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

    private String findHql =
            "SELECT car.user FROM Car car " +
                    "WHERE car.model = :model AND car.series = :series";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findUser(String model, int series) {
        return sessionFactory.getCurrentSession().createQuery(findHql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }
}
