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
import per.iys.crm.workbench.domain.Activity;
import per.iys.crm.workbench.domain.Clue;
import per.iys.crm.workbench.domain.ClueRemark;
import per.iys.crm.workbench.domain.DicValue;
import per.iys.crm.workbench.service.ActivityService;
import per.iys.crm.workbench.service.ClueRemarkService;
import per.iys.crm.workbench.service.ClueService;
import per.iys.crm.workbench.service.DicValueService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    private UserService userService;
    private DicValueService dicValueService;
    private ClueService clueService;
    private ClueRemarkService clueRemarkService;
    private ActivityService activityService;

    @Autowired
    public ClueController(UserService userService, DicValueService dicValueService, ClueService clueService, ClueRemarkService clueRemarkService, ActivityService activityService) {
        this.userService = userService;
        this.dicValueService = dicValueService;
        this.clueService = clueService;
        this.clueRemarkService = clueRemarkService;
        this.activityService = activityService;
    }

    // 跳转到线索主页
    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.queryAllUsers();
        List<DicValue> appellation = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueState = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");
        model.addAttribute("users", users);
        model.addAttribute("appellationList", appellation);
        model.addAttribute("clueStateList", clueState);
        model.addAttribute("sourceList", source);
        return "workbench/clue/index";
    }


    // 创建市场活动
    @PostMapping("/saveCreateClue")
    @ResponseBody
    public Object saveCreateClue(HttpSession session, Clue clue) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 二次封装
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.format(new Date()));
        clue.setCreateBy(user.getId());

        // 调用service方法
        try {
            int ret = clueService.saveCreateClue(clue);
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


    // 展示线索
    @PostMapping("/queryClueByConditionForPage")
    @ResponseBody
    public Object queryClueByConditionForPage(String fullName, String company, String phone, String source, String owner, String mPhone, String state, int pageNo, int pageSize) {
        // 封装参数
        Map<String, Object> map = new HashMap();
        map.put("fullName", fullName);
        map.put("company", company);
        map.put("phone", phone);
        map.put("source", source);
        map.put("owner", owner);
        map.put("mPhone", mPhone);
        map.put("state", state);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        // 调用service方法
        List<Clue> clueList = clueService.queryClueByConditionForPage(map);
        int totalRows = clueService.queryCountOfActivityByCondition(map);

        // 响应参数
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("clueList", clueList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }


    // 根据id获取指定线索
    @GetMapping("/queryClueById")
    @ResponseBody
    public Object queryClueById(String id) {
        return clueService.queryClueById(id);
    }

    // 修改线索
    @PutMapping("/saveEditClueById")
    @ResponseBody
    public Object saveEditClueById(HttpSession session, Clue clue) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        // 二次封装
        clue.setEditBy(user.getId());
        clue.setEditTime(DateUtils.format(new Date()));
        // 调用service方法
        try {
            int ret = clueService.saveEditClueById(clue);
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


    // 删除多条线索
    @DeleteMapping("/deleteClueByIds")
    @ResponseBody
    public Object deleteClueByIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueService.deleteClueByIds(id);
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

    // 跳转到线索详情页面
    @GetMapping("/detail")
    public String detail(Model model, String id) {
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> remarkList = clueRemarkService.queryClueRemarkByClueId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        model.addAttribute("clue", clue);
        model.addAttribute("remarkList", remarkList);
        model.addAttribute("activityList", activityList);
        return "workbench/clue/detail";
    }
}
