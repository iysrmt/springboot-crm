package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

@Mapper
public interface ClueActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    int insert(ClueActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    int insertSelective(ClueActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    ClueActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    int updateByPrimaryKeySelective(ClueActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_clue_activity_relation
     *
     * @mbg.generated Mon May 30 15:05:54 CST 2022
     */
    int updateByPrimaryKey(ClueActivityRelation row);

    /**
     * 批量保存创建的线索和市场活动的关联关系
     *
     * @param list
     * @return
     */
    int insertClueActivityRelationByList(List<ClueActivityRelation> list);

    /**
     * 根据clueId activityId删除线索和市场活动的关系.
     *
     * @param car
     * @return
     */
    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation car);

    /**
     * 根据clueId查询线索和市场活动的关联关系
     *
     * @return
     */
    List<ClueActivityRelation> selectClueActivityRelationByClueId(String clueId);

    /**
     * 根据clueId删除线索和市场活动关联关系
     *
     * @param clueId
     * @return
     */
    int deleteClueActivityRelationByClueId(String clueId);
}