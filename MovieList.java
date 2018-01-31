import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class MovieList{
  private ArrayList<String> movies;
  //Constructor
  public MovieList(String pathname) {
    movies = new ArrayList();
    File file = new File(pathname);
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        movies.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist!");
    }
  }
  
  public String getRandomMovie(){
    int movieIndex = (int)(Math.random()*movies.size());
    return movies.get(movieIndex);
  }
}
