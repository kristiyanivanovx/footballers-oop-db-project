package matches;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MatchModel extends AbstractTableModel {
    private final int columnCount;
    private final List<Object[]> data = new ArrayList<>();
    private final String[] columnNames;

    public MatchModel(List<MatchDTO> matches) {
        this.columnNames = new String[]{
                "Мач Ид", "Домакин Ид", "Гост Ид", "Домакин", "Гост", "Голове на домакина", "Голове на госта"};
        this.columnCount = columnNames.length;

        for (MatchDTO match : matches) {
            Object[] row = {
                match.getMatchId(),
                match.getHomeTeamId(),
                match.getAwayTeamId(),
                match.getHomeTeamName(),
                match.getAwayTeamName(),
                match.getHomeTeamGoals(),
                match.getAwayTeamGoals()
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