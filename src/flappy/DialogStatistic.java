package flappy;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DialogStatistic extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public DialogStatistic(ArrayList<Integer> gameStat) {
        setLocationRelativeTo(null);
        setTitle("Statistics");

        table = new JTable(new DefaultTableModel(
            new Object[] {"0<score<20", "20<score<50", "50<score<100", "score>100"}, 0));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(
            new Object[] {gameStat.get(2), gameStat.get(3), gameStat.get(4), gameStat.get(5)});
        model.addRow(new Object[] {});
        model.addRow(new Object[] {"middle score", gameStat.get(0), "max score", gameStat.get(1)});
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        pack();
    }
}
