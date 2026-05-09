import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Instantiate the four team objects
        Team t1 = new Team("Team 1");
        Team t2 = new Team("Team 2");
        Team t3 = new Team("Team 3");
        Team t4 = new Team("Team 4");

        // Store all four teams in an array so we can loop through them
        Team[] teams = {t1, t2, t3, t4};

        // ArrayList to keep track of every game played this season
        ArrayList<Game> gamesPlayed = new ArrayList<Game>();

        Random rand = new Random();

        // Counter for consecutive freezing weeks - season ends when this hits 3
        int winterWeeks = 0;

        // Game ID starts at 1 and goes up by 1 for every game played
        int gameID = 1;

        // Variables to track temperature stats across the season
        double hottestTemp = 0;
        double totalTemp = 0;
        int gameWeeks = 0;


        // ---- SEASON LOOP ----
        // Keeps running until there are 3 consecutive weeks too cold to play
        while (winterWeeks < 3) {

            // Generate a random temperature between 0 and 100 degrees
            int temp = rand.nextInt(101);

            // If it is freezing, no game is played this week
            if (temp <= 32) {
                winterWeeks++;
                System.out.println("Too cold to play.");
                continue;  // skip the rest of this loop and go back to the top
            }

            // If we get here, it is warm enough to play
            // Reset the consecutive cold week counter
            winterWeeks = 0;

            // Update temperature tracking stats
            totalTemp += temp;
            gameWeeks++;
            if (temp > hottestTemp) {
                hottestTemp = temp;
            }


            // ---- SELECT TEAMS FOR GAME 1 ----
            // Team 4 (index 3) is always the home team for game 1
            Team home1 = teams[3];

            // Randomly pick one of the other three teams as the away team
            int awayIndex = rand.nextInt(3);
            Team away1 = teams[awayIndex];


            // ---- SELECT TEAMS FOR GAME 2 ----
            // The two teams that were NOT picked for game 1 will play game 2
            // We use a switch to figure out which two are left over
            Team home2 = null;
            Team away2 = null;

            switch (awayIndex) {
                case 0:
                    // Team 1 is in game 1, so game 2 is Team 2 vs Team 3
                    home2 = teams[1];
                    away2 = teams[2];
                    break;
                case 1:
                    // Team 2 is in game 1, so game 2 is Team 1 vs Team 3
                    home2 = teams[0];
                    away2 = teams[2];
                    break;
                case 2:
                    // Team 3 is in game 1, so game 2 is Team 1 vs Team 2
                    home2 = teams[0];
                    away2 = teams[1];
                    break;
            }


            // ---- GENERATE SCORES BASED ON TEMPERATURE ----
            // Hotter temperatures allow for higher possible scores
            Game game1 = null;
            Game game2 = null;

            if (temp <= 49) {
                // Max 5 goals per team
                game1 = new Game(gameID,     temp, away1, home1, rand.nextInt(6),  rand.nextInt(6));
                game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(6),  rand.nextInt(6));

            } else if (temp <= 66) {
                // Max 10 goals per team
                game1 = new Game(gameID,     temp, away1, home1, rand.nextInt(11), rand.nextInt(11));
                game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(11), rand.nextInt(11));

            } else if (temp <= 83) {
                // Max 15 goals per team
                game1 = new Game(gameID,     temp, away1, home1, rand.nextInt(16), rand.nextInt(16));
                game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(16), rand.nextInt(16));

            } else {
                // Max 20 goals per team (83 < temp <= 100)
                game1 = new Game(gameID,     temp, away1, home1, rand.nextInt(21), rand.nextInt(21));
                game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(21), rand.nextInt(21));
            }

            // Each week uses 2 game IDs, so increment by 2 for next week
            gameID += 2;


            // ---- UPDATE GOALS SCORED AND GOALS ALLOWED ----
            // A team's goals allowed = what the OTHER team scored against them

            // Game 1
            away1.setTotalGoalsScored(away1.getTotalGoalsScored()   + game1.getAwayScore());
            away1.setTotalGoalsAllowed(away1.getTotalGoalsAllowed()  + game1.getHomeScore());
            home1.setTotalGoalsScored(home1.getTotalGoalsScored()    + game1.getHomeScore());
            home1.setTotalGoalsAllowed(home1.getTotalGoalsAllowed()  + game1.getAwayScore());

            // Game 2
            away2.setTotalGoalsScored(away2.getTotalGoalsScored()   + game2.getAwayScore());
            away2.setTotalGoalsAllowed(away2.getTotalGoalsAllowed()  + game2.getHomeScore());
            home2.setTotalGoalsScored(home2.getTotalGoalsScored()    + game2.getHomeScore());
            home2.setTotalGoalsAllowed(home2.getTotalGoalsAllowed()  + game2.getAwayScore());


            // Add both games to our season list
            gamesPlayed.add(game1);
            gamesPlayed.add(game2);

            // Record wins, losses, and ties for both games
            recordWLT(game1);
            recordWLT(game2);

        } // end while loop


        // ---- PRINT RESULTS ----
        System.out.println("Season is over.\n");
        System.out.println("*********RESULTS*********\n\n");

        // Print each team's season stats using an enhanced for loop
        for (Team team : teams) {
            team.printStats();
        }

        // Print each game's stats using an enhanced for loop
        for (Game game : gamesPlayed) {
            game.printStats();
        }

        // Print temperature summary
        System.out.println("Hottest Temp: " + (int) hottestTemp);
        if (gameWeeks > 0) {
            System.out.printf("Average Temp: %.1f%n", totalTemp / gameWeeks);
        }

    } // end main


    // ---- HELPER METHOD ----
    // Determines the winner of a game and updates both teams' win/loss/tie totals
    // We made this its own method so we don't have to write the same logic twice
    public static void recordWLT(Game game) {

        if (game.getAwayScore() > game.getHomeScore()) {
            // Away team wins
            game.getAway().setWinTotal(game.getAway().getWinTotal() + 1);
            game.getHome().setLossTotal(game.getHome().getLossTotal() + 1);

        } else if (game.getHomeScore() > game.getAwayScore()) {
            // Home team wins
            game.getHome().setWinTotal(game.getHome().getWinTotal() + 1);
            game.getAway().setLossTotal(game.getAway().getLossTotal() + 1);

        } else {
            // Scores are equal - it's a tie
            game.getAway().setTieTotal(game.getAway().getTieTotal() + 1);
            game.getHome().setTieTotal(game.getHome().getTieTotal() + 1);
        }
    }

}4
