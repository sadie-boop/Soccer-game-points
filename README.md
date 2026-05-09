# Soccer League — Section 8 Practice

**Course:** CSC 1060 – Computer Science I (Java)
**Instructor:** Jeremy Holley
**Semester:** Spring 2026

---

## Team Properties

* String name
* int winTotal
* int lossTotal
* int tieTotal
* int totalGoalsScored
* int totalGoalsAllowed

## Game Properties

* int id
* int temp
* Team away
* Team home
* int awayScore
* int homeScore

---

## What Did I Learn

### Multi-Class Design (Three Classes Working Together)

* This project required three separate classes — `Team`, `Game`, and `Main` — each with its own responsibility instead of dumping everything into one file
* `Team` is the blueprint for a team object; `Game` is the blueprint for a single game; `Main` is the scheduler that drives the whole season
* The `Team` and `Game` fields are object references inside `Main` — meaning when you update a team through a `Game` object, it updates the original team in memory automatically, you don't have to copy anything
* This is the same encapsulation idea from the Restaurant project — all fields private, all access goes through getters and setters

### ArrayLists vs Arrays — Using Both at the Same Time

* Used a regular fixed-size array (`Team[] teams`) for the four teams because the number of teams never changes — it's always 4
* Used an `ArrayList<Game>` called `gamesPlayed` for the games because we don't know how many games will be played — the season length is random, so the list needs to grow as we go
* Every time two games get played, both `Game` objects get added to `gamesPlayed` with `.add()`
* At the end, looped through both the array and the ArrayList with enhanced for-each loops to print all the stats

### The While Loop — When You Don't Know How Many Times to Run

* Used a `while` loop instead of a `for` loop because the season has no set length — it just keeps going until winter shuts it down
* The condition is `while (winterWeeks < 3)` — as soon as 3 consecutive freezing weeks happen, the loop stops
* If it's too cold to play, `winterWeeks` goes up by 1 and `continue` skips the rest of that iteration and sends the program right back to the top
* If a warm week happens, `winterWeeks` resets to 0 — the consecutive count breaks and the season keeps going

### The Switch Statement — Figuring Out Game 2 Teams

* Team 4 is always the home team for game 1; the away team is randomly picked from teams 1, 2, or 3 using `rand.nextInt(3)`
* The `switch` statement on that random index figures out which two teams are left over for game 2
* This guarantees no team ever plays itself, and all four teams play every single week

### Random Number Generation — nextInt() vs nextDouble()

* Used `rand.nextInt(101)` for temperature — gives a whole number between 0 and 100, which made the temperature checks cleaner
* Used `rand.nextInt(max + 1)` for scores — the `+ 1` is important because `nextInt(n)` goes from 0 up to but not including `n`, so `nextInt(6)` gives 0 through 5, but `nextInt(6)` with the bound of 6 is what gives you the 0–5 range the assignment wanted
* Temperature controls the max goals possible — hotter weather means more offense, so the score ceiling goes up in four ranges (max 5, 10, 15, or 20) depending on the temp

### Extracting Logic Into a Helper Method — recordWLT()

* The win/loss/tie logic is the same for both games every week — so instead of writing it twice, I pulled it into its own static method called `recordWLT(Game game)`
* It takes a `Game` object, compares the two scores, and calls the right setter on whichever team won, lost, or tied
* This is the same idea as reducing redundant code we talked about in class — if you're copying and pasting the same block, it should probably be a method

### Goals Scored vs Goals Allowed — Getting the Logic Right

* Goals allowed for a team is NOT their own score — it's what the other team scored against them
* So after each game, the away team's goals allowed increases by the home team's score, and the home team's goals allowed increases by the away team's score
* Both fields get updated using the same pattern from the Restaurant project — `team.setSomething(team.getSomething() + newValue)` to add onto the running total instead of replacing it

### Tracking Stats Across the Whole Season

* Three extra variables track temperature across the season: `hottestTemp`, `totalTemp`, and `gameWeeks`
* Every warm week, `totalTemp` adds the current temp and `gameWeeks` goes up by 1 — so at the end, average temp is just `totalTemp / gameWeeks`
* `hottestTemp` updates with a simple `if` — if the current temp is higher than what's stored, replace it
