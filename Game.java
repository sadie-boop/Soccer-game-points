public class Game {

    // Private data fields for one game
    private int id;
    private int temp;
    private Team away;
    private Team home;
    private int awayScore;
    private int homeScore;

    // Constructor - called when we instantiate a new Game object
    public Game(int id, int temp, Team away, Team home, int awayScore, int homeScore) {
        this.id = id;
        this.temp = temp;
        this.away = away;
        this.home = home;
        this.awayScore = awayScore;
        this.homeScore = homeScore;
    }

    // ---------- Getters and Setters ----------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    // Prints this game's results to the console
    public void printStats() {
        System.out.println("Game #" + id);
        System.out.println("Temperature: " + temp);
        System.out.println("Away Team: " + away.getName() + ", " + awayScore);
        System.out.println("Home Team: " + home.getName() + ", " + homeScore);
        System.out.println();
    }
}
