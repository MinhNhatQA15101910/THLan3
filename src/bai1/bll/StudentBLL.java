package bai1.bll;

import bai1.dal.IStudentDAL;
import bai1.dal.StudentDAL;
import bai1.dto.SinhVienDTO;

import java.util.List;

public class StudentBLL implements IStudentBLL {
    private final IStudentDAL _studentDAL = new StudentDAL();

    @Override
    public List<SinhVienDTO> getAllStudents() {
        return _studentDAL.getAllStudents();
    }
}
