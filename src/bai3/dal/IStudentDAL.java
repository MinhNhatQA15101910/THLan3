package bai3.dal;

import bai3.dto.models.SinhVienDTO;
import bai3.dto.responses.MessageDTO;

import java.util.List;

public interface IStudentDAL {
    MessageDTO addStudent(SinhVienDTO student);
    MessageDTO deleteStudent(String maSV);
    List<SinhVienDTO> getAllStudents();
    List<SinhVienDTO> getStudentsByClassId(String maLop);
    SinhVienDTO getStudentById(String maSV);
    MessageDTO updateStudent(SinhVienDTO student);
}
