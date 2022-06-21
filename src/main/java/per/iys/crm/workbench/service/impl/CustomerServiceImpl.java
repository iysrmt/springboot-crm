package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.Customer;
import per.iys.crm.workbench.mapper.CustomerMapper;
import per.iys.crm.workbench.service.CustomerService;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Customer> queryCustomerByConditionForPage(Map<String, Object> map) {
        return customerMapper.selectCustomerByConditionForPage(map);
    }

    @Override
    public int queryCountOfCustomerByCondition(Map<String, Object> map) {
        return customerMapper.selectCountOfCustomerByCondition(map);
    }

    @Override
    public int removeCustomerByIds(String[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }

    @Override
    public Customer queryCustomerById(String id) {
        return customerMapper.selectCustomerById(id);
    }

    @Override
    public int modifyCustomerById(Customer customer) {
        return customerMapper.updateCustomerById(customer);
    }

    @Override
    public Customer queryCustomerByIdForDetail(String id) {
        return customerMapper.selectCustomerByIdForDetail(id);
    }
}
