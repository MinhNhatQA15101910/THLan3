package bai2.bll;

import bai2.dal.ITableDAL;
import bai2.dal.TableDAL;
import bai2.dto.TableDTO;

import java.util.List;

public class TableBLL implements ITableBLL {
    private final ITableDAL _tableDAL = new TableDAL();

    @Override
    public List<TableDTO> getAllTables() {
        return _tableDAL.getAllTables();
    }
}
