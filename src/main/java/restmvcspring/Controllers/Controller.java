package restmvcspring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restmvcspring.Models.Contacts;
import restmvcspring.Service.Impl.ServiceImpl;

import java.util.concurrent.Callable;

@org.springframework.stereotype.Controller
@RequestMapping("/contacts")
public class Controller {
    private ServiceImpl service;
    @Autowired
    public Controller(ServiceImpl serviceImpl, JdbcTemplate jdbcTemplate){
        this.service = serviceImpl;

    }
    @GetMapping()
    public String showContacts(Model model){
        model.addAttribute("myContactsShow",Contacts.class);
        return "contacts/info";
    }
    @GetMapping("{id}")
    public String showById(Model model,@PathVariable("id") int id){
        model.addAttribute("showById",Contacts.class);
        return "contacts/id";

    }
    @PutMapping("create")
    public String create(@ModelAttribute("contacts") Contacts contacts) {
        Contacts contacts1 = new Contacts();
        service.create(contacts);
        return "redirect/newContact";

    }
    @PostMapping("{id}")
    public String update(@ModelAttribute("contacts") Contacts contacts,@PathVariable("id")int id){
        service.update(contacts);
        return "redirect/updatedContact";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")int id,@ModelAttribute("contacts")Contacts contacts){
        service.delete(id);
        return "contacts";
    }



}
