package athletes;

public class AthleteDTO {
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final String position;
    private final double price;
    private final String dateBorn;
    private final int teamId;

    public AthleteDTO(
            String firstName,
            String lastName,
            String nationality,
            String position,
            double price,
            String dateBorn,
            int teamId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.position = position;
        this.price = price;
        this.dateBorn = dateBorn;
        this.teamId = teamId;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNationality() { return nationality; }
    public String getPosition() { return position; }
    public double getPrice() { return price; }
    public String getDateBorn() { return dateBorn; }
    public int getTeamId() { return teamId; }
}
