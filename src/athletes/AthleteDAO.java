package athletes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseConnection;

public class AthleteDAO {
    private Connection connection;

    public AthleteDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addAthlete(AthleteDTO athlete) throws SQLException {
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

    public void deleteAthlete(AthleteDTO athlete) throws SQLException {
        String sql = "DELETE TOP(1) FROM Athletes WHERE FirstName=? AND LastName=? AND Nationality=? AND Position=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, athlete.getFirstName());
            statement.setString(2, athlete.getLastName());
            statement.setString(3, athlete.getNationality());
            statement.setString(4, athlete.getPosition());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<AthleteDTO> getAllAthletes() throws SQLException {
        List<AthleteDTO> athletes = new ArrayList<>();
        String sql = "SELECT FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId FROM Athletes";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                athletes.add(new AthleteDTO(
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Nationality"),
                        resultSet.getString("Position"),
                        resultSet.getDouble("Price"),
                        resultSet.getString("DateBorn"),
                        resultSet.getInt("TeamId")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return athletes;
    }
}
