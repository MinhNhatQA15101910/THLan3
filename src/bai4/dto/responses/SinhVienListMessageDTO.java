package bai4.dto.responses;

import bai4.dto.models.SinhVienDTO;

import java.util.List;

public record SinhVienListMessageDTO(int statusCode, String message, List<SinhVienDTO> studentList) {}
