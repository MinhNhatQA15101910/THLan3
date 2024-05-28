package bai3.pl.forms;

import bai3.bll.IStudentBLL;
import bai3.bll.StudentBLL;
import bai3.dto.models.SinhVienDTO;
import bai3.dto.responses.MessageDTO;
import bai3.pl.interfaces.IAddUpdateStudentRequester;
import bai3.pl.tablemodels.SinhVienTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentManagementTab extends JPanel implements IAddUpdateStudentRequester {
    private final IStudentBLL _studentBLL = new StudentBLL();

    private JTable studentTable;
    private SinhVienTableModel studentTableModel;

    private JScrollPane studentScrollPane;
    private JPanel controlPanel;
    private JTextField maSVTextField;
    private JTextField hoTenTextField;
    private JTextField lopTextField;
    private JTextField diemTBTextField;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;

    public StudentManagementTab() {
        setLayout(new BorderLayout());

        // Load students
        loadStudents();
        add(studentScrollPane, BorderLayout.CENTER);

        // Control panel
        createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        // Handle listeners
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String maSV = (String) studentTable.getValueAt(selectedRow, 0);
                    String hoTen = (String) studentTable.getValueAt(selectedRow, 1);
                    String lop = (String) studentTable.getValueAt(selectedRow, 2);
                    float diemTB = (float) studentTable.getValueAt(selectedRow, 3);

                    maSVTextField.setText(maSV);
                    hoTenTextField.setText(hoTen);
                    lopTextField.setText(lop);
                    diemTBTextField.setText(Float.toString(diemTB));
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa sinh viên đã chọn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    String maSV = (String) studentTable.getValueAt(selectedRow, 0);

                    MessageDTO message = _studentBLL.deleteStudent(maSV);
                    if (message.statusCode() == 200) {
                        refreshStudents();
                        JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addBtn.addActionListener(e -> {
            AddUpdateStudentForm form = new AddUpdateStudentForm(this);
            form.setVisible(true);
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow != -1) {
                SinhVienDTO updatedStudent = new SinhVienDTO(
                        (String) studentTable.getValueAt(selectedRow, 0),
                        (String) studentTable.getValueAt(selectedRow, 1),
                        (String) studentTable.getValueAt(selectedRow, 2),
                        (Float) studentTable.getValueAt(selectedRow, 3)
                );

                AddUpdateStudentForm form = new AddUpdateStudentForm(this, updatedStudent);
                form.setVisible(true);
            }
        });
    }

    private void loadStudents() {
        List<SinhVienDTO> studentList = _studentBLL.getAllStudents();
        studentTableModel = new SinhVienTableModel(studentList);
        studentTable = new JTable(studentTableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentScrollPane = new JScrollPane(studentTable);
    }

    private void refreshStudents() {
        List<SinhVienDTO> studentList = _studentBLL.getAllStudents();
        studentTableModel.setStudents(studentList);

        maSVTextField.setText("");
        hoTenTextField.setText("");
        lopTextField.setText("");
        diemTBTextField.setText("");
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        infoPanel.add(new JLabel("MaSV:"));
        maSVTextField = new JTextField();
        maSVTextField.setEditable(false);
        infoPanel.add(maSVTextField);
        infoPanel.add(new JLabel("HoTen:"));
        hoTenTextField = new JTextField();
        hoTenTextField.setEditable(false);
        infoPanel.add(hoTenTextField);
        infoPanel.add(new JLabel("Lop:"));
        lopTextField = new JTextField();
        lopTextField.setEditable(false);
        infoPanel.add(lopTextField);
        infoPanel.add(new JLabel("DiemTB:"));
        diemTBTextField = new JTextField();
        diemTBTextField.setEditable(false);
        infoPanel.add(diemTBTextField);
        controlPanel.add(infoPanel);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        addBtn = new JButton("Thêm");
        buttonsPanel.add(addBtn);
        updateBtn = new JButton("Cập nhật");
        buttonsPanel.add(updateBtn);
        deleteBtn = new JButton("Xóa");
        buttonsPanel.add(deleteBtn);
        controlPanel.add(buttonsPanel);
    }

    @Override
    public void onAddUpdateStudentFormClosing() {
        refreshStudents();
    }
}
