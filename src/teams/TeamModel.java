package teams;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TeamModel extends AbstractTableModel {
    private final int columnCount;
    private final List<Object[]> data = new ArrayList<>();
    private final String[] columnNames;

    public TeamModel(List<TeamDTO> teams) {
        this.columnNames = new String[] { "Ид", "Име", "Дата на основаване", "Приходи" };
        this.columnCount = columnNames.length;

        for (TeamDTO team : teams) {
            Object[] row = {
                team.getTeamId(),
                team.getName(),
                team.getDateFound(),
                team.getEarnings()
            };

            data.add(row);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}