package per.iys.crm.workbench.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.domain.ReturnObject;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.workbench.domain.ActivityRemark;
import per.iys.crm.workbench.service.ActivityRemarkService;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityRemarkController {

    private ActivityRemarkService activityRemarkService;

    @Autowired
    public ActivityRemarkController(ActivityRemarkService activityRemarkService) {
        this.activityRemarkService = activityRemarkService;
    }

    // 保存 创建的 市场活动备注
    @PostMapping("/saveCreateActivityRemark")
    @ResponseBody
    public Object saveCreateActivityRemark(HttpSession session, ActivityRemark activityRemark) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 二次封装
        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setCreateTime(DateUtils.format(new Date()));
        activityRemark.setCreateBy(user.getId());
        activityRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);
        // 调用service方法, 保存市场活动备注
        try {
            int ret = activityRemarkService.saveCreateActivityRemark(activityRemark);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
                returnObject.setRetData(activityRemark);
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

    // 删除 市场活动备注
    @DeleteMapping("/removeActivityRemarkById")
    @ResponseBody
    public Object removeActivityRemarkById(String id) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityRemarkService.removeActivityRemarkById(id);
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

    // 修改市场活动备注
    @PutMapping("/saveEditActivityRemark")
    @ResponseBody
    public Object saveEditActivityRemark(HttpSession session, ActivityRemark activityRemark) {
        ReturnObject returnObject = new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 封装参数
        activityRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);
        activityRemark.setEditTime(DateUtils.format(new Date()));
        activityRemark.setEditBy(user.getId());
        try {
            int ret = activityRemarkService.saveEditActivityRemark(activityRemark);
            if (ret > 0) {
                returnObject.setStatus(Constants.RETURN_OBJECT_STATUS_SUCCESS);
                returnObject.setRetData(activityRemark);
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
}
