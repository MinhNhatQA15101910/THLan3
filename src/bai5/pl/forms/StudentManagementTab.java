package bai5.pl.forms;

import bai5.bll.IStudentBLL;
import bai5.bll.StudentBLL;
import bai5.dto.models.SinhVienDTO;
import bai5.dto.responses.MessageDTO;
import bai5.dto.responses.SinhVienListMessageDTO;
import bai5.pl.interfaces.IAddUpdateStudentRequester;
import bai5.pl.tablemodels.SinhVienTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class StudentManagementTab extends JPanel implements IAddUpdateStudentRequester {
    private final IStudentBLL _studentBLL = new StudentBLL();

    private JTable studentTable;
    private SinhVienTableModel studentTableModel;

    // Control
    private JScrollPane studentScrollPane;
    private JPanel controlPanel;
    private JTextField maSVTextField;
    private JTextField hoTenTextField;
    private JTextField lopTextField;
    private JTextField diemTBTextField;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;

    // Filter
    private JPanel filterPanel;
    private JButton filterBtn;
    private JComboBox<String> filterAttrComboBox;
    private JTextField maSVFilterTextField;
    private JTextField hoTenFilterTextField;
    private JTextField lopFilterTextField;
    private JTextField diemTBMinFilterTextField;
    private JTextField diemTBMaxFilterTextField;

    // Sort
    private JPanel sortPanel;
    private JRadioButton diemTBAscRBtn;
    private JRadioButton diemTBDescRBtn;

    public StudentManagementTab() {
        setLayout(new BorderLayout());

        // Load students
        loadStudents();
        add(studentScrollPane, BorderLayout.CENTER);

        // Control panel
        createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        // Filter add sort panel
        createFilterPanel();
        createSortPanel();

        JPanel filterAndSortPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        filterAndSortPanel.add(filterPanel);
        filterAndSortPanel.add(sortPanel);
        add(filterAndSortPanel, BorderLayout.NORTH);

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

        filterAttrComboBox.addActionListener(e -> {
            JComboBox<String> source = (JComboBox<String>) e.getSource();
            String selectedItem = (String) source.getSelectedItem();

            filterPanel.remove(1);

            if (Objects.equals(selectedItem, "MaSV")) {
                filterPanel.add(createMaSVFilterPanel(), 1);
            } else if (Objects.equals(selectedItem, "HoTen")) {
                filterPanel.add(createHotenFilterPanel(), 1);
            } else if (Objects.equals(selectedItem, "Lop")) {
                filterPanel.add(createLopFilterPanel(), 1);
            } else if (Objects.equals(selectedItem, "DiemTB")) {
                filterPanel.add(createDiemTBFilterPanel(), 1);
            }

            filterPanel.revalidate();
            filterPanel.repaint();
        });

        filterBtn.addActionListener(e -> {
            String currFilter = (String) filterAttrComboBox.getSelectedItem();
            assert currFilter != null;
            switch (currFilter) {
                case "MaSV" -> filterByMaSV();
                case "HoTen" -> filterByHoTen();
                case "Lop" -> filterByLop();
                case "DiemTB" -> filterByDiemTB();
            }
        });

        diemTBAscRBtn.addActionListener(e -> {
            if (diemTBAscRBtn.isSelected()) {
                diemTBDescRBtn.setSelected(false);
                refreshStudents();
            }
        });

        diemTBDescRBtn.addActionListener(e -> {
            if (diemTBDescRBtn.isSelected()) {
                diemTBAscRBtn.setSelected(false);
                refreshStudents();
            }
        });
    }

    private void loadStudents() {
        List<SinhVienDTO> studentList = _studentBLL.getAllStudentsSortByAvgScoreDesc();
        studentTableModel = new SinhVienTableModel(studentList);
        studentTable = new JTable(studentTableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentScrollPane = new JScrollPane(studentTable);
    }

    private void refreshStudents() {
        List<SinhVienDTO> studentList;
        if (diemTBDescRBtn.isSelected()) {
            studentList = _studentBLL.getAllStudentsSortByAvgScoreDesc();
        } else {
            studentList = _studentBLL.getAllStudentsSortByAvgScoreAsc();
        }

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

    private void createFilterPanel() {
        filterPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        filterAttrComboBox = new JComboBox<>(studentTableModel.getColumnNames());
        filterPanel.add(filterAttrComboBox);

        filterPanel.add(createMaSVFilterPanel());

        filterBtn = new JButton("Lọc");
        filterPanel.add(filterBtn);
    }

    private void createSortPanel() {
        sortPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        sortPanel.add(new JLabel(""));
        sortPanel.add(new JLabel(""));
        sortPanel.add(new JLabel("Sắp xếp bởi:"));
        diemTBAscRBtn = new JRadioButton();
        diemTBAscRBtn.setText("Điểm TB tăng dần");
        sortPanel.add(diemTBAscRBtn);
        diemTBDescRBtn = new JRadioButton();
        diemTBDescRBtn.setText("Điểm TB giảm dần");
        diemTBDescRBtn.setSelected(true);
        sortPanel.add(diemTBDescRBtn);
    }

    private JPanel createMaSVFilterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        panel.add(new JLabel("MSSV:"));

        maSVFilterTextField = new JTextField();
        panel.add(maSVFilterTextField);

        panel.requestFocus();

        return panel;
    }

    private JPanel createHotenFilterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        panel.add(new JLabel("Họ tên:"));

        hoTenFilterTextField = new JTextField();
        panel.add(hoTenFilterTextField);

        panel.requestFocus();

        return panel;
    }

    private JPanel createLopFilterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        panel.add(new JLabel("Lớp:"));

        lopFilterTextField = new JTextField();
        panel.add(lopFilterTextField);

        panel.requestFocus();

        return panel;
    }

    private JPanel createDiemTBFilterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        panel.add(new JLabel("Min:"));

        diemTBMinFilterTextField = new JTextField();
        panel.add(diemTBMinFilterTextField);

        panel.add(new JLabel("Max:"));

        diemTBMaxFilterTextField = new JTextField();
        panel.add(diemTBMaxFilterTextField);

        panel.requestFocus();

        return panel;
    }

    private void filterByMaSV() {
        String maSVFilter = maSVFilterTextField.getText().trim();

        List<SinhVienDTO> filteredStudentList = _studentBLL.getStudentsFilteredById(maSVFilter);
        studentTableModel.setStudents(filteredStudentList);
    }

    private void filterByHoTen() {
        String hoTenFilter = hoTenFilterTextField.getText().trim();

        List<SinhVienDTO> filteredStudentList = _studentBLL.getStudentsFilteredByFullName(hoTenFilter);
        studentTableModel.setStudents(filteredStudentList);
    }

    private void filterByLop() {
        String lopFilter = lopFilterTextField.getText().trim();

        List<SinhVienDTO> filteredStudentList = _studentBLL.getStudentsFilteredByClass(lopFilter);
        studentTableModel.setStudents(filteredStudentList);
    }

    private void filterByDiemTB() {
        String diemTBMin = diemTBMinFilterTextField.getText().trim();
        String diemTBMax = diemTBMaxFilterTextField.getText().trim();
        SinhVienListMessageDTO message = _studentBLL.getStudentsFilteredByAvgScore(diemTBMin, diemTBMax);
        if (message.statusCode() != 200) {
            JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);

        }

        studentTableModel.setStudents(message.studentList());
    }

    @Override
    public void onAddUpdateStudentFormClosing() {
        refreshStudents();
    }
}
