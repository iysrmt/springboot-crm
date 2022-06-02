package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;


public interface ActivityService {

    /**
     * 保存市场活动
     *
     * @param activity
     * @return
     */
    int saveCreateActivity(Activity activity);

    /**
     * 按条件查询页面的市场活动
     *
     * @param map
     * @return
     */
    List<Activity> queryActivityByConditionForPage(Map<String, Object> map);

    /**
     * 按条件查询市场活动计数
     *
     * @param map
     * @return
     */
    int queryCountOfActivityByCondition(Map<String, Object> map);

    /**
     * 按id删除市场活动
     *
     * @param ids 多个id
     * @return
     */
    int deleteActivityByIds(String[] ids);

    /**
     * 根据id来查询市场活动的信息.
     *
     * @param id 用户id
     * @return 实体类
     */
    Activity queryActivityById(String id);

    /**
     * 根据id更新市场活动信息
     *
     * @param activity 市场活动domain
     * @return 操作记录
     */
    int saveEditActivityById(Activity activity);

    /**
     * 查询所有市场活动
     *
     * @return 市场活动list集合
     */
    List<Activity> queryAllActivities();

    /**
     * 根据多个id查询市场活动
     *
     * @param ids 市场活动id数组
     * @return 市场活动list集合
     */
    List<Activity> queryActivitiesByIds(String[] ids);

    /**
     * 导入市场活动
     *
     * @param activityList 市场活动列表
     * @return 保存的记录条数
     */
    int saveCreateActivityByList(List<Activity> activityList);

    /**
     * 查询市场活动明细
     *
     * @param id 一个市场活动的id
     * @return 市场活动全部信息.
     */
    Activity queryActivityForDetailById(String id);

    /**
     * 根据线索id查询市场活动明细
     *
     * @param clueId 线索id
     * @return 市场活动
     */
    List<Activity> queryActivityForDetailByClueId(String clueId);

    /**
     * 根据name模糊查询市场活动, 并且把已经与clueId关联过的市场活动排除
     *
     * @param map key: activityName, clueId
     * @return 市场活动
     */
    List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map);

    /**
     * 根据id条数查询市场活动的明细信息
     *
     * @param ids
     * @return
     */
    List<Activity> queryActivityForDetailByIds(String[] ids);
}
