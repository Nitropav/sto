package troshkoilya;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import troshkoilya.model.Master;
import troshkoilya.model.User;
import troshkoilya.service.MasterService;
import troshkoilya.service.UserService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoApplicationTests {

    @Autowired
    private MasterService masterService;

    @Autowired
    private UserService userService;

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
    public void getAllUsers() {
        List<User> list = (List<User>) userService.loadAllUsers();
        Assert.assertNotNull(list);
        for (User user : userService.loadAllUsers()) {
            System.out.println("User id = " + user.getId() + " fio = " + user.getFio() + " username = " + user.getUsername() + " password = " + user.getPassword() + " phone = " + user.getPhone() + " role = " + user.getRoles());
        }
    }

//    @Test
//    public void testDeleteUser() {
//        User user = userService.loadUserById(150);
//        Assert.(userService.deleteUser(user));
//    }
}
