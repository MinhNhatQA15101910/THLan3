package bai3.bll;

import bai3.dto.models.LopDTO;
import bai3.dto.responses.MessageDTO;

import java.util.List;

public interface IClassBLL {
    MessageDTO addClass(LopDTO classObj);
    List<LopDTO> getAllClasses();
    MessageDTO deleteClass(String maLop);
    MessageDTO updateClass(LopDTO classObj);
}
