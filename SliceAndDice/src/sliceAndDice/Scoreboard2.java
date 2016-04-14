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
         * @return userId of player
         */
        int getUserId() {
            return userId;
        }
        /**
         * @return username of player
         */
        String getUserName() {
            return username;
        }
    }
         
    	/**
    	 * converts the given data object to string and returns it
    	 * @param data data to be converted to string
    	 * @param player player whose username is read
    	 * @return String containing player's username and data on separate lines
    	 */
        public String scoreboardDataToString(Data data, Player player) {
            String dataString = "";
            dataString = dataString + player.getUsername() + "\n";
            dataString = dataString + data.getGame() + "\n";
            dataString = dataString + data.getWin() + "\n";
            dataString = dataString + data.getTurn() + "\n";
            dataString = dataString + data.getAttack() + "\n";
            //dataString = dataString + data.getSPAttack() + "\n";
            dataString = dataString + data.getMeal() + "\n";
            dataString = dataString + data.getHPLost() + "\n";
            dataString = dataString + data.getManaUsed() + "\n";
            dataString = dataString + data.getFoodUsed() + "\n";
            return dataString;
        }
     
    /**
     * @param data data to print
     * @param player player with username to print
     */
    public void printData(Data data, Player player) {
        System.out.print(scoreboardDataToString(data, player));
    }
     
    /**
     * reads username and data and stores them in given data and player objects
     * @param data object to be modified with data from file
     * @param player object to be modified with username from file
     * @param dataReader scanner to read username and data from file
     */
    public void readData(Data data, Player player, Scanner dataReader) {
    	player.setUsername(dataReader.next());
    	data.setGame(dataReader.nextInt());
    	data.setWin(dataReader.nextInt());
    	data.setTurn(dataReader.nextInt());
        data.setAttack(dataReader.nextInt());
        // data.setSPAttack(dataReader.nextInt());
        data.setMeal(dataReader.nextInt());
        data.setHPLost(dataReader.nextInt());
        data.setManaUsed(dataReader.nextInt());
        data.setFoodUsed(dataReader.nextInt());
        // totalTime
        // turnTime
    }
     
    /**
     * updates PlayerStats object with username and data from another PlayerStats object
     * @param oldStats PlayerStats object to be updated
     * @param newStats PlayerStats object to take username and data from
     */
    public void setData(Data oldData, Data newData) {
        oldData.setGame(newData.getGame());
        oldData.setWin(newData.getWin());
        oldData.setTurn(newData.getTurn());
        oldData.setAttack(newData.getAttack());
        //oldData.setSPAttack(newData.getSPAttack());
        oldData.setMeal(newData.getMeal());
        oldData.setHPLost(newData.getHPLost());
        oldData.setManaUsed(newData.getManaUsed());
        oldData.setFoodUsed(newData.getFoodUsed());
    }
     
    /**
     * sets all of the data in a PlayerStats object to 0
     * @param oldStats PlayerStats object to be reset
     */
    public void resetData(Data data) {
        data.setGame(0);
        data.setWin(0);
        data.setTurn(0);
        data.setAttack(0);
        //data.setSPAttack(0);
        data.setMeal(0);
        data.setHPLost(0);
        data.setManaUsed(0);
        data.setFoodUsed(0);
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
                BufferedWriter playerNameWriter = new BufferedWriter(writer);
                numberOfPlayers++;
                Integer numPlayers = numberOfPlayers; // allows conversion to string for writing
                playerNameWriter.write(numPlayers.toString());
                playerNameWriter.write("\n");
                playerNameWriter.write(numPlayers.toString()); // userId is 1 because only 1 player
                playerNameWriter.write("\n");
                playerNameWriter.write(username);
                playerNameWriter.write("\n");
                playerNameWriter.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
        else {
            /* Store all current usernames and userIds into a directory to rewrite to file */
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
    public void addNewDataToData(String username) throws IOException{
        Scanner statsReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        int numberOfPlayers = statsReader.nextInt();
        Player[] playerDirectory = new Player[numberOfPlayers];
        Data[] dataDirectory = new Data[numberOfPlayers];
        
        for(int count = 0; count < numberOfPlayers; count++) {
            Data tempData = new Data();
            Player tempPlayer = new Player();
            /* load temporary data, player objects with data from file */
            readData(tempData, tempPlayer, statsReader);
            /* add new data, player objects to directories */
            dataDirectory[count] = tempData;
            playerDirectory[count] = tempPlayer;
        }
         
        statsReader.close();
        /* rewrite the statistics file with user stats reset */
        FileWriter writer = new FileWriter("statistics.txt");
        BufferedWriter dataWriter = new BufferedWriter(writer);
        numberOfPlayers++;
        Integer numPlayers = numberOfPlayers;
        dataWriter.write(numPlayers.toString()); // write the number of players
        dataWriter.write("\n");
        for(int count = 0; count < numberOfPlayers - 1; count++) { // - 1 because numberOfPlayers was incremented
            dataWriter.write(scoreboardDataToString(dataDirectory[count], playerDirectory[count])); // write player to statistics.txt
        }
        /* add new entry with 0s at end */
            Integer zero = 0;
            dataWriter.write(username);
            dataWriter.write("\n");
             
            for(int count = 0; count < 5; count++) {
                dataWriter.write(zero.toString());
                dataWriter.write("\n");
            }
             
        dataWriter.close();
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
        Data[] dataDirectory = new Data[numberOfPlayers];
        Player[] playerDirectory = new Player[numberOfPlayers];
         
        for(int count = 0; count < numberOfPlayers; count++) {
            Data tempData = new Data();
            Player tempPlayer = new Player();
            /* load temporary stats object with stats */
            readData(tempData, tempPlayer, statsReader);
            /* add new stats object to directory */
            dataDirectory[count] = tempData;
            playerDirectory[count] = tempPlayer;
        }
         
        statsReader.close();
         
        /* update the stats of the two players */
        for(int index = 0; index < numberOfPlayers; index++) {
            if((playerDirectory[index].getUsername()).equals(username) == true) { // modify first player stats
                resetData(dataDirectory[index]);
                found = true;
            }
        }
         
        if(found) {
            /* rewrite the statistics file with user stats reset */
            FileWriter writer = new FileWriter("statistics.txt");
            BufferedWriter dataWriter = new BufferedWriter(writer);
            Integer numPlayers = numberOfPlayers;
            dataWriter.write(numPlayers.toString()); // write the number of players
            dataWriter.write("\n");
             
            for(int count = 0; count < numberOfPlayers; count++) {
                dataWriter.write(scoreboardDataToString(dataDirectory[count], playerDirectory[count]));
            }
             
            dataWriter.close();
        }
        // else if username was not found in statistics.txt, do not make changes
    }
     
    /**
     * updates data of two players in statistics.txt with given data
     * @param stats1 PlayerStats object with data used to update first player data in statistics.txt
     * @param stats2 PlayerStats object with data used to update second player data in statistics.txt
     * @throws IOException
     */
    public void updateTwoPlayersStatsInStats(Data data1, Player player1, Data data2, Player player2) throws IOException {
        Scanner statsReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        int numberOfPlayers;
        numberOfPlayers = statsReader.nextInt();
        Data[] dataDirectory = new Data[numberOfPlayers];
        Player[] playerDirectory = new Player[numberOfPlayers];
         
        for(int count = 0; count < numberOfPlayers; count++) {
            Data tempData = new Data();
            Player tempPlayer = new Player();
            /* load temporary stats object with stats */
            readData(tempData, tempPlayer, statsReader);
            /* add new stats object to directory */
            dataDirectory[count] = tempData;
            playerDirectory[count] = tempPlayer;
        }
         
        statsReader.close();
         
        /* update the stats of the two players */
        for(int index = 0; index < numberOfPlayers; index++) {
            if((playerDirectory[index].getUsername()).equals(player1.getUsername()) == true) { // modify first player stats
                setData(dataDirectory[index], data1);
            }
            else if((playerDirectory[index].getUsername()).equals(player2.getUsername()) == true) { // modify second player stats
                setData(dataDirectory[index], data2);
            }
        }
         
        /* rewrite the statistics file with updated user stats */
        FileWriter writer = new FileWriter("statistics.txt");
        BufferedWriter statsWriter = new BufferedWriter(writer);
        Integer numPlayers = numberOfPlayers;
        statsWriter.write(numPlayers.toString()); // write the number of players
        statsWriter.write("\n");
         
        for(int count = 0; count < numberOfPlayers; count++) {
            statsWriter.write(scoreboardDataToString(dataDirectory[count], playerDirectory[count])); // write player to statistics.txt
        }
         
        statsWriter.close();
    }
     
    /**
     * returns PlayerStats object with data corresponding to user of given username
     * @param username username corresponding to data to be read into PlayerStats object
     * @return PlayerStats object with data of user given by username
     * @throws IOException
     */
    public Data readDataFromData(String username) throws IOException{
        int numberOfPlayers;
        boolean found = false;
        int iteration = 0;
        Scanner dataReader = new Scanner(new BufferedReader(new FileReader("statistics.txt")));
        Data data = new Data();
        Player player = new Player();
        numberOfPlayers = dataReader.nextInt(); // read in the number of players
         
        while((iteration < numberOfPlayers) && (!found)) {
            readData(data, player, dataReader);
            if((player.getUsername()).equals(username) == true){
                found = true;
                dataReader.close();
                return data;
            }
            else {
                iteration++;
                // maybe make this below into its own function to save space
                player.setUsername(username);
                resetData(data);
            }
        }
         
        dataReader.close();
        return data;
    }
    
    public float winPercentage(int numberOfWins, int numberOfGames) {
    	return (numberOfWins / numberOfGames);
    }
}