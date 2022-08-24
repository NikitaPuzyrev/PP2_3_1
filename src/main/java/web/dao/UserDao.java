package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao implements User_Dao {
    @PersistenceContext
    private EntityManager entityManager;
    private List<User> users;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select  p from User p", User.class).getResultList();
    }

    @Override
    public User showUserById(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        User userToBeUpdated = showUserById(id);
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setName(updatedUser.getName());
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        users = entityManager.createQuery("select  u from User u", User.class).getResultList();
        User userToBeDeleted = showUserById(id);
        entityManager.remove(userToBeDeleted);
    }
}
