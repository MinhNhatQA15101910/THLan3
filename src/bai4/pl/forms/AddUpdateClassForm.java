package bai4.pl.forms;

import bai4.bll.ClassBLL;
import bai4.bll.IClassBLL;
import bai4.dto.models.LopDTO;
import bai4.dto.responses.MessageDTO;
import bai4.pl.interfaces.IAddUpdateClassRequester;

import javax.swing.*;
import java.awt.*;

public class AddUpdateClassForm extends JFrame {
    private final IClassBLL _classBLL = new ClassBLL();

    private final IAddUpdateClassRequester _addUpdateClassRequester;

    private final JTextField maLopTextField;
    private final JTextField tenLopTextField;
    private final JTextField cvhtTextField;
    private JButton featureBtn;

    public AddUpdateClassForm(IAddUpdateClassRequester requester) {
        _addUpdateClassRequester = requester;

        setTitle("Thêm lớp");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        maLopTextField = new JTextField();
        tenLopTextField = new JTextField();
        cvhtTextField = new JTextField();
        featureBtn = new JButton("Thêm");

        // Setup layout
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Mã lớp:"));
        infoPanel.add(maLopTextField);
        infoPanel.add(new JLabel("Tên lớp:"));
        infoPanel.add(tenLopTextField);
        infoPanel.add(new JLabel("CVHT:"));
        infoPanel.add(cvhtTextField);
        add(infoPanel, BorderLayout.CENTER);

        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BorderLayout(10, 10));
        featureBtn = new JButton("Thêm");
        featurePanel.add(featureBtn, BorderLayout.CENTER);
        add(featurePanel, BorderLayout.SOUTH);


        // Add action listeners
        featureBtn.addActionListener(e -> addClass());
    }

    public AddUpdateClassForm(IAddUpdateClassRequester requester, LopDTO classObj) {
        _addUpdateClassRequester = requester;

        setTitle("Cập nhật lớp");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        maLopTextField = new JTextField();
        maLopTextField.setEditable(false);
        maLopTextField.setText(classObj.maLop());
        tenLopTextField = new JTextField();
        tenLopTextField.setText(classObj.tenLop());
        cvhtTextField = new JTextField();
        cvhtTextField.setText(classObj.cvht());
        featureBtn = new JButton("Cập nhật");

        // Setup layout
        setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2, 10, 10));
        infoPanel.add(new JLabel("Mã lớp:"));
        infoPanel.add(maLopTextField);
        infoPanel.add(new JLabel("Tên lớp:"));
        infoPanel.add(tenLopTextField);
        infoPanel.add(new JLabel("CVHT:"));
        infoPanel.add(cvhtTextField);
        add(infoPanel, BorderLayout.CENTER);

        JPanel featurePanel = new JPanel();
        featurePanel.setLayout(new BorderLayout(10, 10));
        featureBtn = new JButton("Cập nhật");
        featurePanel.add(featureBtn, BorderLayout.CENTER);
        add(featurePanel, BorderLayout.SOUTH);

        // Add action listeners
        featureBtn.addActionListener(e -> updateClass());
    }

    private void updateClass() {
        LopDTO updatedClass = new LopDTO(
                maLopTextField.getText().trim(),
                tenLopTextField.getText().trim(),
                cvhtTextField.getText().trim()
        );

        MessageDTO message = _classBLL.updateClass(updatedClass);
        if (message.statusCode() == 200) {
            if (_addUpdateClassRequester != null) {
                _addUpdateClassRequester.onAddUpdateClassFormClosing();
            }
            JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addClass() {
        LopDTO newClass = new LopDTO(
                maLopTextField.getText().trim(),
                tenLopTextField.getText().trim(),
                cvhtTextField.getText().trim()
        );

        MessageDTO message = _classBLL.addClass(newClass);
        if (message.statusCode() == 200) {
            if (_addUpdateClassRequester != null) {
                _addUpdateClassRequester.onAddUpdateClassFormClosing();
            }
            JOptionPane.showMessageDialog(null, message.message(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, message.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
