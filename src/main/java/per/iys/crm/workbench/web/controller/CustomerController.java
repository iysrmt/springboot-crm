package per.iys.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.settings.service.UserService;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.service.CustomerService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/customer")
public class CustomerController {

    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> userList = userService.queryAllUsers();
        model.addAttribute("userList", userList);
        return "workbench/customer/index";
    }

    @ResponseBody
    @PostMapping("/saveCreateCustomer")
    public Object saveCreateCustomer(Customer customer, HttpSession session) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 二次封装
        customer.setId(UUIDUtils.getUUID());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.format(new Date()));

        try {
            // 调用service
            int ret = customerService.createCustomer(customer);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
            } else {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
                returnObject.setMessage("系统繁忙, 请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_FAIL);
            returnObject.setMessage("系统繁忙, 请稍后重试...");
        }

        return returnObject;
    }

    @ResponseBody
    @GetMapping("/queryCustomerByConditionForPage")
    public Object queryCustomerByConditionForPage(Customer customer, int pageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", customer);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Customer> customerList = customerService.queryCustomerByConditionForPage(map);
        int totalRows = customerService.queryCountOfCustomerByCondition(map);

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("customerList", customerList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }
}
