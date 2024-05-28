package bai2.pl.forms;

import bai2.bll.ITableBLL;
import bai2.bll.TableBLL;
import bai2.dto.TableDTO;
import bai2.pl.tablemodels.TableTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TablesForm extends JFrame {
    private final ITableBLL _tableBLL = new TableBLL();

    private final JTable table;
    private final JScrollPane scrollPane;

    public TablesForm() {
        setTitle("Database Tables");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<TableDTO> tableList = _tableBLL.getAllTables();
        TableTableModel tableTableModel = new TableTableModel(tableList);
        table = new JTable(tableTableModel);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TablesForm::new);
    }
}
