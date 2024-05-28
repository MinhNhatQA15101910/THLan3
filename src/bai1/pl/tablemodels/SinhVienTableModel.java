package bai1.pl.tablemodels;

import bai1.dto.SinhVienDTO;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SinhVienTableModel extends DefaultTableModel {
    private final String[] columnNames = {"MaSV", "HoTen", "Lop", "DiemTB"};
    private final List<SinhVienDTO> students;

    public SinhVienTableModel(List<SinhVienDTO> students) {
        super();
        this.students = students;
        loadStudentsToModel();
    }

    private void loadStudentsToModel() {
        setColumnIdentifiers(columnNames);
        for (SinhVienDTO student : students) {
            Object[] row = {student.maSV(), student.hoTen(), student.lop(), student.diemTB()};
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
