package bai5.dto.responses;

import bai5.dto.models.SinhVienDTO;

import java.util.List;

public record SinhVienListMessageDTO(int statusCode, String message, List<SinhVienDTO> studentList) {}
