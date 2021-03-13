package troshkoilya;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import troshkoilya.model.*;
import troshkoilya.service.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoApplicationTests {

    @Autowired
    private MasterService masterService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @Autowired
    private ServService servService;

    @Test
    void contextLoads() {
        Master master = new Master("Трошко В. В.", "1980-07-06", 3, 1, 120.0, "2016");
        List<Master> iterable = masterService.loadMasterByCategory(3);
        System.out.println(iterable.get(0));
        List<Master> list = new ArrayList<>();
        list.add(master);

        Assert.assertEquals(iterable, list);
    }

    @Test
    public void getAllUsersNull() {//
        List<User> list = (List<User>) userService.loadAllUsers();
        list = null;
        Assert.assertNotNull(list);
        for (User user : userService.loadAllUsers()) {
            System.out.println("User id = " + user.getId() + " fio = " + user.getFio() + " username = " + user.getUsername() + " password = " + user.getPassword() + " phone = " + user.getPhone() + " role = " + user.getRoles());
        }
    }

    @Test
    public void getAllUsersNotNull()  {//
        List<User> list = (List<User>) userService.loadAllUsers();
        Assert.assertNotNull(list);
        for (User user : userService.loadAllUsers()) {
            System.out.println("User id = " + user.getId() + " fio = " + user.getFio() + " username = " + user.getUsername() + " password = " + user.getPassword() + " phone = " + user.getPhone() + " role = " + user.getRoles());
        }
    }

    @Test
    public void testDeleteUserCorrect() {//
        User user = userService.loadUserById(158);
        System.out.println("Delete successful");
        Assert.assertTrue(userService.deleteUser(user));
    }

    @Test
    public void testDeleteUserNotCorrect() {//
        User user = userService.loadUserById(344);
        Assert.assertTrue(userService.deleteUser(user));
    }

    @Test
    public void testSaveOrdersCorrect() {
        User user = userService.loadUserById(1);
        Master master = new Master();
        master.setIdmaster(117L);
        Iterable<Car> cars = carService.loadAllUserCars(1);
        Car car = new Car();
        for (Car t : cars) {
            if (t.getCarname().equals("BMW M5")) {
                car = t;
            }
        }
        Order order = new Order("16:00 04/18/2021", "18:00 04/18/2021", 230, user, master, "Обрабатывается", car);
        System.out.println("Save order successful");
        Assert.assertEquals(order, orderService.saveOrders(order));
    }

    @Test
    public void testAddServiceOrdersNotCorrect() {//
        User user = userService.loadUserById(1);
        Master master = new Master();
        master.setIdmaster(null);
        Iterable<Car> cars = carService.loadAllUserCars(1);
        Car car = new Car();
        for (Car t : cars) {
            if (t.getCarname().equals("VW Tiguan")) {
                car = t;
            }
        }
        Service currService = servService.loadServiceByname("Полировка");

        Order order = new Order("09:30 03/10/2021", "11:00 03/10/2021", 200, user, master, "Обрабатывается", car);
        orderService.addServices(order, currService);
        System.out.println("Id мастера равен null");
    }

    @Test
    public void testAddServiceOrdersCorrect() {//
        User user = userService.loadUserById(1);
        Master master = new Master();
        master.setIdmaster(116L);
        Iterable<Car> cars = carService.loadAllUserCars(1);
        Car car = new Car();
        for (Car t : cars) {
            if (t.getCarname().equals("VW Tiguan")) {
                car = t;
            }
        }
        Service currService = servService.loadServiceByname("Полировка");
        Order order = new Order("09:30 03/10/2021", "11:00 03/10/2021", 200, user, master, "Обрабатывается", car);
        System.out.println("Add service-order successful");
        orderService.addServices(order, currService);
    }

    @Test
    public void testAuthUserNotCorrect() {//
        User user = new User();
        user.setUsername("testUser");
        Assert.assertEquals("testUser", userService.loadUserByUsername("testUser"));
    }

    @Test
    public void testAuthUserCorrect() {//
        User user = new User();
        user.setUsername("testUser");
        System.out.println("The authorized user is available in the system");
        Assert.assertEquals("troshko", userService.loadUserByUsername("troshko").getUsername());
    }
}
