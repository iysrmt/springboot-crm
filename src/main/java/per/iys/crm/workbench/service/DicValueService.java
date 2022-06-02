package per.iys.crm.workbench.service;

import per.iys.crm.workbench.domain.DicValue;

import java.util.List;

public interface DicValueService {
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
