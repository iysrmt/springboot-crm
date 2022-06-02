package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.DicValue;
import per.iys.crm.workbench.mapper.DicValueMapper;
import per.iys.crm.workbench.service.DicValueService;

import java.util.List;

@Service
public class DicValueServiceImpl implements DicValueService {

    private DicValueMapper dicValueMapper;

    @Autowired
    public DicValueServiceImpl(DicValueMapper dicValueMapper) {
        this.dicValueMapper = dicValueMapper;
    }

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
