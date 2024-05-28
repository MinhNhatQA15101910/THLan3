package bai1.pl.forms;

import bai1.bll.IStudentBLL;
import bai1.bll.StudentBLL;
import bai1.dto.SinhVienDTO;
import bai1.pl.tablemodels.SinhVienTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentsForm extends JFrame {
    private final IStudentBLL _studentBLL = new StudentBLL();

    private final JTable table;
    private final JScrollPane scrollPane;

    public StudentsForm() {
        setTitle("Student Database");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<SinhVienDTO> studentList = _studentBLL.getAllStudents();
        SinhVienTableModel studentTableModel = new SinhVienTableModel(studentList);
        table = new JTable(studentTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentsForm::new);
    }
}
