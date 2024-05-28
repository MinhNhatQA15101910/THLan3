package bai7.dto.responses;

import bai7.dto.models.ClassMaxAvgScoreDTO;

public record ClassMaxAvgScoreResponseDTO(int statusCode, String message, ClassMaxAvgScoreDTO classMaxAvgScore) {
}
