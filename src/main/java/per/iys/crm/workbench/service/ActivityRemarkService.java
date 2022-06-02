package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {

    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    int saveCreateActivityRemark(ActivityRemark activityRemark);

    int removeActivityRemarkById(String id);

    int saveEditActivityRemark(ActivityRemark activityRemark);
}
