package troshkoilya.controller;

import troshkoilya.model.Master;
import troshkoilya.model.Order;
import troshkoilya.model.User;
import troshkoilya.service.MasterService;
import troshkoilya.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('ADMIN')")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private MasterService masterService;

    @GetMapping
    public String orderList(Map<String, Object> model) {
        Iterable<Order> orders = orderService.loadAllOrders();

        model.put("orders", orders);

        return "order";
    }

    @PostMapping
    public String addMaster(@RequestParam String timestart, @RequestParam String timefinish, @RequestParam int amount,
                            @RequestParam User id, @RequestParam Master idmaster,
                            @RequestParam String status, Map<String, Object> model) {
        Order order = new Order(timestart, timefinish, amount, id, idmaster, status);

        orderService.saveOrders(order);

        Iterable<Order> orders = orderService.loadAllOrders();
        model.put("orders", orders);

        return "order";
    }

    @PostMapping("/filter")
    public String filterOrder(@RequestParam("filter") String filter, Map<String, Object> model) {
        Iterable<Order> orders;
        if (filter != null && !filter.isEmpty()) {
            orders = orderService.loadOrderByStatus(filter);
        } else {
            orders = orderService.loadAllOrders();
        }

        model.put("orders", orders);

        return "order";
    }

    @GetMapping("/{orders}")
    public String editOrder(@PathVariable("orders") int orders, Model model) {
        Order order1 = orderService.loadOrder(orders);
        Iterable<Master> masters = masterService.loadAllMasters();
        model.addAttribute("masters", masters);

        model.addAttribute("orders", order1);

        return "editOrder";
    }

    @PostMapping("/show")
    public String edit(@RequestParam("idorder") Long idorder, @RequestParam String timefinish,
                       @RequestParam int amount, @RequestParam Master idmaster,
                       @RequestParam String status) {
        Order orders = orderService.loadOrder(Math.toIntExact(idorder));

        if (timefinish != null) {
            Master master = masterService.loadMaster(idmaster.getIdmaster());
            double salary = master.getSalary();
            salary += amount * 0.1 * master.getCategory();
            master.setSalary(salary);
            masterService.saveMasters(master);
        }

        orders.setTimefinish(timefinish);
        orders.setAmount(amount);
        orders.setIdmaster(idmaster);
        orders.setStatus(status);
        orderService.saveOrders(orders);

        return "redirect:/order";
    }

    @GetMapping("/o/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerkey = "Content-Disposition";
        String headerValue = "attachement; filename=orders.xls";

        response.setHeader(headerkey, headerValue);

        List<Order> list = (List<Order>) orderService.loadAllOrders();

        UserExcelExporter excelExporter = new UserExcelExporter(list);
        excelExporter.export(response);
    }
}
