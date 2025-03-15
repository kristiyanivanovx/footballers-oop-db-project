package teams;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private final Connection connection;

    public TeamDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<TeamDTO> getAllTeams() {
        List<TeamDTO> teams = new ArrayList<>();
        String sql = "SELECT TeamId, Name, DateFound, Earnings FROM Teams";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                teams.add(new TeamDTO(
                    resultSet.getInt("TeamId"),
                    resultSet.getString("Name"),
                    resultSet.getString("DateFound"),
                    resultSet.getDouble("Earnings")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return teams;
    }

    public void addTeam(TeamDTO team) {
        String sql = "INSERT INTO Teams (Name, DateFound, Earnings) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getDateFound());
            statement.setDouble(3, team.getEarnings());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTeam(TeamDTO team) {
        String sql = "UPDATE Teams SET Name=?, DateFound=?, Earnings=? WHERE TeamId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getName());
            statement.setString(2, team.getDateFound());
            statement.setDouble(3, team.getEarnings());
            statement.setInt(4, team.getTeamId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTeam(int teamId) {
        String sql = "DELETE FROM Teams WHERE TeamId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teamId);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}