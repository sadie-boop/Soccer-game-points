public class Team {

    // Private data fields - nobody outside this class can change these directly
    private String name;
    private int winTotal;
    private int lossTotal;
    private int tieTotal;
    private int totalGoalsScored;
    private int totalGoalsAllowed;

    // Constructor - called when we instantiate a new Team object
    public Team(String name) {
        this.name = name;
    }

    // ---------- Getters and Setters ----------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinTotal() {
        return winTotal;
    }

    public void setWinTotal(int winTotal) {
        this.winTotal = winTotal;
    }

    public int getLossTotal() {
        return lossTotal;
    }

    public void setLossTotal(int lossTotal) {
        this.lossTotal = lossTotal;
    }

    public int getTieTotal() {
        return tieTotal;
    }

    public void setTieTotal(int tieTotal) {
        this.tieTotal = tieTotal;
    }

    public int getTotalGoalsScored() {
        return totalGoalsScored;
    }

    public void setTotalGoalsScored(int totalGoalsScored) {
        this.totalGoalsScored = totalGoalsScored;
    }

    public int getTotalGoalsAllowed() {
        return totalGoalsAllowed;
    }

    public void setTotalGoalsAllowed(int totalGoalsAllowed) {
        this.totalGoalsAllowed = totalGoalsAllowed;
    }

    // Prints this team's full season statistics to the console
    public void printStats() {
        System.out.println(name);
        System.out.println("Wins: " + winTotal + ", Losses: " + lossTotal + ", Ties: " + tieTotal);
        System.out.println("Points Scored: " + totalGoalsScored + ", Points Allowed: " + totalGoalsAllowed);
        System.out.println();
    }
}
