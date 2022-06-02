package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {

    /**
     * 保存创建的线索
     *
     * @param clue 线索实体类
     * @return 操作成功的记录条数: 1表示成功, 0表示失败.
     */
    int saveCreateClue(Clue clue);

    /**
     * 根据条件分页查询线索信息
     *
     * @param conditionsAndPages map集合, key: fullName,company,phone,source,owner,mPhone,state,beginNo,pageSize. 其中beginNo,pageSize必须有.
     * @return 线索集合
     */
    List<Clue> queryClueByConditionForPage(Map<String, Object> conditionsAndPages);

    /**
     * 根据条件分页查询线索信息条数
     *
     * @param conditions map集合, key: fullName,company,phone,source,owner,mPhone,state. 可为空
     * @return 总记录条数
     */
    int queryCountOfActivityByCondition(Map<String, Object> conditions);

    /**
     * 根据id获取指定线索
     *
     * @param id id
     * @return 线索
     */
    Clue queryClueById(String id);

    /**
     * 根据id更新线索
     *
     * @param clue 线索实体类
     * @return 成功更新的条目, 1表示成功, 0表示失败.
     */
    int saveEditClueById(Clue clue);

    /**
     * 根据多个id删除线索
     *
     * @param ids string数组
     * @return 成功删除的条目
     */
    int deleteClueByIds(String[] ids);

    /**
     * 根据id获取线索, 连接信息.
     *
     * @param id 线索id
     * @return 线索
     */
    Clue queryClueForDetailById(String id);
}
