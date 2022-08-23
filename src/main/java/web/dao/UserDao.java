package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private List<User> users;

    public List<User> index() {
        return entityManager.createQuery("select  p from User p", User.class).getResultList();
    }

    public User show(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }


    public void save(User user) {
        entityManager.persist(user);
    }


    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setName(updatedUser.getName());
        entityManager.merge(updatedUser);
    }

    //@Transactional
    public void delete(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        User userToBeDeleted = show(id);
        entityManager.remove(userToBeDeleted);
    }
}
