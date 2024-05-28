package bai1.dal;

import bai1.dto.SinhVienDTO;

import java.util.List;

public interface IStudentDAL {
    List<SinhVienDTO> getAllStudents();
}
