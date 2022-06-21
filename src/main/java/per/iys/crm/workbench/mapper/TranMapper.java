package per.iys.crm.workbench.mapper;

import org.apache.ibatis.annotations.Mapper;
import per.iys.crm.workbench.domain.Tran;

import java.util.List;

@Mapper
public interface TranMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    int insert(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    int insertSelective(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    Tran selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    int updateByPrimaryKeySelective(Tran row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran
     *
     * @mbg.generated Fri Jun 17 16:47:26 CST 2022
     */
    int updateByPrimaryKey(Tran row);

    /**
     * 插入一条交易记录
     *
     * @param tran
     * @return
     */
    int insertTran(Tran tran);

    /**
     * 根据客户id查询交易记录
     *
     * @param customerId
     * @return
     */
    List<Tran> selectTranByCustomerId(String customerId);
}