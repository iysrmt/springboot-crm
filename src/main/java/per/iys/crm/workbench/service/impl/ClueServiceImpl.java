package per.iys.crm.workbench.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.iys.crm.workbench.domain.Clue;
import per.iys.crm.workbench.mapper.ClueMapper;
import per.iys.crm.workbench.service.ClueService;

import java.util.List;
import java.util.Map;


@Service
public class ClueServiceImpl implements ClueService {

    private ClueMapper clueMapper;

    @Autowired
    public ClueServiceImpl(ClueMapper clueMapper) {
        this.clueMapper = clueMapper;
    }

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public List<Clue> queryClueByConditionForPage(Map<String, Object> conditionsAndPages) {
        return clueMapper.selectClueByConditionForPage(conditionsAndPages);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> conditions) {
        return clueMapper.selectCountOfActivityByCondition(conditions);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.selectClueById(id);
    }

    @Override
    public int saveEditClueById(Clue clue) {
        return clueMapper.updateClueById(clue);
    }

    @Override
    public int deleteClueByIds(String[] ids) {
        return clueMapper.deleteClueByIds(ids);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }
}
