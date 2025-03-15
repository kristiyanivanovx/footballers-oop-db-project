package matches;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    private final Connection connection;

    public MatchDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<MatchDTO> getAllMatches() {
        List<MatchDTO> matches = new ArrayList<>();
        String sql =
            "SELECT M.MatchId, M.HomeTeamId, M.AwayTeamId, M.HomeTeamGoals, M.AwayTeamGoals, " +
            "HT.Name AS HomeTeamName, AT.Name AS AwayTeamName " +
            "FROM Matches AS M JOIN Teams AS HT ON M.HomeTeamId = HT.TeamId " +
            "JOIN Teams AS AT ON M.AwayTeamId = AT.TeamId";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                matches.add(new MatchDTO(
                    resultSet.getInt("MatchId"),
                    resultSet.getInt("HomeTeamId"),
                    resultSet.getInt("AwayTeamId"),
                    resultSet.getInt("HomeTeamGoals"),
                    resultSet.getInt("AwayTeamGoals"),
                    resultSet.getString("HomeTeamName"),
                    resultSet.getString("AwayTeamName")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return matches;
    }

    public void addMatch(MatchDTO match) {
        String sql = "INSERT INTO Matches (HomeTeamId, AwayTeamId, HomeTeamGoals, AwayTeamGoals) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, match.getHomeTeamId());
            statement.setInt(2, match.getAwayTeamId());
            statement.setInt(3, match.getHomeTeamGoals());
            statement.setInt(4, match.getAwayTeamGoals());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateMatch(MatchDTO match) {
        String sql = "UPDATE Matches SET HomeTeamId=?, AwayTeamId=?, HomeTeamGoals=?, AwayTeamGoals=? WHERE MatchId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, match.getHomeTeamId());
            statement.setInt(2, match.getAwayTeamId());
            statement.setInt(3, match.getHomeTeamGoals());
            statement.setInt(4, match.getAwayTeamGoals());
            statement.setInt(5, match.getMatchId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteMatch(int matchId) {
        String sql = "DELETE FROM Matches WHERE MatchId=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, matchId);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
