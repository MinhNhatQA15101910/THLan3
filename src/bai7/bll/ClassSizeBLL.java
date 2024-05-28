package bai7.bll;

import bai7.dal.ClassSizeDAL;
import bai7.dal.IClassSizeDAL;
import bai7.dto.responses.ClassSizeListResponseDTO;
import bai7.dto.responses.ClassSizeResponseDTO;

public class ClassSizeBLL implements IClassSizeBLL {
    private final IClassSizeDAL _classSizeDAL = new ClassSizeDAL();

    @Override
    public ClassSizeListResponseDTO getAllClassSize() {
        return _classSizeDAL.getAllClassSize();
    }

    @Override
    public ClassSizeResponseDTO getClassWithMaxNumberOfStudents() {
        return _classSizeDAL.getClassWithMaxNumberOfStudents();
    }

    @Override
    public ClassSizeResponseDTO getClassWithMaxNumberOfStudentsFailed() {
        return _classSizeDAL.getClassWithMaxNumberOfStudentsFailed();
    }
}
