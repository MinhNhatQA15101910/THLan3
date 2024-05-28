package bai4.pl.cellrenderers;

import bai4.dto.models.LopDTO;

import javax.swing.*;
import java.awt.*;

public class LopCellRenderer extends JLabel implements ListCellRenderer<LopDTO> {
    public LopCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends LopDTO> list, LopDTO classObj, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if (classObj != null) {
            setText(classObj.tenLop());
        } else {
            setText("");
        }

        return this;
    }
}
