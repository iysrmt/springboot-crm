package per.iys.crm.workbench.mapper;

import per.iys.crm.workbench.domain.ContactsRemark;

public interface ContactsRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int insert(ContactsRemark row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int insertSelective(ContactsRemark row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    ContactsRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int updateByPrimaryKeySelective(ContactsRemark row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbg.generated Fri Jun 17 19:56:34 CST 2022
     */
    int updateByPrimaryKey(ContactsRemark row);
}