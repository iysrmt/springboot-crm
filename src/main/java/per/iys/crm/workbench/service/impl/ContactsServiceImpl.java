package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.workbench.domain.Contacts;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.mapper.ContactsMapper;
import per.iys.crm.workbench.mapper.CustomerMapper;
import per.iys.crm.workbench.service.ContactsService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsMapper contactsMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public ContactsServiceImpl(ContactsMapper contactsMapper, CustomerMapper customerMapper) {
        this.contactsMapper = contactsMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<Contacts> queryContactsByCustomerId(String customerId) {
        return contactsMapper.selectContactsByCustomerId(customerId);
    }

    @Transactional
    @Override
    public int saveCreateContacts(Contacts contacts) {
        Customer customer = customerMapper.selectCustomerByName(contacts.getCustomerId());
        if (customer == null) {
            // 当客户不存在时, 创建客户
            customer = new Customer();
            customer.setId(UUIDUtils.getUUID());
            customer.setName(contacts.getCustomerId());
            customer.setCreateTime(DateUtils.format(new Date()));
            customer.setCreateBy(contacts.getCreateBy());
            customer.setOwner(contacts.getOwner());
            contacts.setCustomerId(customer.getId());
            customerMapper.insertCustomer(customer);
        } else {
            // 客户存在时
            contacts.setCustomerId(customer.getId());
        }
        return contactsMapper.insertContacts(contacts);
    }

    @Override
    public List<Contacts> queryContactsByFullName(String fullName) {
        return contactsMapper.selectContactsByFullName(fullName);
    }

    @Override
    public List<Contacts> queryContactsByConditionPaging(Map<String, Object> map) {
        return contactsMapper.selectContactsByConditionPaging(map);
    }

    @Override
    public int queryContactsByConditionCount(Contacts contacts) {
        return contactsMapper.selectContactsByConditionCount(contacts);
    }
}
