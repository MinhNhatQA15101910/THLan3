package bai7.dto.responses;

import bai7.dto.models.ClassSizeDTO;

import java.util.List;

public record ClassSizeListResponseDTO(int statusCode, String message, List<ClassSizeDTO> classSizeList) {
}
