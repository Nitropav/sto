package troshkoilya.controller;

import troshkoilya.model.Role;
import troshkoilya.model.User;
import troshkoilya.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/createuser")
@PreAuthorize("hasAuthority('ADMIN')")
public class CreateUserController {

    @Autowired
    private CreateUserService createUserService;

    @GetMapping
    public String userList(Map<String, Object> model) {
        Iterable<User> users = createUserService.loadAllUsers();
        model.put("users", users);

        return "createuser";
    }

    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String fio,
                          @RequestParam String phone, @RequestParam String email, @RequestParam boolean active,
                          @RequestParam Set<Role> roles, Map<String, Object> model) {
        User user = new User(username, password, fio, password, email, active, roles);

        createUserService.saveUsers(user);

        Iterable<User> users = createUserService.loadAllUsers();
        model.put("users", users);

        return "createuser";
    }

    @PostMapping("/filter")
    public String filterUser(@RequestParam("filter") String filter, Map<String, Object> model) {
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()) {
            users = createUserService.loadUserByLogin(filter);
        } else {
            users = createUserService.loadAllUsers();
        }

        model.put("users", users);

        return "createuser";
    }

    @PostMapping("deleteUser")
    public String deleteEvent(@RequestParam("userId") User user, Map<String, Object> model){
        createUserService.deleteUser(user);

        Iterable<User> users = createUserService.loadAllUsers();
        model.put("users", users);

        return "createuser";
    }

    @GetMapping("/{user}")
    public String editUser(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "editCreateUser";
    }

    @PostMapping("/show")
    public String edit(@RequestParam String username, @RequestParam String password, @RequestParam String fio,
                       @RequestParam String phone, @RequestParam String email,
                       @RequestParam("id") User user){

        user.setUsername(username);
        user.setPassword(password);
        user.setFio(fio);
        user.setPhone(phone);
        user.setEmail(email);
        createUserService.saveUsers(user);

        return "redirect:/createuser";
    }
}
