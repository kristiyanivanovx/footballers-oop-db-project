package athletes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


import java.sql.SQLException;
import java.util.List;

public class AthleteModel extends AbstractTableModel {

    private final int columnCount;
    private final List<Object[]> data = new ArrayList<>();
    private final String[] columnNames;

    public AthleteModel(List<AthleteDTO> athletes) throws SQLException {

        this.columnNames = new String[]{"Име", "Фамилия", "Националност", "Позиция", "Цена", "Дата на раждане", "Екип"};
        this.columnCount = columnNames.length;

        for (AthleteDTO athlete : athletes) {
            Object[] row = {
                    athlete.getFirstName(),
                    athlete.getLastName(),
                    athlete.getNationality(),
                    athlete.getPosition(),
                    athlete.getPrice(),
                    athlete.getDateBorn(),
                    athlete.getTeamId()
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
