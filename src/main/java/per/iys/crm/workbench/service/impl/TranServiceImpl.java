package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.iys.crm.commons.utils.DateUtils;
import per.iys.crm.commons.utils.UUIDUtils;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.domain.Tran;
import per.iys.crm.workbench.mapper.CustomerMapper;
import per.iys.crm.workbench.mapper.TranMapper;
import per.iys.crm.workbench.service.TranService;

import java.util.Date;
import java.util.List;

@Service
public class TranServiceImpl implements TranService {

    private TranMapper tranMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public TranServiceImpl(TranMapper tranMapper, CustomerMapper customerMapper) {
        this.tranMapper = tranMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<Tran> queryTranByCustomerId(String customerId) {
        return tranMapper.selectTranByCustomerId(customerId);
    }

    @Transactional
    @Override
    public int saveCreateTran(Tran tran) {
        // 检查客户是否存在
        Customer customer = customerMapper.selectCustomerByName(tran.getCustomerId());
        if (customer == null) {
            // 当客户不存在时, 创建客户
            customer = new Customer();
            customer.setId(UUIDUtils.getUUID());
            customer.setOwner(tran.getCreateBy());
            customer.setCreateBy(tran.getCreateBy());
            customer.setCreateTime(DateUtils.format(new Date()));
            customer.setName(tran.getCustomerId());
            tran.setCustomerId(customer.getId());
            customerMapper.insertCustomer(customer);
        } else {
            tran.setCustomerId(customer.getId());
        }
        return tranMapper.insertTran(tran);
    }
}
