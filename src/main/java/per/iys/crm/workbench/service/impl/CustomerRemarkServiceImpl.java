package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.CustomerRemark;
import per.iys.crm.workbench.mapper.CustomerRemarkMapper;
import per.iys.crm.workbench.service.CustomerRemarkService;

import java.util.List;

@Service
public class CustomerRemarkServiceImpl implements CustomerRemarkService {

    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    public CustomerRemarkServiceImpl(CustomerRemarkMapper customerRemarkMapper) {
        this.customerRemarkMapper = customerRemarkMapper;
    }

    @Override
    public List<CustomerRemark> queryCustomerRemarkByCustomerId(String customerId) {
        return customerRemarkMapper.selectCustomerRemarkByCustomerId(customerId);
    }
}
