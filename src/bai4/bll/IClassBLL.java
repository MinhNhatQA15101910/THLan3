package bai4.bll;

import bai4.dto.models.LopDTO;
import bai4.dto.responses.MessageDTO;

import java.util.List;

public interface IClassBLL {
    MessageDTO addClass(LopDTO classObj);
    List<LopDTO> getAllClasses();
    MessageDTO deleteClass(String maLop);
    MessageDTO updateClass(LopDTO classObj);
}
