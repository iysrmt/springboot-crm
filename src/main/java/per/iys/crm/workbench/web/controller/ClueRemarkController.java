package per.iys.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.workbench.domain.Activity;
import per.iys.crm.workbench.domain.ClueActivityRelation;
import per.iys.crm.workbench.domain.ClueRemark;
import per.iys.crm.workbench.service.ActivityService;
import per.iys.crm.workbench.service.ClueActivityRelationService;
import per.iys.crm.workbench.service.ClueRemarkService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/workbench/clue/remark")
public class ClueRemarkController {

    private ClueRemarkService clueRemarkService;
    private ActivityService activityService;
    private ClueActivityRelationService clueActivityRelationService;

    @Autowired
    public ClueRemarkController(ClueRemarkService clueRemarkService, ActivityService activityService, ClueActivityRelationService clueActivityRelationService) {
        this.clueRemarkService = clueRemarkService;
        this.activityService = activityService;
        this.clueActivityRelationService = clueActivityRelationService;
    }


    // 添加备注
    @PostMapping("/")
    @ResponseBody
    public Object saveCreateClueRemark(HttpSession session, ClueRemark clueRemark) {
        ReturnObject returnObject = new ReturnObject();

        // 二次封装参数
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setCreateBy(user.getId());
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);
        clueRemark.setCreateTime(DateUtils.format(new Date()));

        try {
            int ret = clueRemarkService.saveCreateClueRemark(clueRemark);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
                returnObject.setRetData(clueRemark);
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


    // 删除备注
    @DeleteMapping("/")
    @ResponseBody
    public Object removeClueRemarkById(String id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueRemarkService.removeClueRemarkById(id);
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

    // 修改备注
    @PutMapping("/")
    @ResponseBody
    public Object modifyClueRemarkById(HttpSession session, ClueRemark clueRemark) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 二次封装
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);
        clueRemark.setEditTime(DateUtils.format(new Date()));
        clueRemark.setEditBy(user.getId());

        // 调用service层方法
        try {
            int ret = clueRemarkService.modifyClueRemarkById(clueRemark);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
                returnObject.setRetData(clueRemark);
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

    // 展示数据到关联市场活动
    @GetMapping("/AssociatedActivities")
    @ResponseBody
    public Object queryActivityForDetailByNameClueId(String activityName, String clueId) {
        // 封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("activityName", activityName);
        map.put("clueId", clueId);
        // 查询结果
        List<Activity> activityList = activityService.queryActivityForDetailByNameClueId(map);
        return activityList;
    }

    // 关联市场活动
    @ResponseBody
    @PostMapping("/AssociatedActivities")
    public Object saveBind(String[] activityId, String clueId) {
        ReturnObject returnObject = new ReturnObject();
        List<ClueActivityRelation> carList = new ArrayList<>();
        ClueActivityRelation car = null;
        for (String id : activityId) {
            car = new ClueActivityRelation();
            car.setId(UUIDUtils.getUUID());
            car.setClueId(clueId);
            car.setActivityId(id);
            carList.add(car);
        }
        // 调用service方法, 批量保存线索和市场活动的关联关系.
        try {
            int ret = clueActivityRelationService.saveClueActivityRelationByList(carList);
            if (ret > 0) {
                List<Activity> activityList = activityService.queryActivityForDetailByIds(activityId);
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
                returnObject.setRetData(activityList);
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
    @DeleteMapping("/AssociatedActivities")
    public Object unBind(ClueActivityRelation clueActivityRelation) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueActivityRelationService.removeClueActivityRelationByClueIdActivityId(clueActivityRelation);
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

    @GetMapping("/convert")
    public String convert(Module module) {

        return "workbench/clue/convert";
    }
}
