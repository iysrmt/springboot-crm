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
        // 根据id查询线索信息
        String clueId = (String) map.get("clueId");
        // 获取当前用户
        User user = (User) map.get(Constants.SESSION_USER);
        // 是否创建交易
        String isCreateTran = (String) map.get("isCreateTran");

        // 线索
        Clue clue = clueMapper.selectClueById(clueId);
        // 把 线索 转换到 客户 中 clue->customer
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
        // 调用mapper方法
        customerMapper.insertCustomer(customer);

        // 把 线索 转换到 联系人 clue->contacts
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
        // 调用mapper方法
        contactsMapper.insertContacts(contacts);


        // 线索备注
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkByClueIdIsNotJoin(clueId);
        // 把 线索备注 转换到 客户备注 clueRemark -> customerRemark
        // 把 线索备注 转换到 联系方式备注 clueRemark -> contactsRemark
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

                // 添加到集合
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
            // 调用mapper方法
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
        }


        // 线索与市场活动关联关系
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        // 把 线索与市场活动关联关系 转换到 联系人与市场活动关系 clueActivityRelation -> contactsActivityRelation
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
            // 调用mapper
            contactsActivityRelationMapper.insertContactsActivityRelationByList(contactsActivityRelationList);
        }


        // 判断是否需要创建交易
        if ("true".equals(isCreateTran)) {
            // 创建交易
            Tran tran = (Tran) map.get("tran");
            tran.setId(UUIDUtils.getUUID());
            // 二次封装参数
            tran.setOwner(user.getId());
            tran.setCustomerId(customer.getId());
            tran.setContactsId(contacts.getId());
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.format(new Date()));
            // 调用mapper
            tranMapper.insertTran(tran);

            // 线索备注 转换 交易备注 clueRemark -> tranRemark
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
                // 调用mapper
                tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
            }
        }

        // 数据转换完成, 删除被转换数据
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        clueMapper.deleteClueById(clueId);
    }
}
