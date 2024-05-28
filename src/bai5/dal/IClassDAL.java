package bai5.dal;

import bai5.dto.models.LopDTO;
import bai5.dto.responses.MessageDTO;

import java.util.List;

public interface IClassDAL {
    MessageDTO addClass(LopDTO classObj);
    MessageDTO deleteClass(String maLop);
    LopDTO getClassById(String maLop);
    LopDTO getClassByName(String tenLop);
    List<LopDTO> getAllClasses();
    MessageDTO updateClass(LopDTO classObj);
}
