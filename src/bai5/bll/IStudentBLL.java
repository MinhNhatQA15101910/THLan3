package bai5.bll;

import bai5.dto.models.SinhVienDTO;
import bai5.dto.responses.MessageDTO;
import bai5.dto.responses.SinhVienListMessageDTO;

import java.util.List;

public interface IStudentBLL {
    MessageDTO addStudent(SinhVienDTO student, String avgScore);
    MessageDTO deleteStudent(String maSV);
    SinhVienListMessageDTO getStudentsFilteredByAvgScore(String diemTBMin, String diemTBMax);
    List<SinhVienDTO> getStudentsFilteredByClass(String lopFilter);
    List<SinhVienDTO> getStudentsFilteredByFullName(String hoTenFilter);
    List<SinhVienDTO> getStudentsFilteredById(String maSVFilter);
    List<SinhVienDTO> getAllStudentsSortByAvgScoreDesc();
    List<SinhVienDTO> getAllStudentsSortByAvgScoreAsc();
    MessageDTO updateStudent(SinhVienDTO student, String avgScore);
}
