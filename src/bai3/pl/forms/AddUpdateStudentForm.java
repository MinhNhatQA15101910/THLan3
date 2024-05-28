package bai3.pl.forms;

import bai3.bll.ClassBLL;
import bai3.bll.IClassBLL;
import bai3.bll.IStudentBLL;
import bai3.bll.StudentBLL;
import bai3.dto.models.LopDTO;
import bai3.dto.models.SinhVienDTO;
import bai3.dto.responses.MessageDTO;
import bai3.pl.cellrenderers.LopCellRenderer;
import bai3.pl.interfaces.IAddUpdateStudentRequester;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class AddUpdateStudentForm extends JFrame {
    private final IClassBLL _classBLL = new ClassBLL();
    private final IStudentBLL _studentBLL = new StudentBLL();

    private final IAddUpdateStudentRequester _addUpdateStudentRequester;
    private SinhVienDTO _student = null;

    private final JTextField maSVTextField;
    private final JTextField hoTenTextField;
    private final JComboBox<LopDTO> lopComboBox;
    private final JTextField diemTBField;
    private final JButton featureBtn;

    public AddUpdateStudentForm(IAddUpdateStudentRequester requester) {
        _addUpdateStudentRequester = requester;

        setTitle("Thêm sinh viên");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        maSVTextField = new JTextField();
        hoTenTextField = new JTextField();
        lopComboBox = new JComboBox<>();
        loadClasses();
        lopComboBox.setRenderer(new LopCellRenderer());
        diemTBField = new JTextField();
        featureBtn = new JButton("Thêm");

        // Setup layout
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2, 10, 10));
        infoPanel.add(new JLabel("MSSV:"));
        infoPanel.add(maSVTextField);
        infoPanel.add(new JLabel("Họ tên:"));
        infoPanel.add(hoTenTextField);
        infoPanel.add(new JLabel("Lớp:"));
        infoPanel.add(lopComboBox);
        infoPanel.add(new JLabel("Điểm TB:"));
        infoPanel.add(diemTBField);
        add(infoPanel, BorderLayout.CENTER);

        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BorderLayout(10, 10));
        featurePanel.add(featureBtn, BorderLayout.CENTER);
        add(featurePanel, BorderLayout.SOUTH);

        // Add action listeners
        featureBtn.addActionListener(e -> addStudent());
    }

    public AddUpdateStudentForm(IAddUpdateStudentRequester requester, SinhVienDTO student) {
        _addUpdateStudentRequester = requester;
        _student = student;

        setTitle("Cập nhật sinh viên");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        maSVTextField = new JTextField();
        maSVTextField.setEditable(false);
        maSVTextField.setText(student.maSV());
        hoTenTextField = new JTextField();
        hoTenTextField.setText(student.hoTen());
        lopComboBox = new JComboBox<>();
        loadClasses();
        lopComboBox.setSelectedIndex(getSelectedLopIndex());
        lopComboBox.setRenderer(new LopCellRenderer());
        lopComboBox.setSelectedIndex(getSelectedLopIndex());
        diemTBField = new JTextField();
        diemTBField.setText(Float.toString(student.diemTB()));
        featureBtn = new JButton("Cập nhật");

        // Setup layout
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2, 10, 10));
        infoPanel.add(new JLabel("MSSV:"));
        infoPanel.add(maSVTextField);
        infoPanel.add(new JLabel("Họ tên:"));
        infoPanel.add(hoTenTextField);
        infoPanel.add(new JLabel("Lớp:"));
        infoPanel.add(lopComboBox);
        infoPanel.add(new JLabel("Điểm TB:"));
        infoPanel.add(diemTBField);
        add(infoPanel, BorderLayout.CENTER);

        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BorderLayout(10, 10));
        featurePanel.add(featureBtn, BorderLayout.CENTER);
        add(featurePanel, BorderLayout.SOUTH);

        // Add action listeners
        featureBtn.addActionListener(e -> updateStudent());
    }

    private int getSelectedLopIndex() {
        List<LopDTO> classList = _classBLL.getAllClasses();
        for (int i = 0; i < classList.size(); i++) {
            if (classList.get(i).maLop().equals(_student.lop())) {
                return i;
            }
        }

        return -1;
    }

    private void loadClasses() {
        List<LopDTO> classList = _classBLL.getAllClasses();
        for (LopDTO classObj : classList) {
            lopComboBox.addItem(classObj);
        }
    }

    private void addStudent() {
        SinhVienDTO newStudent = new SinhVienDTO(
                maSVTextField.getText().trim(),
                hoTenTextField.getText().trim(),
                ((LopDTO) Objects.requireNonNull(lopComboBox.getSelectedItem())).maLop(),
                0
        );
        String avgScore = diemTBField.getText();

        MessageDTO message = _studentBLL.addStudent(newStudent, avgScore);
        if (message.statusCode() == 200) {
            if (_addUpdateStudentRequester != null) {
                _addUpdateStudentRequester.onAddUpdateStudentFormClosing();
            }
            JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        SinhVienDTO updatedStudent = new SinhVienDTO(
                maSVTextField.getText().trim(),
                hoTenTextField.getText().trim(),
                ((LopDTO) Objects.requireNonNull(lopComboBox.getSelectedItem())).maLop(),
                0
        );
        String avgScore = diemTBField.getText();

        MessageDTO message = _studentBLL.updateStudent(updatedStudent, avgScore);
        if (message.statusCode() == 200) {
            if (_addUpdateStudentRequester != null) {
                _addUpdateStudentRequester.onAddUpdateStudentFormClosing();
            }
            JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
