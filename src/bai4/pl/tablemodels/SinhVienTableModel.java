package bai4.pl.tablemodels;

import bai4.dto.models.SinhVienDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SinhVienTableModel extends AbstractTableModel {
    private final String[] columnNames = {"MaSV", "HoTen", "Lop", "DiemTB"};
    private List<SinhVienDTO> students;

    public SinhVienTableModel(List<SinhVienDTO> students) {
        super();
        this.students = students;
    }

    public void setStudents(List<SinhVienDTO> students) {
        this.students = students;
        fireTableDataChanged();
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public int getRowCount() {
        return students.size();
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
        SinhVienDTO student = students.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> student.maSV();
            case 1 -> student.hoTen();
            case 2 -> student.lop();
            case 3 -> student.diemTB();
            default -> null;
        };
    }
}
