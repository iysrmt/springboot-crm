package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.Contacts;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    int insert(Contacts row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    int insertSelective(Contacts row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    int updateByPrimaryKeySelective(Contacts row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbg.generated Fri Jun 17 17:34:44 CST 2022
     */
    int updateByPrimaryKey(Contacts row);

    /**
     * 保存一条联系人信息
     *
     * @param contacts
     * @return
     */
    int insertContacts(Contacts contacts);

    /**
     * 根据客户id查询联系人
     *
     * @param customerId
     * @return
     */
    List<Contacts> selectContactsByCustomerId(String customerId);

    /**
     * 根据联系人名称模糊查找联系人
     *
     * @param fullName
     * @return
     */
    List<Contacts> selectContactsByFullName(String fullName);

    /**
     * 根据条件分页展示联系人
     *
     * @param map key: contacts, beginNo, pageSize
     * @return
     */
    List<Contacts> selectContactsByConditionPaging(Map<String, Object> map);

    /**
     * 查询符合条件的联系人总条数
     *
     * @param contacts
     * @return
     */
    int selectContactsByConditionCount(Contacts contacts);

    /**
     * 根据联系人id查询联系人
     *
     * @param id
     * @return
     */
    Contacts selectContactsById(String id);

    /**
     * 根据id更新联系人信息
     *
     * @param contacts
     * @return
     */
    int updateContactsById(Contacts contacts);

    /**
     * 根据id删除联系人
     * @param ids
     * @return
     */
    int deleteContactsById(String[] ids);
}