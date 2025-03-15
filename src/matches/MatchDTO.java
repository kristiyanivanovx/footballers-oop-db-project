package matches;

public class MatchDTO {
    private final int matchId;
    private final int homeTeamId;
    private final int awayTeamId;
    private final int homeTeamGoals;
    private final int awayTeamGoals;
    private final String homeTeamName;
    private final String awayTeamName;

    public MatchDTO(
            int matchId,
            int homeTeamId,
            int awayTeamId,
            int homeTeamGoals,
            int awayTeamGoals,
            String homeTeamName,
            String awayTeamName) {
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }
}
