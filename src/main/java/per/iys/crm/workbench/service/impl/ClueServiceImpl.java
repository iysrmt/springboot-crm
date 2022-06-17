package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.commons.constants.Constants;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.workbench.domain.Clue;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.mapper.ClueMapper;
import per.iys.crm.workbench.mapper.CustomerMapper;
import per.iys.crm.workbench.service.ClueService;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ClueServiceImpl implements ClueService {

    private ClueMapper clueMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public ClueServiceImpl(ClueMapper clueMapper, CustomerMapper customerMapper) {
        this.clueMapper = clueMapper;
        this.customerMapper = customerMapper;
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
    public int queryCountOfActivityByCondition(Map<String, Object> conditions) {
        return clueMapper.selectCountOfActivityByCondition(conditions);
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

    @Override
    public void saveCovertClue(Map<String, Object> map) {
        // 根据id查询线索信息
        String clueId = (String) map.get("clueId");
        // 获取当前用户
        User user = (User) map.get(Constants.SESSION_USER);

        Clue clue = clueMapper.selectClueById(clueId);
        // 把线索中有关公司的信息转换到客户表中
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

        // 调用客户mapper
        customerMapper.insertCustomer(customer);
    }
}
