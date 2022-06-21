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
}
