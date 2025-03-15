package matches;

import common.Utils;
import teams.TeamDAO;
import teams.TeamDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MatchPanel extends JPanel {
    private final MatchDAO matchDAO = new MatchDAO();
    private final JTable matchesTable = new JTable();
    private final JTextField matchIdField = new JTextField();
    private final JComboBox<String> homeTeamComboBox = new JComboBox<>();
    private final JComboBox<String> awayTeamComboBox = new JComboBox<>();
    private final JTextField homeTeamGoalsField = new JTextField();
    private final JTextField awayTeamGoalsField = new JTextField();

    private final Utils utils = new Utils();

    public MatchPanel() {
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
        JScrollPane matchesScroll = new JScrollPane(matchesTable);
        matchesScroll.setPreferredSize(new Dimension(800, 400));
        listPanel.add(matchesScroll);

        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(listPanel, gbc);

        refreshMatchesTable();
        refreshTeamComboBoxes();
        matchesTable.addMouseListener(new MouseActionMatchTable());
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Добавяне / редактиране на мач"));

        panel.add(new JLabel("Домакин:"));
        panel.add(homeTeamComboBox);

        panel.add(new JLabel("Гост:"));
        panel.add(awayTeamComboBox);

        panel.add(new JLabel("Голове на домакина:"));
        panel.add(homeTeamGoalsField);

        panel.add(new JLabel("Голове на госта:"));
        panel.add(awayTeamGoalsField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Действия"));

        JButton addButton = new JButton("Добави");
        JButton updateButton = new JButton("Промени");
        JButton deleteButton = new JButton("Изтрий");

        addButton.addActionListener(e -> addMatch());
        updateButton.addActionListener(e -> updateMatch());
        deleteButton.addActionListener(e -> deleteMatch());

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    private void addMatch() {
        int homeTeamId = getTeamIdFromComboBox(homeTeamComboBox);
        int awayTeamId = getTeamIdFromComboBox(awayTeamComboBox);

        MatchDTO match = new MatchDTO(
            0,
            homeTeamId,
            awayTeamId,
            Integer.parseInt(homeTeamGoalsField.getText()),
            Integer.parseInt(awayTeamGoalsField.getText()),
            "",
            ""
        );

        matchDAO.addMatch(match);
        refreshMatchesTable();
        resetFields();
    }

    private void updateMatch() {
        int homeTeamId = getTeamIdFromComboBox(homeTeamComboBox);
        int awayTeamId = getTeamIdFromComboBox(awayTeamComboBox);

        MatchDTO match = new MatchDTO(
            Integer.parseInt(matchIdField.getText()),
            homeTeamId,
            awayTeamId,
            Integer.parseInt(homeTeamGoalsField.getText()),
            Integer.parseInt(awayTeamGoalsField.getText()),
            "",
            ""
        );

        matchDAO.updateMatch(match);
        refreshMatchesTable();
        resetFields();
    }

    private void deleteMatch() {
        matchDAO.deleteMatch(Integer.parseInt(matchIdField.getText()));
        refreshMatchesTable();
        resetFields();
    }

    private void refreshMatchesTable() {
        List<MatchDTO> matches = matchDAO.getAllMatches();
        matchesTable.setModel(new MatchModel(matches));
        utils.hideColumnOfTableByIndex(matchesTable, 0);
        utils.hideColumnOfTableByIndex(matchesTable, 1);
        utils.hideColumnOfTableByIndex(matchesTable, 2);
    }

    private void refreshTeamComboBoxes() {
        TeamDAO teamDAO = new TeamDAO();
        List<TeamDTO> teams = teamDAO.getAllTeams();

        homeTeamComboBox.removeAllItems();
        awayTeamComboBox.removeAllItems();

        for (TeamDTO team : teams) {
            homeTeamComboBox.addItem(team.getName());
            awayTeamComboBox.addItem(team.getName());
        }
    }

    private int getTeamIdFromComboBox(JComboBox<String> comboBox) {
        String teamName = comboBox.getSelectedItem().toString();
        TeamDAO teamDAO = new TeamDAO();
        return teamDAO.getTeamIdByName(teamName);
    }

    private void resetFields() {
        matchIdField.setText("");
        homeTeamComboBox.setSelectedIndex(-1);
        awayTeamComboBox.setSelectedIndex(-1);
        homeTeamGoalsField.setText("");
        awayTeamGoalsField.setText("");
    }

    class MouseActionMatchTable implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            int row = matchesTable.getSelectedRow();
            matchIdField.setText(matchesTable.getValueAt(row, 0).toString());
            // column idx 1: home team id
            // column idx 2: away team id
            // column idx 3: home team name
            // column idx 4: away team name
            // column idx 5: home team goals
            // column idx 6: away team goals
//            homeTeamComboBox.setSelectedItem(matchesTable.getValueAt(row, 1).toString());
//            homeTeamComboBox.setSelectedItem(matchesTable.getValueAt(row, 2).toString());

            homeTeamComboBox.setSelectedItem(matchesTable.getValueAt(row, 3).toString());
            awayTeamComboBox.setSelectedItem(matchesTable.getValueAt(row, 4).toString());

            homeTeamGoalsField.setText(matchesTable.getValueAt(row, 5).toString());
            awayTeamGoalsField.setText(matchesTable.getValueAt(row, 6).toString());

            utils.hideColumnOfTableByIndex(matchesTable, 0);
            utils.hideColumnOfTableByIndex(matchesTable, 1);
            utils.hideColumnOfTableByIndex(matchesTable, 2);
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }
    }
}