import java.util.Scanner;

public class Game {
  private String movieToGuess;
  private int pointsLost;
  private String wrongLetters;
  private String rightLetters;
  private boolean gameWon;
  
  //constructor intializing MovieList
  public Game(String pathname) {
    MovieList movielist = new MovieList(pathname);
    movieToGuess = movielist.getRandomMovie().trim();
    pointsLost = 0;
    rightLetters = "";
    wrongLetters = "";
    gameWon = false;
  }
  
  //method return title of movie
  public String getMovieTitle(){
    return movieToGuess;
  }
  
  //method that replaces all the letters in the movie title with underscores, if no letters have been correctly guessed yet, and all the letters except the ones guessed, if any letter was already correctly guessed.
  public String getHiddenMovieTitle(){
    if (rightLetters.equals("")){
      return movieToGuess.replaceAll("[a-zA-Z]", "_");
    }
    else{
      return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters + "]]", "_");
    }
  }
  
  //Method that returns letters guessed that are not in the movie title.
  public String getWrongLetters(){
    return wrongLetters;
  }
  
  //Method that returns true if the game was won and false otherwise.
  public boolean WonGame(){
    return gameWon;
  }
  
  //Method that returns that the game has ended and the player did not win if number of points lost is at least 10, and returns that the game has ended and the player won if the previous is not true and there are no underscores left in the hidden version of the movie title.
  public boolean gameEnded(){
    if (pointsLost >= 10){
      return true;
    }
    if(!getHiddenMovieTitle().contains("_")){
      gameWon = true;
      return true;
    }
    return false;
  }
  
  /**
     * Method that (1) asks the player to input a letter; (2) converts it to lower case; (3) asks him to input another
     * letter (implemented recursively) if (a) the inputted is not a letter or (b) if the letter was
     * already guessed and so is included in the {@link String} objects containing the letters guessed wrongly and
     * correctly, respectively; (4) if the {@link String} inputted is a letter not guessed yet, the method returns the
     * letter.
     *
     * @return Letter not guessed yet.
     */
  
  private String inputLetter(){
    System.out.println("Guess the letter: ");
    Scanner scanner = new Scanner(System.in);
    String letter = scanner.nextLine().toLowerCase();
    
    if (!letter.matches("[a-z]")){
      System.out.println("This is not a letter.");
      return inputLetter();
    }
    else if (wrongLetters.contains(letter) || rightLetters.contains(letter)){
      System.out.println("You already guessed that letter.");
      return inputLetter();
    }
    else return letter;
  }
  
  public void guessLetter(){
    String guessedLetter = inputLetter();
    
    if (movieToGuess.toLowerCase().contains(guessedLetter)){
      rightLetters += guessedLetter + guessedLetter.toUpperCase();
    }
    else {
      pointsLost++;
      wrongLetters += " "+guessedLetter;
    }
  }
}
