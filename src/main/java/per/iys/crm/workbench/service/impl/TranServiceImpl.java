package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.Tran;
import per.iys.crm.workbench.mapper.TranMapper;
import per.iys.crm.workbench.service.TranService;

import java.util.List;

@Service
public class TranServiceImpl implements TranService {

    private TranMapper tranMapper;

    @Autowired
    public TranServiceImpl(TranMapper tranMapper) {
        this.tranMapper = tranMapper;
    }

    @Override
    public List<Tran> queryTranByCustomerId(String customerId) {
        return tranMapper.selectTranByCustomerId(customerId);
    }
}
