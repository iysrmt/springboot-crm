package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.workbench.domain.*;
import per.iys.crm.workbench.mapper.*;
import per.iys.crm.workbench.service.ClueService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ClueServiceImpl implements ClueService {

    private ClueMapper clueMapper;
    private CustomerMapper customerMapper;
    private ContactsMapper contactsMapper;
    private ClueRemarkMapper clueRemarkMapper;
    private CustomerRemarkMapper customerRemarkMapper;
    private ContactsRemarkMapper contactsRemarkMapper;
    private ClueActivityRelationMapper clueActivityRelationMapper;
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    private TranMapper tranMapper;
    private TranRemarkMapper tranRemarkMapper;

    @Autowired
    public ClueServiceImpl(ClueMapper clueMapper, CustomerMapper customerMapper, ContactsMapper contactsMapper, ClueRemarkMapper clueRemarkMapper, CustomerRemarkMapper customerRemarkMapper, ContactsRemarkMapper contactsRemarkMapper, ClueActivityRelationMapper clueActivityRelationMapper, ContactsActivityRelationMapper contactsActivityRelationMapper, TranMapper tranMapper, TranRemarkMapper tranRemarkMapper) {
        this.clueMapper = clueMapper;
        this.customerMapper = customerMapper;
        this.contactsMapper = contactsMapper;
        this.clueRemarkMapper = clueRemarkMapper;
        this.customerRemarkMapper = customerRemarkMapper;
        this.contactsRemarkMapper = contactsRemarkMapper;
        this.clueActivityRelationMapper = clueActivityRelationMapper;
        this.contactsActivityRelationMapper = contactsActivityRelationMapper;
        this.tranMapper = tranMapper;
        this.tranRemarkMapper = tranRemarkMapper;
    }

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public List<Clue> queryClueByConditionForPage(Map<String, Object> conditionsAndPages) {
        return clueMapper.selectClueByConditionForPage(conditionsAndPages);
    }

    @Override
    public int queryCountOfClueByCondition(Map<String, Object> conditions) {
        return clueMapper.selectCountOfClueByCondition(conditions);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.selectClueById(id);
    }

    @Override
    public int saveEditClueById(Clue clue) {
        return clueMapper.updateClueById(clue);
    }

    @Override
    public int deleteClueByIds(String[] ids) {
        return clueMapper.deleteClueByIds(ids);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Transactional
    @Override
    public void saveCovertClue(Map<String, Object> map) {
        // ??????id??????????????????
        String clueId = (String) map.get("clueId");
        // ??????????????????
        User user = (User) map.get(Constants.SESSION_USER);
        // ??????????????????
        String isCreateTran = (String) map.get("isCreateTran");

        // ??????
        Clue clue = clueMapper.selectClueById(clueId);
        // ??? ?????? ????????? ?????? ??? clue->customer
        Customer customer = new Customer();
        customer.setId(UUIDUtils.getUUID());
        customer.setOwner(user.getId());
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.format(new Date()));
        customer.setContactSummary(clue.getContactSummary());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setDescription(clue.getDescription());
        customer.setAddress(clue.getAddress());
        // ??????mapper??????
        customerMapper.insertCustomer(customer);

        // ??? ?????? ????????? ????????? clue->contacts
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtils.getUUID());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullName(clue.getFullName());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setmPhone(clue.getmPhone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.format(new Date()));
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        // ??????mapper??????
        contactsMapper.insertContacts(contacts);


        // ????????????
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkByClueIdIsNotJoin(clueId);
        // ??? ???????????? ????????? ???????????? clueRemark -> customerRemark
        // ??? ???????????? ????????? ?????????????????? clueRemark -> contactsRemark
        if (clueRemarkList != null && clueRemarkList.size() > 0) {
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();
            CustomerRemark customerRemark = null;
            ContactsRemark contactsRemark = null;
            for (ClueRemark clueRemark : clueRemarkList) {
                customerRemark = new CustomerRemark();
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(DateUtils.format(new Date()));
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setCustomerId(customer.getId());

                // ???????????????
                customerRemarkList.add(customerRemark);

                contactsRemark = new ContactsRemark();
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemarkList.add(contactsRemark);
            }
            // ??????mapper??????
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
        }


        // ?????????????????????????????????
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        // ??? ????????????????????????????????? ????????? ?????????????????????????????? clueActivityRelation -> contactsActivityRelation
        if (clueActivityRelationList != null && clueActivityRelationList.size() > 0) {
            List<ContactsActivityRelation> contactsActivityRelationList = new ArrayList<>();
            ContactsActivityRelation contactsActivityRelation = null;
            for (ClueActivityRelation clueActivityRelation : clueActivityRelationList) {
                contactsActivityRelation = new ContactsActivityRelation();
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
            // ??????mapper
            contactsActivityRelationMapper.insertContactsActivityRelationByList(contactsActivityRelationList);
        }


        // ??????????????????????????????
        if ("true".equals(isCreateTran)) {
            // ????????????
            Tran tran = (Tran) map.get("tran");
            tran.setId(UUIDUtils.getUUID());
            // ??????????????????
            tran.setOwner(user.getId());
            tran.setCustomerId(customer.getId());
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.format(new Date()));
            // ??????mapper
            tranMapper.insertTran(tran);

            // ???????????? ?????? ???????????? clueRemark -> tranRemark
            if (clueRemarkList != null && clueRemarkList.size() > 0) {
                List<TranRemark> tranRemarkList = new ArrayList<>();
                TranRemark tranRemark = null;
                for (ClueRemark clueRemark : clueRemarkList) {
                    tranRemark = new TranRemark();
                    tranRemark.setId(UUIDUtils.getUUID());
                    tranRemark.setNoteContent(clueRemark.getNoteContent());
                    tranRemark.setCreateBy(clueRemark.getCreateBy());
                    tranRemark.setCreateTime(clueRemark.getCreateTime());
                    tranRemark.setEditBy(clueRemark.getEditBy());
                    tranRemark.setEditTime(clueRemark.getEditTime());
                    tranRemark.setEditFlag(clueRemark.getEditFlag());
                    tranRemark.setTranId(tran.getId());
                    tranRemarkList.add(tranRemark);
                }
                // ??????mapper
                tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
            }
        }

        // ??????????????????, ?????????????????????
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        clueMapper.deleteClueById(clueId);
    }
}
