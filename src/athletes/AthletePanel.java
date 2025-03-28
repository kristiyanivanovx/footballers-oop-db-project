package athletes;

import common.Utils;
import teams.TeamDAO;
import teams.TeamDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class AthletePanel extends JPanel {
    private final AthleteDAO athleteDAO = new AthleteDAO();

    private final JTextField athleteIdTextField = new JTextField();
    private final JTextField firstNameField = new JTextField();
    private final JTextField lastNameField = new JTextField();
    private final JTextField nationalityField = new JTextField();
    private final JTextField positionField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JTextField bornYearField = new JTextField();
    private final JComboBox<String> teamNameComboBox = new JComboBox<>();

    // Athletes - List All
    private final JTable athletesTable = new JTable();

    // Athletes - Search
    private final JTable athletesSearchTable = new JTable();
    private final JTextField searchNameField = new JTextField();
    private final JTextField searchTeamField = new JTextField();

    private final Utils utils = new Utils();
    private int teamId;

    public AthletePanel(JPanel searchPanel) {
        setLayout(new GridBagLayout());
        initializeAthletePanels();
        initializeSearchPanel(searchPanel);
        refreshAthletesTable();
        refreshTeamComboBox();
    }

    private void searchAthletes(String athleteName, String teamName) {
        List<AthleteDTO> athletes = athleteDAO.searchAthletesByNameAndTeam(athleteName, teamName);
        athletesSearchTable.setModel(new AthleteModel(athletes));

        utils.hideColumnOfTableByIndex(athletesSearchTable, 0);
        utils.hideColumnOfTableByIndex(athletesSearchTable, 7);
    }

    private JPanel createSearchInputPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Търсене на атлети по име и екип"));

        panel.add(new JLabel("Име на атлет:"));
        panel.add(searchNameField);

        panel.add(new JLabel("Име на екип:"));
        panel.add(searchTeamField);

        return panel;
    }

    private void initializeSearchPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        JPanel searchInputPanel = createSearchInputPanel();
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        panel.add(searchInputPanel, gbc);

        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setBorder(BorderFactory.createTitledBorder("Действия"));

        JButton searchButton = new JButton("Търси");
        searchButton.addActionListener(e -> searchAthletes(searchNameField.getText(), searchTeamField.getText()));

        gbc.gridy = 1;
        gbc.weighty = 0.1;
        searchButtonPanel.add(searchButton);
        panel.add(searchButtonPanel, gbc);

        JPanel searchResultPanel = new JPanel();
        JScrollPane searchScroll = new JScrollPane(athletesSearchTable);
        searchScroll.setPreferredSize(new Dimension(800, 400));

        searchResultPanel.add(searchScroll);

        gbc.gridy = 2;
        gbc.weighty = 0.8;
        panel.add(searchResultPanel, gbc);
    }

    private void initializeAthletePanels() {
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
        JScrollPane athletesScroll = new JScrollPane(athletesTable);
        athletesScroll.setPreferredSize(new Dimension(800, 400));

        listPanel.add(athletesScroll);

        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(listPanel, gbc);

        athletesTable.addMouseListener(new MouseActionAthleteTable());
        teamNameComboBox.addActionListener(e -> {
            Object selectedItem = teamNameComboBox.getSelectedItem();
            if (selectedItem != null) {
                String teamName = selectedItem.toString();
                teamId = athleteDAO.getTeamId(teamName);
            }
        });
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Добавяне / редактиране на атлет"));

        panel.add(new JLabel("Име:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Фамилия:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Националност:"));
        panel.add(nationalityField);

        panel.add(new JLabel("Позиция:"));
        panel.add(positionField);

        panel.add(new JLabel("Цена:"));
        panel.add(priceField);

        panel.add(new JLabel("Дата на раждане:"));
        panel.add(bornYearField);

        panel.add(new JLabel("Екип:"));
        panel.add(teamNameComboBox);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Действия"));

        JButton addButton = new JButton("Добави");
        JButton updateButton = new JButton("Промени");
        JButton deleteButton = new JButton("Изтрий");

        addButton.addActionListener(e -> addAthlete());
        updateButton.addActionListener(e -> updateAthlete());
        deleteButton.addActionListener(e -> deleteAthlete());

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        return panel;
    }

    private void addAthlete() {
        String selectedTeamName = teamNameComboBox.getSelectedItem().toString();
        int teamId = athleteDAO.getTeamId(selectedTeamName);

        AthleteDTO athlete = new AthleteDTO(
            0,
            firstNameField.getText(),
            lastNameField.getText(),
            nationalityField.getText(),
            positionField.getText(),
            Double.parseDouble(priceField.getText()),
            bornYearField.getText(),
            teamId,
            selectedTeamName
        );

        athleteDAO.addAthlete(athlete);

        refreshAthletesTable();
        resetAthleteFields();
    }

    private void updateAthlete() {
        String selectedTeamName = teamNameComboBox.getSelectedItem().toString();
        int teamId = athleteDAO.getTeamId(selectedTeamName);

        AthleteDTO athlete = new AthleteDTO(
            Integer.parseInt(athleteIdTextField.getText()),
            firstNameField.getText(),
            lastNameField.getText(),
            nationalityField.getText(),
            positionField.getText(),
            Double.parseDouble(priceField.getText()),
            bornYearField.getText(),
            teamId,
            selectedTeamName
        );

        athleteDAO.updateAthlete(athlete);

        refreshAthletesTable();
        resetAthleteFields();
    }

    private void deleteAthlete() {
        athleteDAO.deleteAthlete(Integer.parseInt(athleteIdTextField.getText()));

        refreshAthletesTable();
        resetAthleteFields();
    }

    private void refreshAthletesTable() {
        List<AthleteDTO> athletes = athleteDAO.getAllAthletes();

        athletesTable.setModel(new AthleteModel(athletes));
        utils.hideColumnOfTableByIndex(athletesTable, 0);
        utils.hideColumnOfTableByIndex(athletesTable, 7);
    }

    public void refreshTeamComboBox() {
        teamNameComboBox.removeAllItems();
        TeamDAO teamDAO = new TeamDAO();
        List<TeamDTO> teams = teamDAO.getAllTeams();

        for (TeamDTO team : teams) {
            teamNameComboBox.addItem(team.getName());
        }
    }

    private void resetAthleteFields() {
        athleteIdTextField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        nationalityField.setText("");
        positionField.setText("");
        priceField.setText("");
        bornYearField.setText("");
    }


    class MouseActionAthleteTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = athletesTable.getSelectedRow();

            athleteIdTextField.setText(athletesTable.getValueAt(row, 0).toString());
            firstNameField.setText(athletesTable.getValueAt(row, 1).toString());
            lastNameField.setText(athletesTable.getValueAt(row, 2).toString());
            nationalityField.setText(athletesTable.getValueAt(row, 3).toString());
            positionField.setText(athletesTable.getValueAt(row, 4).toString());
            priceField.setText(athletesTable.getValueAt(row, 5).toString());
            bornYearField.setText(athletesTable.getValueAt(row, 6).toString());
            String teamName = athletesTable.getValueAt(row, 8).toString();
            teamNameComboBox.setSelectedItem(teamName);
            teamId = athleteDAO.getTeamId(teamName);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}