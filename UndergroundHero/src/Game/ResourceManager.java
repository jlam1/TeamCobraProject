package Game;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * This class will read text files and load them into a list. Call list by using getter method.
 * @author King
 *
 */
public class ResourceManager {
	
	/**
	 * Method: saveGame()
	 * 
	 * This method will save the game. It will throw an exception when overwriting a existing save 
	 * file and create a new file if the file does not exist.
	 * 
	 * @throws Exception
	 */
	public static void saveGame(Serializable data, String fileName) throws Exception
	{
		try(ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName))))
		{
			output.writeObject(data);
		}
		
	}
	
	/**
	 * Method: loadGame()
	 * 
	 * This method will load the file. It will throw an exception when the file does 
	 * not exist and creates a new game. it will throw an exception when the file cannot be loaded.
	 * 
	 * @return game
	 * @throws Exception
	 */
	public static Object loadGame(String fileName) throws Exception
	{
		try(ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName))))
		{
			return input.readObject();
		}
	}
}
