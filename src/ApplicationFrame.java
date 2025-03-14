
import athletes.AthleteDAO;
import athletes.AthleteDTO;
import athletes.AthleteModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.*;

public class ApplicationFrame extends JFrame {

    private AthleteDAO athleteDAO = new AthleteDAO();
    private JTextField firstNameField = new JTextField();
    private JTextField lastNameField = new JTextField();
    private JTextField nationalityField = new JTextField();
    private JTextField positionField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField bornYearField = new JTextField();
    private JTextField teamField = new JTextField();
    private JTable athletesTable = new JTable();

    public ApplicationFrame() {
        initializeWindow();
        initializePanelsAndTabs();
        refreshAthletesTable();
        setVisible(true);
    }

    private void initializeWindow() {
        this.setSize(850, 700);
        this.setTitle("Footballers - OOP DB Project");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    private void initializePanelsAndTabs() {
        JPanel athletesPanel = new JPanel(new GridBagLayout());
        JTabbedPane tabs = new JTabbedPane();
        initializeAthletesPanel(athletesPanel);

        JPanel teamsPanel = new JPanel();
        JPanel matchesPanel = new JPanel();

        // tab options at the top
        tabs.add(athletesPanel, "Атлети");
        tabs.add(teamsPanel,"Екипи");
        tabs.add(matchesPanel,"Мачове");

        this.add(tabs);

        athletesTable.addMouseListener(new MouseActionAthleteTable());
    }

    class MouseActionAthleteTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = athletesTable.getSelectedRow();

            firstNameField.setText(athletesTable.getValueAt(row, 0).toString());
            lastNameField.setText(athletesTable.getValueAt(row, 1).toString());
            nationalityField.setText(athletesTable.getValueAt(row, 2).toString());
            positionField.setText(athletesTable.getValueAt(row, 3).toString());
            priceField.setText(athletesTable.getValueAt(row, 4).toString());
            bornYearField.setText(athletesTable.getValueAt(row, 5).toString());
            teamField.setText(athletesTable.getValueAt(row, 6).toString());
        }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }
    }

    private void initializeAthletesPanel(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        JPanel inputPanel = createInputPanel();
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        panel.add(inputPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        panel.add(buttonPanel, gbc);

        JPanel listPanel = new JPanel();
        JScrollPane athletesScroll = new JScrollPane(athletesTable);
        athletesScroll.setPreferredSize(new Dimension(800, 400));

        listPanel.add(athletesScroll);

        gbc.gridy = 2;
        gbc.weighty = 0.5;
        panel.add(listPanel, gbc);
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
        panel.add(teamField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Бутони за действие"));

        JButton addButton = new JButton("Добави");
        JButton deleteButton = new JButton("Изтрий");

        addButton.addActionListener(e -> addAthlete());
        deleteButton.addActionListener(e -> deleteAthlete());

        panel.add(addButton);
        panel.add(deleteButton);
        return panel;
    }

    private void addAthlete() {
        try {
            AthleteDTO athlete = new AthleteDTO(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    nationalityField.getText(),
                    positionField.getText(),
                    Double.parseDouble(priceField.getText()),
                    bornYearField.getText(),
                    Integer.parseInt(teamField.getText())
            );
            athleteDAO.addAthlete(athlete);

            refreshAthletesTable();

            firstNameField.setText("");
            lastNameField.setText("");
            nationalityField.setText("");
            positionField.setText("");
            priceField.setText("");
            bornYearField.setText("");
            teamField.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteAthlete() {
        try {
            AthleteDTO athlete = new AthleteDTO(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    nationalityField.getText(),
                    positionField.getText(),
                    Double.parseDouble(priceField.getText()),
                    bornYearField.getText(),
                    Integer.parseInt(teamField.getText())
            );
            athleteDAO.deleteAthlete(athlete);
            refreshAthletesTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void refreshAthletesTable() {
        try {
//            List athletes = (List) athleteDAO.getAllAthletes();
            java.util.List<AthleteDTO> athletes = athleteDAO.getAllAthletes();




            athletesTable.setModel(new AthleteModel(athletes));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
