package troshkoilya.controller;

import troshkoilya.model.*;
import troshkoilya.repos.UserRepo;
import troshkoilya.service.CarService;
import troshkoilya.service.OrderService;
import troshkoilya.service.ServService;
import troshkoilya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServService servService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/registrationUserInfo")
    public String registrationUserInfo() {
        return "registrationUserInfo";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addInfoAboutUser(User User, Map<String, Object> model) {

        User UserFromDB = userRepo.findByUsername(User.getUsername());

        if (UserFromDB != null) {
            model.put("message", "User with number or email already exist!" + User.getFio());
            return "registrationUserInfo";
        }
        User.setActive(true);
        User.setRoles(Collections.singleton(Role.USER));
        userRepo.save(User);
        return "redirect:/login";
    }

    @GetMapping("/registerOnService")
    public String registrationOnService(@RequestParam("id_user") Long id, Map<String, Object> model) {

        System.out.println("USER = " + id);
        Iterable<Car> cars = carService.loadAllUserCars(id);
        model.put("cars", cars);
        Iterable<Service> services = servService.loadAllServices();
        model.put("flag", false);
        model.put("services", services);


        return "services";
    }

    private ArrayList<String[]> viewInfo = new ArrayList<>();
    private String result = "";
    private int res = 0;
    ArrayList<String> servs = new ArrayList<>();
    int sum = 0;
    boolean flag = true;
    Order order = new Order();

    @PostMapping("/registerOnService")
    public String addServiceInOrder(@RequestParam("id_user") int id,
                                    @RequestParam("choiceCar") String carName,
                                    @RequestParam("choiceService") String serviceName,
                                    @RequestParam("datatimestart") String datetimestart,
                                    Map<String, Object> model) {

        User user = userService.loadUserById(id);
        Service service = servService.loadServiceByname(serviceName);
        Iterable<Car> cars = carService.loadAllUserCars((long) user.getId());
        Car car = new Car();
        for (Car t : cars){
            if (t.getCarname().equals(carName)){
                car = t;
            }
        }

        model.put("cars", cars);
        Iterable<Service> services = servService.loadAllServices();
        model.put("flag", false);
        model.put("services", services);
        if (carName != null) {
            String[] tmp = new String[4];
            tmp[0] = carName;
            tmp[1] = serviceName;
            servs.add(serviceName);
            tmp[2] = datetimestart;
            tmp[3] = service.getCost();
            viewInfo.add(tmp);

            res += Integer.parseInt(tmp[3]);

            result = String.valueOf(res);
            sum = res;
            model.put("sum", result);
            model.put("OrderLines", viewInfo);
            model.put("flag", true);
        }

        if (flag) {
            order = new Order(datetimestart, "", sum, user, null, "Обрабатывается", car);
            orderService.saveOrders(order);
            flag = false;
        }

        Service currService = servService.loadServiceByname(serviceName);
        orderService.addServices(order , currService);

        return "services";
    }
}
