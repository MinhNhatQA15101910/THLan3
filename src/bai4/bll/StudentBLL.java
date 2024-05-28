package bai4.bll;

import bai4.dal.IStudentDAL;
import bai4.dal.StudentDAL;
import bai4.dto.models.SinhVienDTO;
import bai4.dto.responses.MessageDTO;
import bai4.dto.responses.SinhVienListMessageDTO;

import java.util.List;

public class StudentBLL implements IStudentBLL {
    private final IStudentDAL _studentDAL = new StudentDAL();

    @Override
    public MessageDTO addStudent(SinhVienDTO student, String avgScore) {
        try {
            if (student.maSV().isEmpty()) {
                return new MessageDTO(
                        400,
                        "MSSV không được để trống."
                );
            }

            if (student.hoTen().isEmpty()) {
                return new MessageDTO(
                        400,
                        "Họ tên không được để trống."
                );
            }

            float diemTBValue = Float.parseFloat(avgScore);

            if (diemTBValue < 0 || diemTBValue > 10) {
                return new MessageDTO(
                        400,
                        "Điểm TB không hợp lệ"
                );
            }

            SinhVienDTO existingStudent = _studentDAL.getStudentById(student.maSV());
            if (!existingStudent.maSV().isEmpty()) {
                return new MessageDTO(
                        400,
                        "Đã tồn tại sinh viên với MSSV được nhập."
                );
            }

            SinhVienDTO newStudent = new SinhVienDTO(
                    student.maSV(),
                    student.hoTen(),
                    student.lop(),
                    diemTBValue
            );

            return _studentDAL.addStudent(newStudent);
        } catch (NumberFormatException e) {
            return new MessageDTO(
                    400,
                    "Điểm TB không hợp lệ"
            );
        }
    }

    @Override
    public MessageDTO deleteStudent(String maSV) {
        return _studentDAL.deleteStudent(maSV);
    }

    @Override
    public SinhVienListMessageDTO getStudentsFilteredByAvgScore(String diemTBMin, String diemTBMax) {
        try {
            float diemTBMinValue = Float.parseFloat(diemTBMin);
            float diemTBMaxValue = Float.parseFloat(diemTBMax);

            if (diemTBMinValue > diemTBMaxValue) {
                return new SinhVienListMessageDTO(
                        400,
                        "Khoảng nhập vào không hợp lệ.",
                        null
                );
            }

            return _studentDAL.getStudentsFilteredByAvgScore(diemTBMinValue, diemTBMaxValue);
        } catch (NumberFormatException e) {
            return new SinhVienListMessageDTO(
                    400,
                    "Khoảng nhập vào không hợp lệ.",
                    null
            );
        }
    }

    @Override
    public List<SinhVienDTO> getStudentsFilteredByClass(String lopFilter) {
        return _studentDAL.getStudentsFilteredByClass(lopFilter);
    }

    @Override
    public List<SinhVienDTO> getStudentsFilteredByFullName(String hoTenFilter) {
        return _studentDAL.getStudentsFilteredByFullName(hoTenFilter);
    }

    @Override
    public List<SinhVienDTO> getStudentsFilteredById(String maSVFilter) {
        return _studentDAL.getStudentsFilteredById(maSVFilter);
    }

    @Override
    public List<SinhVienDTO> getAllStudents() {
        return _studentDAL.getAllStudents();
    }

    @Override
    public MessageDTO updateStudent(SinhVienDTO student, String avgScore) {
        try {
            if (student.maSV().isEmpty()) {
                return new MessageDTO(
                        400,
                        "MSSV không được để trống."
                );
            }

            if (student.hoTen().isEmpty()) {
                return new MessageDTO(
                        400,
                        "Họ tên không được để trống."
                );
            }

            float diemTBValue = Float.parseFloat(avgScore);

            if (diemTBValue < 0 || diemTBValue > 10) {
                return new MessageDTO(
                        400,
                        "Điểm TB không hợp lệ"
                );
            }

            SinhVienDTO updatedStudent = new SinhVienDTO(
                    student.maSV(),
                    student.hoTen(),
                    student.lop(),
                    diemTBValue
            );

            return _studentDAL.updateStudent(updatedStudent);
        } catch (NumberFormatException e) {
            return new MessageDTO(
                    400,
                    "Điểm TB không hợp lệ"
            );
        }
    }
}
