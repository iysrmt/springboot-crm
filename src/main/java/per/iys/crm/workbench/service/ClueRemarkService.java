package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {

    /**
     * 根据线索查询备注
     *
     * @param clueId 线索id
     * @return 线索备注
     */
    List<ClueRemark> queryClueRemarkByClueId(String clueId);

    /**
     * 插入一条线索备注
     *
     * @param clueRemark 线索备注实体类
     * @return 成功操作记录条数, 1表示成功, 0表示失败.
     */
    int saveCreateClueRemark(ClueRemark clueRemark);

    /**
     * 删除一条线索备注
     *
     * @param id 线索备注id
     * @return 成功操作记录条数, 1表示成功, 0表示失败.
     */
    int removeClueRemarkById(String id);

    /**
     * 更新一条线索备注
     *
     * @param clueRemark 线索备注实体类
     * @return 成功操作记录条数, 1表示成功, 0表示失败
     */
    int modifyClueRemarkById(ClueRemark clueRemark);
}
