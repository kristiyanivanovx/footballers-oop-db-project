package athletes;

import database.DatabaseConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO {
    private final Connection connection;

    public AthleteDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void updateAthlete(AthleteDTO athlete) {
        String sql = "UPDATE Athletes SET FirstName=?, LastName=?, Nationality=?, Position=?, Price=?, DateBorn=?, TeamId=? WHERE AthleteId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, athlete.getFirstName());
            statement.setString(2, athlete.getLastName());
            statement.setString(3, athlete.getNationality());
            statement.setString(4, athlete.getPosition());
            statement.setDouble(5, athlete.getPrice());
            statement.setString(6, athlete.getDateBorn());
            statement.setInt(7, athlete.getTeamId());
            statement.setInt(8, athlete.getAthleteId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<AthleteDTO> searchAthletesByNameAndTeam(String athleteName, String teamName) {
        List<AthleteDTO> athletes = new ArrayList<>();
        String sql = "SELECT A.AthleteId, A.FirstName, A.LastName, A.Nationality, A.Position, A.Price, A.DateBorn, A.TeamId, T.Name " +
                "FROM Athletes A JOIN Teams T ON A.TeamId = T.TeamId WHERE A.FirstName=? AND T.Name=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, athleteName);
            statement.setString(2, teamName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                athletes.add(new AthleteDTO(
                        resultSet.getInt("AthleteId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Nationality"),
                        resultSet.getString("Position"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("DateBorn"),
                        resultSet.getInt("TeamId"),
                        resultSet.getString("Name")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return athletes;
    }

    public void addAthlete(AthleteDTO athlete) {
        String sql = "INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, athlete.getFirstName());
            statement.setString(2, athlete.getLastName());
            statement.setString(3, athlete.getNationality());
            statement.setString(4, athlete.getPosition());
            statement.setDouble(5, athlete.getPrice());
            statement.setString(6, athlete.getDateBorn());
            statement.setInt(7, athlete.getTeamId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAthlete(int athleteId) {
        String sql = "DELETE FROM Athletes WHERE AthleteId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, athleteId);

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getTeams(JComboBox<String> comboBox) {
        String sql = "SELECT TeamId, Name FROM Teams";
//        String item = "";
        int teamId = -1;

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                teamId = Integer.parseInt(resultSet.getObject(1).toString());
                do {
                    String item = resultSet.getObject(2).toString();
                    comboBox.addItem(item);
                } while(resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamId;
    }

    public int getTeamId(String teamName) {
        String sql = "SELECT * FROM Teams WHERE Name =?";
        int teamId = -1;

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                teamId = Integer.parseInt(resultSet.getObject(1).toString());
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return teamId;
    }

    public List<AthleteDTO> getAllAthletes() {
        List<AthleteDTO> athletes = new ArrayList<>();
        String sql = "SELECT A.AthleteId, A.FirstName, A.LastName, A.Nationality, A.Position, A.Price, A.DateBorn, A.TeamId, T.Name " +
                "FROM Athletes AS A JOIN Teams AS T ON A.TeamId = T.TeamId";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                athletes.add(new AthleteDTO(
                    resultSet.getInt("AthleteId"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Nationality"),
                    resultSet.getString("Position"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("DateBorn"),
                    resultSet.getInt("TeamId"),
                    resultSet.getString("Name")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return athletes;
    }
}
