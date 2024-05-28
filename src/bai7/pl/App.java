package bai7.pl;

import bai7.bll.ClassMaxAvgScoreBLL;
import bai7.bll.ClassSizeBLL;
import bai7.bll.IClassMaxAvgScoreBLL;
import bai7.bll.IClassSizeBLL;
import bai7.dto.models.ClassSizeDTO;
import bai7.dto.responses.ClassMaxAvgScoreResponseDTO;
import bai7.dto.responses.ClassSizeListResponseDTO;
import bai7.dto.responses.ClassSizeResponseDTO;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private final IClassSizeBLL _classSizeBLL = new ClassSizeBLL();
    private final IClassMaxAvgScoreBLL _classMaxAvgScoreBLL = new ClassMaxAvgScoreBLL();

    public App() {
        setTitle("Thống kê");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createMainScrollPane(), BorderLayout.CENTER);
    }

    private JScrollPane createMainScrollPane() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Get all class size
        textArea.append("Số lượng sinh viên của từng lớp:\n");

        ClassSizeListResponseDTO classSizeListResponse = _classSizeBLL.getAllClassSize();
        if (classSizeListResponse.statusCode() != 200) {
            JOptionPane.showMessageDialog(null, classSizeListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            for (ClassSizeDTO classSize : classSizeListResponse.classSizeList()) {
                textArea.append("- Lớp " + classSize.classId() + ": " + classSize.numberOfStudents() + "\n");
            }
        }

        // Get class with the most students
        textArea.append("Lớp có sinh viên nhiều nhất:\n");

        ClassSizeResponseDTO classSizeResponse = _classSizeBLL.getClassWithMaxNumberOfStudents();
        if (classSizeResponse.statusCode() != 200) {
            JOptionPane.showMessageDialog(null, classSizeListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            textArea.append("- Lớp " + classSizeResponse.classSize().classId() + ": " + classSizeResponse.classSize().numberOfStudents() + "\n");
        }

        // Get class with max avg score student
        textArea.append("Lớp có sinh viên có điểm trung bình cao nhất:\n");

        ClassMaxAvgScoreResponseDTO classMaxAvgScoreResponse = _classMaxAvgScoreBLL.getClassWithMaxAvgScoreStudent();
        if (classMaxAvgScoreResponse.statusCode() != 200) {
            JOptionPane.showMessageDialog(null, classSizeListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            textArea.append("- Lớp " + classMaxAvgScoreResponse.classMaxAvgScore().classId() + ", sinh viên " + classMaxAvgScoreResponse.classMaxAvgScore().studentName() + ", điểm trung bình " + classMaxAvgScoreResponse.classMaxAvgScore().maxAvgScore() + "\n");
        }

        // Get class with max number of students failed
        textArea.append("Lớp có nhiều sinh viên không đạt nhất:\n");
        classSizeResponse = _classSizeBLL.getClassWithMaxNumberOfStudentsFailed();
        if (classSizeResponse.statusCode() != 200) {
            JOptionPane.showMessageDialog(null, classSizeListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            textArea.append("- Lớp " + classSizeResponse.classSize().classId() + ": " + classSizeResponse.classSize().numberOfStudents() + "\n");
        }

        return new JScrollPane(textArea);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> (new App()).setVisible(true));
    }
}
