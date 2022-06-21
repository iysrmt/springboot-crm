package per.iys.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.settings.service.UserService;
import per.iys.crm.workbench.domain.Contacts;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.domain.CustomerRemark;
import per.iys.crm.workbench.domain.Tran;
import per.iys.crm.workbench.service.ContactsService;
import per.iys.crm.workbench.service.CustomerRemarkService;
import per.iys.crm.workbench.service.CustomerService;
import per.iys.crm.workbench.service.TranService;

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
    private CustomerRemarkService customerRemarkService;
    private ContactsService contactsService;
    private TranService tranService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, CustomerRemarkService customerRemarkService, ContactsService contactsService, TranService tranService) {
        this.customerService = customerService;
        this.userService = userService;
        this.customerRemarkService = customerRemarkService;
        this.contactsService = contactsService;
        this.tranService = tranService;
    }

    // 客户主页
    @GetMapping("/")
    public String index(Model model) {
        List<User> userList = userService.queryAllUsers();
        model.addAttribute("userList", userList);
        return "workbench/customer/index";
    }

    // 创建客户
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

    // 分页查询显示客户
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

    // 删除客户
    @ResponseBody
    @DeleteMapping("/removeCustomerByIds")
    public Object removeCustomerByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = customerService.removeCustomerByIds(id);
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

    // 查询客户信息
    @ResponseBody
    @GetMapping("/queryCustomerById")
    public Object queryCustomerById(String id) {
        return customerService.queryCustomerById(id);
    }

    // 更新客户信息
    @ResponseBody
    @PutMapping("/modifyCustomerById")
    public Object modifyCustomerById(Customer customer, HttpSession session) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        // 二次封装
        customer.setEditBy(user.getId());
        customer.setEditTime(DateUtils.format(new Date()));

        try {
            int ret = customerService.modifyCustomerById(customer);
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

    // 跳转到备注页
    @GetMapping("/detail")
    public String detail(Model model, String id) {
        // 客户信息
        Customer customer = customerService.queryCustomerByIdForDetail(id);
        // 客户备注
        List<CustomerRemark> customerRemarkList = customerRemarkService.queryCustomerRemarkByCustomerId(id);
        // 交易
        List<Tran> tranList = tranService.queryTranByCustomerId(id);
        // 联系人
        List<Contacts> contactsList = contactsService.queryContactsByCustomerId(id);

        model.addAttribute("customer", customer);
        model.addAttribute("customerRemarkList", customerRemarkList);
        model.addAttribute("tranList", tranList);
        model.addAttribute("contactsList", contactsList);

        return "workbench/customer/detail";
    }

}
