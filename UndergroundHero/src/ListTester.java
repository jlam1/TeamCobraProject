import java.util.ArrayList;
import java.util.List;

public class ListTester {

	public static void main(String[] args) {
		ResourceManager test1 = new ResourceManager();
		test1.loadAssetToList();
		List<Puzzle> puzzleList = new ArrayList<Puzzle>();
		puzzleList = test1.getPuzzleList();
		
		String puzzleName1 = puzzleList.get(0).getName();
		String puzzleDescription1 = puzzleList.get(0).getDescription();
		
		System.out.println(puzzleName1);
		System.out.println(puzzleDescription1);


	}

}
