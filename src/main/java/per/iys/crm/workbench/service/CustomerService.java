package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    /**
     * 创建一条客户信息
     *
     * @param customer
     * @return
     */
    int createCustomer(Customer customer);

    /**
     * 根据条件分页查询客户
     *
     * @param map key: customer, beginNo, pageSize
     * @return
     */
    List<Customer> queryCustomerByConditionForPage(Map<String, Object> map);

    /**
     * 根据条件查询客户记录条数
     *
     * @param map key: customer, beginNo, pageSize
     * @return
     */
    int queryCountOfCustomerByCondition(Map<String, Object> map);

    /**
     * 根据多条id删除客户
     *
     * @param ids
     * @return
     */
    int removeCustomerByIds(String[] ids);

    /**
     * 根据id查询指定客户
     *
     * @param id
     * @return
     */
    Customer queryCustomerById(String id);

    /**
     * 根据id更新客户信息
     *
     * @param customer
     * @return
     */
    int modifyCustomerById(Customer customer);

    /**
     * 根据id连接查询指定客户
     *
     * @param id
     * @return
     */
    Customer queryCustomerByIdForDetail(String id);

    /**
     * 按客户名模糊查询客户
     *
     * @return
     */
    List<Customer> queryCustomerByNameIsFuzzy(String name);

    /**
     * 按客户名精准查询客户
     *
     * @param name
     * @return
     */
    Customer queryCustomerByName(String name);
}
