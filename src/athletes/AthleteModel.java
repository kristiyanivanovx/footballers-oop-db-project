package athletes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AthleteModel extends AbstractTableModel {
    private final int columnCount;
    private final List<Object[]> data = new ArrayList<>();
    private final String[] columnNames;

    public AthleteModel(List<AthleteDTO> athletes) {
        this.columnNames = new String[]
                { "Ид", "Име", "Фамилия", "Националност", "Позиция", "Цена", "Дата на раждане", "ИД на Екип", "Екип" };
        this.columnCount = columnNames.length;

        for (AthleteDTO athlete : athletes) {
            Object[] row = {
                athlete.getAthleteId(),
                athlete.getFirstName(),
                athlete.getLastName(),
                athlete.getNationality(),
                athlete.getPosition(),
                athlete.getPrice(),
                athlete.getDateBorn(),
                athlete.getTeamId(),
                athlete.getTeamName(),
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
