package bai6.tablemodels;

import bai6.dto.models.ConstraintDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ConstraintTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Constraint Name", "Table Name", "Constraint Type"};
    private final List<ConstraintDTO> constraints;

    public ConstraintTableModel(List<ConstraintDTO> constraints) {
        super();
        this.constraints = constraints;
    }

    @Override
    public int getRowCount() {
        return constraints.size();
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
        ConstraintDTO constraint = constraints.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> constraint.constraintName();
            case 1 -> constraint.tableName();
            case 2 -> constraint.constraintType();
            default -> null;
        };
    }
}
