package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {

    /**
     * 批量保存创建的线索和市场活动的关联关系
     *
     * @param list
     * @return
     */
    int saveClueActivityRelationByList(List<ClueActivityRelation> list);

    /**
     * 根据clueId activityId删除线索和市场活动的关系.
     *
     * @param car
     * @return
     */
    int removeClueActivityRelationByClueIdActivityId(ClueActivityRelation car);
}
