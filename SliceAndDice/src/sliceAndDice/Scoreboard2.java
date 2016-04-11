package sliceAndDice;
 
import java.io.*;
import java.util.Scanner;
import java.lang.Integer;
 
 
 
/**
 * Scoreboard2 reads and writes data to a file, computes statistics from the data, and loads
 * player objects with data. It contains the main method for the program.
 */
public class Scoreboard2{
 
     
    /**
     * contains a player's userID and username and get and set methods for them
     */
    class PlayerName{
        int userId; // ID of players used to simplify checking for player in players.txt
        String username; // username of players used to keep track of players
         
        /**
         * sets userId of player to given userId
         * @param newId new userId of player
         */
        void setUserId(int newId) {
            userId = newId;
        }
         
        /**
         * sets username of player to given username
         * @param newName newusername of player
         */
        void setUserName(String newName) {
            username = newName;
        }
         
        /**
         * returns userId of player
         * @return userId of player
         */
        int getUserId() {
            return userId;
        }
        /**
         * returns username of player
         * @return username of player
         */
        String getUserName() {
            return username;
        }
    }
     
     
    /**
     * contains data for players and a toString() method to aid writing to statistics.txt
     */
    class PlayerStats{
        String username;
        int numberOfWins;
        int numberOfGames;
        int numberOfAttacks;
        int numberOfSPAttacks;
        int numberOfMeals;
         
        /**
         * creates a formatted string using the username and data of the player
         * @return String of data in format needed to write to statistics.txt
         */
        public String playerStatsToString() {
            String stats = "";
            stats = stats + username + "\n";
            stats = stats + numberOfWins + "\n";
            stats = stats + numberOfGames + "\n";
            stats = stats + numberOfAttacks + "\n";
            stats = stats + numberOfSPAttacks + "\n";
            stats = stats + numberOfMeals + "\n";
            return stats;
        }
    }
     
     
    /**
     * prints out player's username and data
     * @param statistics PlayerStats object containing data to print
     */
    public void printPlayerStats(PlayerStats statistics) {
        System.out.print(statistics.playerStatsToString());
    }
     
    /**
     * reads player username and data from file and puts it into given PlayerStats object
     * @param statistics PlayerStats object to be updated
     * @param statsReader scanner to read data into PlayerStats object
     */
    public void readPlayerStats(PlayerStats statistics, Scanner statsReader) {
        statistics.username = statsReader.next();
        statistics.numberOfWins = statsReader.nextInt();
        statistics.numberOfGames = statsReader.nextInt();
        statistics.numberOfAttacks = statsReader.nextInt();
        statistics.numberOfSPAttacks = statsReader.nextInt();
        statistics.numberOfMeals = statsReader.nextInt();
    }
     
    /**
     * updates PlayerStats object with username and data from another PlayerStats object
     * @param oldStats PlayerStats object to be updated
     * @param newStats PlayerStats object to take username and data from
     */
    public void setPlayerStats(PlayerStats oldStats, PlayerStats newStats) {
        oldStats.username = newStats.username;
        oldStats.numberOfWins = newStats.numberOfWins;
        oldStats.numberOfGames = newStats.numberOfGames;
        oldStats.numberOfAttacks = newStats.numberOfAttacks;
        oldStats.numberOfSPAttacks = newStats.numberOfSPAttacks;
        oldStats.numberOfMeals = newStats.numberOfMeals;
    }
     
    /**
     * sets all of the data in a PlayerStats object to 0
     * @param oldStats PlayerStats object to be reset
     */
    public void resetPlayerStats(PlayerStats oldStats) {
        oldStats.numberOfWins = 0;
        oldStats.numberOfGames = 0;
        oldStats.numberOfAttacks = 0;
        oldStats.numberOfSPAttacks = 0;
        oldStats.numberOfMeals = 0;
    }
     
