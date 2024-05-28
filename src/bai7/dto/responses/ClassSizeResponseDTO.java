package bai7.dto.responses;

import bai7.dto.models.ClassSizeDTO;

public record ClassSizeResponseDTO(int statusCode, String message, ClassSizeDTO classSize) {
}
