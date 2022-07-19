package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.Contacts;

import java.util.List;

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
}