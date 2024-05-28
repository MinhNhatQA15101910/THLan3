package bai6.tablemodels;

import bai6.dto.models.StoredProcedureDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StoredProcedureTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Name", "Create Date", "Modify Date"};
    private final List<StoredProcedureDTO> storedProcedures;

    public StoredProcedureTableModel(List<StoredProcedureDTO> storedProcedures) {
        super();
        this.storedProcedures = storedProcedures;
    }

    @Override
    public int getRowCount() {
        return storedProcedures.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StoredProcedureDTO storedProcedure = storedProcedures.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> storedProcedure.name();
            case 1 -> storedProcedure.createDate();
            case 2 -> storedProcedure.modifyDate();
            default -> null;
        };
    }
}
