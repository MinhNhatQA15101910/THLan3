package bai5.bll;

import bai5.dto.models.LopDTO;
import bai5.dto.responses.MessageDTO;

import java.util.List;

public interface IClassBLL {
    MessageDTO addClass(LopDTO classObj);
    List<LopDTO> getAllClasses();
    MessageDTO deleteClass(String maLop);
    MessageDTO updateClass(LopDTO classObj);
}
