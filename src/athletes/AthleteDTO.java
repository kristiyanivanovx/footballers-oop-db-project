package athletes;

public class AthleteDTO {
    private final int athleteId;
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final String position;
    private final double price;
    private final String dateBorn;
    private final int teamId;
    private final String teamName;

    public AthleteDTO(
            int athleteId,
            String firstName,
            String lastName,
            String nationality,
            String position,
            double price,
            String dateBorn,
            int teamId,
            String teamName)
    {
        this.athleteId = athleteId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.position = position;
        this.price = price;
        this.dateBorn = dateBorn;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getAthleteId() { return athleteId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationality() { return nationality; }
    public String getPosition() { return position; }
    public double getPrice() { return price; }
    public String getDateBorn() { return dateBorn; }
    public int getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
}
