package teams;

import athletes.AthletePanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TeamPanel extends JPanel {
    private final AthletePanel athletePanel;

    private final TeamDAO teamDAO = new TeamDAO();
    private final JTable teamsTable = new JTable();
    private final JTextField teamIdField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField dateFoundField = new JTextField();
    private final JTextField earningsField = new JTextField();

    public TeamPanel(AthletePanel athletePanel) {
        this.athletePanel = athletePanel;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        JPanel inputPanel = createInputPanel();
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        add(inputPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(buttonPanel, gbc);

        JPanel listPanel = new JPanel();
        JScrollPane teamsScroll = new JScrollPane(teamsTable);
        teamsScroll.setPreferredSize(new Dimension(800, 400));
        listPanel.add(teamsScroll);

        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(listPanel, gbc);

        refreshTeamsTable();
        teamsTable.addMouseListener(new MouseActionTeamTable());
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Добавяне / редактиране на екип"));

        panel.add(new JLabel("Име:"));
        panel.add(nameField);

        panel.add(new JLabel("Дата на основаване:"));
        panel.add(dateFoundField);

        panel.add(new JLabel("Печалби:"));
        panel.add(earningsField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Действия"));

        JButton addButton = new JButton("Добави");
        JButton updateButton = new JButton("Промени");
        JButton deleteButton = new JButton("Изтрий");

        addButton.addActionListener(e -> addTeam());
        updateButton.addActionListener(e -> updateTeam());
        deleteButton.addActionListener(e -> deleteTeam());

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    private void addTeam() {
        TeamDTO team = new TeamDTO(
            0,
            nameField.getText(),
            dateFoundField.getText(),
            Double.parseDouble(earningsField.getText())
        );

        teamDAO.addTeam(team);
        refreshTeamsTable();
        resetFields();
        athletePanel.refreshTeamComboBox();
    }

    private void updateTeam() {
        TeamDTO team = new TeamDTO(
            Integer.parseInt(teamIdField.getText()),
            nameField.getText(),
            dateFoundField.getText(),
            Double.parseDouble(earningsField.getText())
        );

        teamDAO.updateTeam(team);
        refreshTeamsTable();
        resetFields();
        athletePanel.refreshTeamComboBox();
    }

    private void deleteTeam() {
        teamDAO.deleteTeam(Integer.parseInt(teamIdField.getText()));
        refreshTeamsTable();
        resetFields();
        athletePanel.refreshTeamComboBox();
    }

    private void refreshTeamsTable() {
        List<TeamDTO> teams = teamDAO.getAllTeams();
        teamsTable.setModel(new TeamModel(teams));
        hideColumnOfTableByIndex(teamsTable, 0);
    }

    private void hideColumnOfTableByIndex(JTable table, int columnIndex) {
        table.getColumnModel().getColumn(columnIndex).setMinWidth(0);
        table.getColumnModel().getColumn(columnIndex).setMaxWidth(0);
        table.getColumnModel().getColumn(columnIndex).setWidth(0);
    }

    private void resetFields() {
        teamIdField.setText("");
        nameField.setText("");
        dateFoundField.setText("");
        earningsField.setText("");
    }

    class MouseActionTeamTable implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            int row = teamsTable.getSelectedRow();
            teamIdField.setText(teamsTable.getValueAt(row, 0).toString());
            nameField.setText(teamsTable.getValueAt(row, 1).toString());
            dateFoundField.setText(teamsTable.getValueAt(row, 2).toString());
            earningsField.setText(teamsTable.getValueAt(row, 3).toString());
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) { }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) { }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) { }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) { }
    }
}