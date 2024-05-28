package bai7.dal;

import bai7.dto.responses.ClassSizeListResponseDTO;
import bai7.dto.responses.ClassSizeResponseDTO;

public interface IClassSizeDAL {
    ClassSizeListResponseDTO getAllClassSize();
    ClassSizeResponseDTO getClassWithMaxNumberOfStudents();
    ClassSizeResponseDTO getClassWithMaxNumberOfStudentsFailed();
}
