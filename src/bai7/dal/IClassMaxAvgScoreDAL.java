package bai7.dal;

import bai7.dto.responses.ClassMaxAvgScoreResponseDTO;

public interface IClassMaxAvgScoreDAL {
    ClassMaxAvgScoreResponseDTO getClassWithMaxAvgScoreStudent();
}
