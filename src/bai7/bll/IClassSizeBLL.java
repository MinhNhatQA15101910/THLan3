package bai7.bll;

import bai7.dto.responses.ClassSizeListResponseDTO;
import bai7.dto.responses.ClassSizeResponseDTO;

public interface IClassSizeBLL {
    ClassSizeListResponseDTO getAllClassSize();
    ClassSizeResponseDTO getClassWithMaxNumberOfStudents();
    ClassSizeResponseDTO getClassWithMaxNumberOfStudentsFailed();
}
