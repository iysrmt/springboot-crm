package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.CustomerRemark;

import java.util.List;

public interface CustomerRemarkService {

    /**
     * 根据customerId查询客户备注
     *
     * @param customerId
     * @return
     */
    List<CustomerRemark> queryCustomerRemarkByCustomerId(String customerId);
}
