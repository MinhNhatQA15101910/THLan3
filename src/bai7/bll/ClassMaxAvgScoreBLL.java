package bai7.bll;

import bai7.dal.ClassMaxAvgScoreDAL;
import bai7.dal.IClassMaxAvgScoreDAL;
import bai7.dto.responses.ClassMaxAvgScoreResponseDTO;

public class ClassMaxAvgScoreBLL implements IClassMaxAvgScoreBLL {
    private final IClassMaxAvgScoreDAL _classMaxAvgScoreDAL = new ClassMaxAvgScoreDAL();

    @Override
    public ClassMaxAvgScoreResponseDTO getClassWithMaxAvgScoreStudent() {
        return _classMaxAvgScoreDAL.getClassWithMaxAvgScoreStudent();
    }
}
