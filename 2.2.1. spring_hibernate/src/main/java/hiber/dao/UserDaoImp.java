package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
    public User getUserByCar(String model, int series) {
       TypedQuery<User> query =  sessionFactory.getCurrentSession().createQuery("from User u where u.car.model = :model and u.car.series = :series",
               User.class);
       query.setParameter("model", model);
       query.setParameter("series", series);

       return query.getSingleResult();
   }
   @Override
    public void addCar(Car car) {
       sessionFactory.getCurrentSession().save(car);
   }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory
                .getCurrentSession()
                .createQuery("from Car", Car.class);
        return query.getResultList();
    }

   @Override
    public void updateUsers(List<User> users) {
       for(User user:users){
           sessionFactory.getCurrentSession().update(user);
       }
   }

}
