package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Contacts;

import java.util.List;
import java.util.Map;

public interface ContactsService {

    /**
     * 根据客户id查询联系人
     *
     * @param customerId
     * @return
     */
    List<Contacts> queryContactsByCustomerId(String customerId);

    /**
     * 保存创建的联系人
     *
     * @param contacts
     * @return
     */
    int saveCreateContacts(Contacts contacts);

    /**
     * 根据联系人名称, 模糊查询联系人
     *
     * @param fullName
     * @return
     */
    List<Contacts> queryContactsByFullName(String fullName);

    /**
     * 根据条件分页展示联系人
     *
     * @param map key: contacts, beginNo, pageSize
     * @return
     */
    List<Contacts> queryContactsByConditionPaging(Map<String, Object> map);

    /**
     * 查询符合条件的联系人总条数
     *
     * @param contacts
     * @return
     */
    int queryContactsByConditionCount(Contacts contacts);

    /**
     * 根据联系人id查询联系人
     *
     * @param id
     * @return
     */
    Contacts queryContactsById(String id);

    /**
     * 根据联系人id更新信息
     *
     * @param contacts
     * @return
     */
    int modifyContactsById(Contacts contacts);

    /**
     * 根据多条id删除联系人
     *
     * @param ids
     * @return
     */
    int removeContactsById(String[] ids);
}
