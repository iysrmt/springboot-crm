package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Customer;

public interface CustomerService {

    /**
     * 创建一条客户信息
     *
     * @param customer
     * @return
     */
    int createCustomer(Customer customer);
}
