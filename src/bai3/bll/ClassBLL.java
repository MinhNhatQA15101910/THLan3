package bai3.bll;

import bai3.dal.ClassDAL;
import bai3.dal.IClassDAL;
import bai3.dal.IStudentDAL;
import bai3.dal.StudentDAL;
import bai3.dto.models.LopDTO;
import bai3.dto.models.SinhVienDTO;
import bai3.dto.responses.MessageDTO;

import java.util.List;

public class ClassBLL implements IClassBLL {
    private final IClassDAL _classDAL = new ClassDAL();
    private final IStudentDAL _studentDAL = new StudentDAL();

    @Override
    public MessageDTO addClass(LopDTO classObj) {
        if (classObj.maLop().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Mã lớp không được để trống."
            );
        }

        if (classObj.tenLop().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Tên lớp không được để trống."
            );
        }

        if (classObj.cvht().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Cố vấn học tập không được để trống."
            );
        }

        LopDTO existingClass = _classDAL.getClassById(classObj.maLop());
        if (!existingClass.maLop().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Đã tồn tại lớp với mã lớp được nhập."
            );
        }

        existingClass = _classDAL.getClassByName(classObj.tenLop());
        if (!existingClass.maLop().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Đã tồn tại lớp với tên lớp được nhập."
            );
        }

        return _classDAL.addClass(classObj);
    }

    @Override
    public List<LopDTO> getAllClasses() {
        return _classDAL.getAllClasses();
    }

    @Override
    public MessageDTO deleteClass(String maLop) {
        List<SinhVienDTO> studentList = _studentDAL.getStudentsByClassId(maLop);
        if (!studentList.isEmpty()) {
            return new MessageDTO(400, "Không thể xóa lớp này vì có sinh viên đang thuộc lớp.");
        }

        return _classDAL.deleteClass(maLop);
    }

    @Override
    public MessageDTO updateClass(LopDTO classObj) {
        if (classObj.tenLop().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Tên lớp không được để trống."
            );
        }

        if (classObj.cvht().isEmpty()) {
            return new MessageDTO(
                    400,
                    "Cố vấn học tập không được để trống."
            );
        }

        LopDTO existingClass = _classDAL.getClassByName(classObj.tenLop());
        if (!existingClass.maLop().isEmpty() && !existingClass.maLop().equals(classObj.maLop())) {
            return new MessageDTO(
                    400,
                    "Đã tồn tại lớp với tên lớp được nhập."
            );
        }

        return _classDAL.updateClass(classObj);
    }
}
