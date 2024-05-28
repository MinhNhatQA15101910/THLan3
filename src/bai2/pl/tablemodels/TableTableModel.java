package bai2.pl.tablemodels;

import bai2.dto.TableDTO;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableTableModel extends DefaultTableModel {
    private final String[] columnNames = {"TableName"};
    private final List<TableDTO> tables;

    public TableTableModel(List<TableDTO> tables) {
        super();
        this.tables = tables;
        loadStudentsToModel();
    }

    private void loadStudentsToModel() {
        setColumnIdentifiers(columnNames);
        for (TableDTO table : tables) {
            Object[] row = {table.getName()};
            addRow(row);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
