package bai6.dto.responses;

import bai6.dto.models.StoredProcedureDTO;

import java.util.List;

public record StoredProcedureListResponse(int statusCode, String message,
                                          List<StoredProcedureDTO> storedProcedureList) {
}
