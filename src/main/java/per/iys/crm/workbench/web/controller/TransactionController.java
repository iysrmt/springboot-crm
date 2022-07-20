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
import per.iys.crm.workbench.domain.*;
import per.iys.crm.workbench.service.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/workbench/transaction")
public class TransactionController {

    private TranService tranService;
    private UserService userService;
    private DicValueService dicValueService;
    private ActivityService activityService;
    private ContactsService contactsService;
    private CustomerService customerService;

    @Autowired
    public TransactionController(TranService tranService, UserService userService, DicValueService dicValueService, ActivityService activityService, ContactsService contactsService, CustomerService customerService) {
        this.tranService = tranService;
        this.userService = userService;
        this.dicValueService = dicValueService;
        this.activityService = activityService;
        this.contactsService = contactsService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 阶段
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        // 类型
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        // 来源
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");

        model.addAttribute("stageList", stageList);
        model.addAttribute("transactionTypeList", transactionTypeList);
        model.addAttribute("sourceList", sourceList);

        return "workbench/transaction/index";
    }

    // 创建交易页面
    @GetMapping("/save")
    public String save(Model model) {
        // 所有者
        List<User> userList = userService.queryAllUsers();
        // 阶段
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        // 类型
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");
        // 来源
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");

        model.addAttribute("userList", userList);
        model.addAttribute("stageList", stageList);
        model.addAttribute("transactionTypeList", transactionTypeList);
        model.addAttribute("sourceList", sourceList);
        return "workbench/transaction/save";
    }

    // 修改交易页面
    @GetMapping("/edit")
    public String edit(Model model, String id) {
        return "workbench/transaction/edit";
    }

    // 创建交易
    @ResponseBody
    @PostMapping("/saveCreateTran")
    public Object saveCreateTran(HttpSession session, Tran tran) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        // 二次封装
        tran.setId(UUIDUtils.getUUID());
        tran.setCreateBy(user.getId());
        tran.setCreateTime(DateUtils.format(new Date()));

        try {
            int ret = tranService.saveCreateTran(tran);
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

    // 模糊查询市场活动
    @ResponseBody
    @GetMapping("/queryActivityByName")
    public Object queryActivityByName(String name) {
        List<Activity> activityList = activityService.queryActivityByName(name);
        return activityList;
    }

    // 模糊查询联系人
    @ResponseBody
    @GetMapping("/queryContactsByFullName")
    public Object queryContactsByFullName(String fullName) {
        List<Contacts> contactsList = contactsService.queryContactsByFullName(fullName);
        return contactsList;
    }

    // 从配置文件中获取可能性
    @ResponseBody
    @GetMapping("/getPossibilityByStage")
    public Object getPossibilityByStage(String stage) {
        // 解析properties文件, 返回对应的可能性
        ResourceBundle bundle = ResourceBundle.getBundle("possibility");
        String possibility = bundle.getString(stage);
        return possibility;
    }

}
