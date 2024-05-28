package bai6.dto.responses;

import bai6.dto.models.ConstraintDTO;

import java.util.List;

public record ConstraintListResponse(int statusCode, String message, List<ConstraintDTO> constraintList) {
}
