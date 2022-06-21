package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.Tran;

import java.util.List;

public interface TranService {

    /**
     * 根据客户id查询交易记录
     *
     * @param customerId
     * @return
     */
    List<Tran> queryTranByCustomerId(String customerId);
}
