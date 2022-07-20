package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.Contacts;
import per.iys.crm.workbench.mapper.ContactsMapper;
import per.iys.crm.workbench.service.ContactsService;

import java.util.List;
import java.util.Map;

@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsMapper contactsMapper;

    @Autowired
    public ContactsServiceImpl(ContactsMapper contactsMapper) {
        this.contactsMapper = contactsMapper;
    }

    @Override
    public List<Contacts> queryContactsByCustomerId(String customerId) {
        return contactsMapper.selectContactsByCustomerId(customerId);
    }

    @Override
    public int saveCreateContacts(Contacts contacts) {
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
