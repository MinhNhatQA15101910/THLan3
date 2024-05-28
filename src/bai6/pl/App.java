package bai6.pl;

import bai6.bll.ConstraintBLL;
import bai6.bll.IConstraintBLL;
import bai6.bll.IStoredProcedureBLL;
import bai6.bll.StoredProcedureBLL;
import bai6.dto.responses.ConstraintListResponse;
import bai6.dto.responses.StoredProcedureListResponse;
import bai6.tablemodels.ConstraintTableModel;
import bai6.tablemodels.StoredProcedureTableModel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private final IConstraintBLL _constraintBLL = new ConstraintBLL();
    private final IStoredProcedureBLL _storedProcedureBLL = new StoredProcedureBLL();

    private JTable constraintTable;
    private JTable storedProcedureTable;

    public App() {
        setTitle("Ràng buộc và stored procedure");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        panel.add(new JLabel("RÀNG BUỘC", SwingConstants.CENTER));

        ConstraintListResponse constraintListResponse = _constraintBLL.getAllConstraints();
        if (constraintListResponse.statusCode() == 200) {
            ConstraintTableModel constraintTableModel = new ConstraintTableModel(constraintListResponse.constraintList());
            constraintTable = new JTable(constraintTableModel);
            constraintTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            JOptionPane.showMessageDialog(null, constraintListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(new JScrollPane(constraintTable));

        panel.add(new JLabel("STORED PROCEDURE", SwingConstants.CENTER));

        StoredProcedureListResponse storedProcedureListResponse = _storedProcedureBLL.getAllStoredProcedures();
        if (storedProcedureListResponse.statusCode() == 200) {
            StoredProcedureTableModel storedProcedureTableModel = new StoredProcedureTableModel(storedProcedureListResponse.storedProcedureList());
            storedProcedureTable = new JTable(storedProcedureTableModel);
            storedProcedureTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            JOptionPane.showMessageDialog(null, storedProcedureListResponse.message(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        panel.add(new JScrollPane(storedProcedureTable));

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> (new App()).setVisible(true));
    }
}
