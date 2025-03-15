package teams;

public class TeamDTO {
    private final int teamId;
    private final String name;
    private final String dateFound;
    private final double earnings;

    public TeamDTO(int teamId, String name, String dateFound, double earnings) {
        this.teamId = teamId;
        this.name = name;
        this.dateFound = dateFound;
        this.earnings = earnings;
    }

    public int getTeamId() { return teamId; }
    public String getName() { return name; }
    public String getDateFound() { return dateFound; }
    public double getEarnings() { return earnings; }
}