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
import per.iys.crm.workbench.domain.Contacts;
import per.iys.crm.workbench.domain.DicValue;
import per.iys.crm.workbench.service.ContactsService;
import per.iys.crm.workbench.service.CustomerService;
import per.iys.crm.workbench.service.DicValueService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/contacts")
public class ContactsController {

    private ContactsService contactsService;
    private UserService userService;
    private DicValueService dicValueService;
    private CustomerService customerService;

    @Autowired
    public ContactsController(ContactsService contactsService, UserService userService, DicValueService dicValueService, CustomerService customerService) {
        this.contactsService = contactsService;
        this.userService = userService;
        this.dicValueService = dicValueService;
        this.customerService = customerService;
    }

    // 跳转到联系人主页
    @GetMapping("/")
    public String index(Model model) {
        List<User> userList = userService.queryAllUsers();
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");

        model.addAttribute("userList", userList);
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("appellationList", appellationList);
        return "workbench/contacts/index";
    }

    @ResponseBody
    @PostMapping("/saveCreateContacts")
    public Object saveCreateContacts(HttpSession session, Contacts contacts) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();

        // 二次封装
        contacts.setId(UUIDUtils.getUUID());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.format(new Date()));

        try {
            int ret = contactsService.saveCreateContacts(contacts);
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

    // 联系人展示
    @ResponseBody
    @GetMapping("/queryContactsByConditionPaging")
    public Object queryContactsByConditionPaging(Contacts contacts, int pageNo, int pageSize) {
        // 封装参数
        pageNo = (pageNo - 1) * pageSize;
        Map map = new HashMap();
        map.put("contacts", contacts);
        map.put("beginNo", pageNo);
        map.put("pageSize", pageSize);

        List<Contacts> contactsList = contactsService.queryContactsByConditionPaging(map);
        int totalRows = contactsService.queryContactsByConditionCount(contacts);

        // 封装
        Map retMap = new HashMap();
        retMap.put("contactsList", contactsList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }
}
