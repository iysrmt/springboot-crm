package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.Customer;

@Mapper
public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    int insert(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    int insertSelective(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    int updateByPrimaryKeySelective(Customer row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbg.generated Fri Jun 17 15:24:12 CST 2022
     */
    int updateByPrimaryKey(Customer row);

    /**
     * 保存创建的客户
     *
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);
}