    /**
     * adds a new player's username and userId to players.txt and increments the numberOfPlayers
     * @param username the username to add to players.txt
     * @throws IOException
     */
    public void addPlayerToPlayers(String username) throws IOException{
        Scanner readPlayers = new Scanner(new BufferedReader(new FileReader("players.txt")));
        int numberOfPlayers = readPlayers.nextInt();
        if(numberOfPlayers == 0) { // no need to store data to rewrite
            try {
                FileWriter writer = new FileWriter("players.txt");
                BufferedWriter playerWriter = new BufferedWriter(writer);
                numberOfPlayers++;
                Integer numPlayers = numberOfPlayers; // allows conversion to string for writing
                playerWriter.write(numPlayers.toString());
                playerWriter.write("\n");
                playerWriter.write(numPlayers.toString()); // write userId as 1 because only 1 player
                playerWriter.write("\n");
                playerWriter.write(username);
                playerWriter.write("\n");
                playerWriter.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
        else {
            /* Store all current usernames and userIds in a directory to rewrite to file */
            PlayerName[] directory = new PlayerName[numberOfPlayers];
             
            for(int index = 0; index < numberOfPlayers; index++) {
                PlayerName playerName = new PlayerName();
                playerName.setUserId(readPlayers.nextInt());
                playerName.setUserName(readPlayers.next());
                directory[index] = playerName;
            }
             
            readPlayers.close();
            /* Add a new player */
            try {
                numberOfPlayers++;
                FileWriter writer = new FileWriter("players.txt");
                BufferedWriter playerWriter = new BufferedWriter(writer);
                Integer numPlayers = numberOfPlayers; // allows conversion to string for writing
                playerWriter.write(numPlayers.toString());
                playerWriter.write("\n"); // maintains format of players.txt
                 
                for(int index = 0; index < numberOfPlayers - 1; index++) { // - 1 because it was incremented
                    Integer nextId = directory[index].getUserId();
                    playerWriter.write(nextId.toString());
                    playerWriter.write("\n");
                    playerWriter.write(directory[index].getUserName());
                    playerWriter.write("\n");
                }
                 
                Integer lastId = numberOfPlayers;
                playerWriter.write(lastId.toString());
                playerWriter.write("\n");
                playerWriter.write(username);
                playerWriter.write("\n");
                playerWriter.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
        readPlayers.close();
    }
     
    /**
     * returns userId if the user with given username is in players.txt, otherwise returns -1
     * @param username the username entered by player and passed in from Display
     * @return userId if found, otherwise -1
     */
    public int checkPlayerInPlayers(String username) throws IOException{
        Scanner userFinder = new Scanner(new BufferedReader(new FileReader("players.txt")));
        int numberOfPlayers = userFinder.nextInt(); // read in number of players (first line in file);
        int playerId = -1; // should return -1 if numberOfPlayers is 0
        String playerName;
        boolean found = false;
        int iteration = 0;
         
        while((iteration < numberOfPlayers) && (!found)) {
            playerId = userFinder.nextInt();
            playerName = userFinder.next();
            if(playerName.equals(username) == true) {
                found = true;
            }
            else {
                iteration++;
                playerId = -1;
            }
        }
         
        userFinder.close();
        return playerId;
    }
     
    /**
     * adds new username with user data (0s) to end of statistics.txt and increments numberOfStats
     * @param username username of the player added to statistics.txt
     * @throws IOException
     */
    public void addNewPlayerStatsToStats(String username) throws IOException{
        Scanner statsReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        int numberOfPlayers = statsReader.nextInt();
        PlayerStats[] statsDirectory = new PlayerStats[numberOfPlayers];
         
        for(int count = 0; count < numberOfPlayers; count++) {
            PlayerStats tempPlayerStats = new PlayerStats();
            /* load temporary stats object with stats */
            readPlayerStats(tempPlayerStats, statsReader);
            /* add new stats object to directory */
            statsDirectory[count] = tempPlayerStats;
        }
         
        statsReader.close();
        /* rewrite the statistics file with user stats reset */
        FileWriter writer = new FileWriter("statistics.txt");
        BufferedWriter statsWriter = new BufferedWriter(writer);
        numberOfPlayers++;
        Integer numPlayers = numberOfPlayers;
        statsWriter.write(numPlayers.toString()); // write the number of players
        statsWriter.write("\n");
        for(int count = 0; count < numberOfPlayers - 1; count++) { // - 1 because numberOfPlayers was incremented
            statsWriter.write(statsDirectory[count].playerStatsToString()); // write player to statistics.txt
        }
        /* add new entry with 0s at end */
            Integer zero = 0;
            statsWriter.write(username);
            statsWriter.write("\n");
             
            for(int count = 0; count < 5; count++) {
                statsWriter.write(zero.toString());
                statsWriter.write("\n");
            }
             
        statsWriter.close();
    }
     
    /**
     * sets the data of the user with given username to 0
     * @param username username of user to have stats reset
     * @throws IOException
     */
    public void resetPlayerStatsInStats(String username) throws IOException{
        Scanner statsReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        int numberOfPlayers;
        boolean found = false; // used to determine if username is in statistics.txt
        numberOfPlayers = statsReader.nextInt();
        PlayerStats[] statsDirectory = new PlayerStats[numberOfPlayers];
         
        for(int count = 0; count < numberOfPlayers; count++) {
            PlayerStats tempPlayerStats = new PlayerStats();
            /* load temporary stats object with stats */
            readPlayerStats(tempPlayerStats, statsReader);
            /* add new stats object to directory */
            statsDirectory[count] = tempPlayerStats;
        }
         
        statsReader.close();
         
        /* update the stats of the two players */
        for(int index = 0; index < numberOfPlayers; index++) {
            if((statsDirectory[index].username).equals(username) == true) { // modify first player stats
                resetPlayerStats(statsDirectory[index]);
                found = true;
            }
        }
         
        if(found) {
            /* rewrite the statistics file with user stats reset */
            FileWriter writer = new FileWriter("statistics.txt");
            BufferedWriter statsWriter = new BufferedWriter(writer);
            Integer numPlayers = numberOfPlayers;
            statsWriter.write(numPlayers.toString()); // write the number of players
            statsWriter.write("\n");
             
            for(int count = 0; count < numberOfPlayers; count++) {
                statsWriter.write(statsDirectory[count].playerStatsToString());
            }
             
            statsWriter.close();
        }
        // else if username was not found in statistics.txt, do not make changes
    }
     
    /**
     * updates data of two players in statistics.txt with given data
     * @param stats1 PlayerStats object with data used to update first player data in statistics.txt
     * @param stats2 PlayerStats object with data used to update second player data in statistics.txt
     * @throws IOException
     */
    public void updateTwoPlayersStatsInStats(PlayerStats stats1, PlayerStats stats2) throws IOException {
        Scanner statsReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        int numberOfPlayers;
        numberOfPlayers = statsReader.nextInt();
        PlayerStats[] statsDirectory = new PlayerStats[numberOfPlayers];
         
        for(int count = 0; count < numberOfPlayers; count++) {
            PlayerStats tempPlayerStats = new PlayerStats();
            /* load temporary stats object with stats */
            readPlayerStats(tempPlayerStats, statsReader);
            /* add new stats object to directory */
            statsDirectory[count] = tempPlayerStats;
        }
         
        statsReader.close();
         
        /* update the stats of the two players */
        for(int index = 0; index < numberOfPlayers; index++) {
            if((statsDirectory[index].username).equals(stats1.username) == true) { // modify first player stats
                setPlayerStats(statsDirectory[index], stats1);
            }
            else if((statsDirectory[index].username).equals(stats2.username) == true) { // modify second player stats
                setPlayerStats(statsDirectory[index], stats2);
            }
        }
         
        /* rewrite the statistics file with updated user stats */
        FileWriter writer = new FileWriter("statistics.txt");
        BufferedWriter statsWriter = new BufferedWriter(writer);
        Integer numPlayers = numberOfPlayers;
        statsWriter.write(numPlayers.toString()); // write the number of players
        statsWriter.write("\n");
         
        for(int count = 0; count < numberOfPlayers; count++) {
            statsWriter.write(statsDirectory[count].playerStatsToString()); // write player to statistics.txt
        }
         
        statsWriter.close();
    }
     
    /**
     * returns PlayerStats object with data corresponding to user of given username
     * @param username username corresponding to data to be read into PlayerStats object
     * @return PlayerStats object with data of user given by username
     * @throws IOException
     */
    public PlayerStats readPlayerStatsFromStats(String username) throws IOException{
        int numberOfPlayers;
        boolean found = false;
        int iteration = 0;
        Scanner statReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        PlayerStats stats = new PlayerStats();
        numberOfPlayers = statReader.nextInt(); // read in the number of players
         
        while((iteration < numberOfPlayers) && (!found)) {
            readPlayerStats(stats, statReader);
            if((stats.username).equals(username) == true){
                found = true;
                statReader.close();
                return stats;
            }
            else {
                iteration++;
                // maybe make this below into its own function to save space
                stats.username = username;
                resetPlayerStats(stats);
            }
        }
         
        statReader.close();
        return stats;
    }
     
    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        int userId;
        Scoreboard2 scoreboard = new Scoreboard2();
        try {
            scoreboard.addPlayerToPlayers("user1");
        } catch (IOException e) {
            System.out.println(e);
        }
        userId = scoreboard.checkPlayerInPlayers("user1"); // this should return 1
        System.out.println(userId); // print out 1
        try {
            scoreboard.addPlayerToPlayers("user2");
            scoreboard.addPlayerToPlayers("user3");
            scoreboard.addPlayerToPlayers("user4");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}