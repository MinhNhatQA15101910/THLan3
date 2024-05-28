package bai3.pl.forms;

import bai3.bll.ClassBLL;
import bai3.bll.IClassBLL;
import bai3.dto.models.LopDTO;
import bai3.dto.responses.MessageDTO;
import bai3.pl.interfaces.IAddUpdateClassRequester;
import bai3.pl.tablemodels.LopTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClassManagementTab extends JPanel implements IAddUpdateClassRequester {
    private final IClassBLL _classBLL = new ClassBLL();

    private JTable classTable;
    private LopTableModel classTableModel;

    private JScrollPane classScrollPane;
    private JPanel controlPanel;
    private JTextField maLopTextField;
    private JTextField tenLopTextField;
    private JTextField cvhtTextField;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;

    public ClassManagementTab() {
        setLayout(new BorderLayout());

        // Load classes
        loadClasses();
        add(classScrollPane, BorderLayout.CENTER);

        // Control panel
        createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        // Handle listeners
        classTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = classTable.getSelectedRow();
                if (selectedRow != -1) {
                    String maLop = (String) classTable.getValueAt(selectedRow, 0);
                    String tenLop = (String) classTable.getValueAt(selectedRow, 1);
                    String cvht = (String) classTable.getValueAt(selectedRow, 2);

                    maLopTextField.setText(maLop);
                    tenLopTextField.setText(tenLop);
                    cvhtTextField.setText(cvht);
                }
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa lớp đã chọn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    String maLop = (String) classTable.getValueAt(selectedRow, 0);

                    MessageDTO message = _classBLL.deleteClass(maLop);
                    if (message.statusCode() == 200) {
                        refreshClasses();
                        JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addBtn.addActionListener(e -> {
            AddUpdateClassForm form = new AddUpdateClassForm(this);
            form.setVisible(true);
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) {
                LopDTO updatedClass = new LopDTO(
                        (String) classTable.getValueAt(selectedRow, 0),
                        (String) classTable.getValueAt(selectedRow, 1),
                        (String) classTable.getValueAt(selectedRow, 2)
                );

                AddUpdateClassForm form = new AddUpdateClassForm(this, updatedClass);
                form.setVisible(true);
            }
        });
    }

    private void loadClasses() {
        List<LopDTO> classList = _classBLL.getAllClasses();
        classTableModel = new LopTableModel(classList);
        classTable = new JTable(classTableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        classScrollPane = new JScrollPane(classTable);
    }

    private void refreshClasses() {
        List<LopDTO> classList = _classBLL.getAllClasses();
        classTableModel.setClasses(classList);
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        infoPanel.add(new JLabel("MaLop:"));
        maLopTextField = new JTextField();
        maLopTextField.setEditable(false);
        infoPanel.add(maLopTextField);
        infoPanel.add(new JLabel("TenLop:"));
        tenLopTextField = new JTextField();
        tenLopTextField.setEditable(false);
        infoPanel.add(tenLopTextField);
        infoPanel.add(new JLabel("CVHT:"));
        cvhtTextField = new JTextField();
        cvhtTextField.setEditable(false);
        infoPanel.add(cvhtTextField);
        infoPanel.add(new JLabel(""));
        infoPanel.add(new JLabel(""));
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
    public void onAddUpdateClassFormClosing() {
        refreshClasses();
    }
}
