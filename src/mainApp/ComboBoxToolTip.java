package mainApp;

import javax.swing.*;
import java.awt.*;

public class ComboBoxToolTip extends DefaultListCellRenderer {
    String[] toolTips;

    public ComboBoxToolTip(String[] toolTips) {
        this.toolTips = toolTips;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        JComponent comp = (JComponent) super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);

        if (-1 < index && value != null && index < toolTips.length) {
            list.setToolTipText(toolTips[index]);
        }
        return comp;
    }
}