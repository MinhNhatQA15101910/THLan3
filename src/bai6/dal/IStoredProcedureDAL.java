package bai6.dal;

import bai6.dto.responses.StoredProcedureListResponse;

public interface IStoredProcedureDAL {
    StoredProcedureListResponse getAllStoredProcedures();
}
