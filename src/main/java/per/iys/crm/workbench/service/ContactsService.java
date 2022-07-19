package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Contacts;

import java.util.List;

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
}
