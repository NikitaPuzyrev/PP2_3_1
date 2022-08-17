package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.PersonDao;
import web.model.Person;

@Controller
//@RequestMapping(value = "/")
public class UserController {
    private PersonDao personDao;

    public UserController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "users/listUsers";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "users/show";
    }

    @GetMapping("users/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "users/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("person") Person person) {
        personDao.save(person);
        return "redirect:/";
    }
@GetMapping("users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.show(id));
        return "users/edit";
}
@PatchMapping("users/{id}")
    public String update(@ModelAttribute("person") Person person,@PathVariable("id") int id){
        personDao.update(id, person);
        return "redirect:/";
}
@DeleteMapping("users/{id}")
public String delete(@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/";
}
}