package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.ClueActivityRelation;
import per.iys.crm.workbench.mapper.ClueActivityRelationMapper;
import per.iys.crm.workbench.service.ClueActivityRelationService;

import java.util.List;

@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    public ClueActivityRelationServiceImpl(ClueActivityRelationMapper clueActivityRelationMapper) {
        this.clueActivityRelationMapper = clueActivityRelationMapper;
    }

    @Override
    public int saveClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    @Override
    public int removeClueActivityRelationByClueIdActivityId(ClueActivityRelation car) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(car);
    }
}
