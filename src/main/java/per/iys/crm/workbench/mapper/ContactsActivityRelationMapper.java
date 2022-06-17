package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.ContactsActivityRelation;

@Mapper
public interface ContactsActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int insert(ContactsActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int insertSelective(ContactsActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    ContactsActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int updateByPrimaryKeySelective(ContactsActivityRelation row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int updateByPrimaryKey(ContactsActivityRelation row);
}