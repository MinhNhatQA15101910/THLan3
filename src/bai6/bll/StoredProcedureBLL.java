package bai6.bll;

import bai6.dal.IStoredProcedureDAL;
import bai6.dal.StoredProcedureDAL;
import bai6.dto.responses.StoredProcedureListResponse;

public class StoredProcedureBLL implements IStoredProcedureBLL {
    private final IStoredProcedureDAL _storedProcedureDAL = new StoredProcedureDAL();

    @Override
    public StoredProcedureListResponse getAllStoredProcedures() {
        return _storedProcedureDAL.getAllStoredProcedures();
    }
}
