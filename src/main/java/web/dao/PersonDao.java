package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;
    private List<Person> people;

    public List<Person> index() {
        return entityManager.createQuery("select  p from Person p", Person.class).getResultList();
    }

    public Person show(int id) {
        people = entityManager.createQuery("select  p from Person p", Person.class).getResultList();
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setName(updatedPerson.getName());
        entityManager.merge(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        people = entityManager.createQuery("select  p from Person p", Person.class).getResultList();
        Person personToBeDeleted = show(id);
        entityManager.remove(personToBeDeleted);
    }
}
