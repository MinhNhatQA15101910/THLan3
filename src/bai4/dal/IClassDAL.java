package bai4.dal;

import bai4.dto.models.LopDTO;
import bai4.dto.responses.MessageDTO;

import java.util.List;

public interface IClassDAL {
    MessageDTO addClass(LopDTO classObj);
    MessageDTO deleteClass(String maLop);
    LopDTO getClassById(String maLop);
    LopDTO getClassByName(String tenLop);
    List<LopDTO> getAllClasses();
    MessageDTO updateClass(LopDTO classObj);
}
