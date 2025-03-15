
import athletes.AthletePanel;
import teams.TeamPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        initializeWindow();
        initializePanelsAndTabs();
        setVisible(true);
    }

    private void initializeWindow() {
        this.setSize(900, 890);
        this.setTitle("Footballers - OOP DB Project");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
    }

    private void initializePanelsAndTabs() {
        JTabbedPane tabs = new JTabbedPane();

        JPanel searchPanel = new JPanel(new GridBagLayout());
        AthletePanel athletesPanel = new AthletePanel(searchPanel);
        TeamPanel teamsPanel = new TeamPanel(athletesPanel);
        JPanel matchesPanel = new JPanel();

        tabs.add(athletesPanel, "Атлети");
        tabs.add(searchPanel, "Търсене");
        tabs.add(teamsPanel, "Екипи");
        tabs.add(matchesPanel, "Мачове");

        this.add(tabs);
    }
}
