package bai4.dal;

import bai4.dto.models.SinhVienDTO;
import bai4.dto.responses.MessageDTO;
import bai4.dto.responses.SinhVienListMessageDTO;

import java.util.List;

public interface IStudentDAL {
    MessageDTO addStudent(SinhVienDTO student);
    MessageDTO deleteStudent(String maSV);
    List<SinhVienDTO> getAllStudents();
    List<SinhVienDTO> getStudentsByClassId(String maLop);
    SinhVienListMessageDTO getStudentsFilteredByAvgScore(float diemTBMinValue, float diemTBMaxValue);
    List<SinhVienDTO> getStudentsFilteredByClass(String lopFilter);
    List<SinhVienDTO> getStudentsFilteredByFullName(String hoTenFilter);
    List<SinhVienDTO> getStudentsFilteredById(String maSVFilter);
    SinhVienDTO getStudentById(String maSV);
    MessageDTO updateStudent(SinhVienDTO student);
}
