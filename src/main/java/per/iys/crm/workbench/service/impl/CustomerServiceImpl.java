package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.mapper.CustomerMapper;
import per.iys.crm.workbench.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public int createCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }
}
