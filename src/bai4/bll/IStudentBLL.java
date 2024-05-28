package bai4.bll;

import bai4.dto.models.SinhVienDTO;
import bai4.dto.responses.MessageDTO;
import bai4.dto.responses.SinhVienListMessageDTO;

import java.util.List;

public interface IStudentBLL {
    MessageDTO addStudent(SinhVienDTO student, String avgScore);
    MessageDTO deleteStudent(String maSV);
    SinhVienListMessageDTO getStudentsFilteredByAvgScore(String diemTBMin, String diemTBMax);
    List<SinhVienDTO> getStudentsFilteredByClass(String lopFilter);
    List<SinhVienDTO> getStudentsFilteredByFullName(String hoTenFilter);
    List<SinhVienDTO> getStudentsFilteredById(String maSVFilter);
    List<SinhVienDTO> getAllStudents();
    MessageDTO updateStudent(SinhVienDTO student, String avgScore);
}
