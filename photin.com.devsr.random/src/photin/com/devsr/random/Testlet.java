package photin.com.devsr.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Swapna
 *
 */
public class Testlet {
	
	public String testletId;
	private List<Item> items;

	public Testlet(String testletId, List<Item> items) {
		this.testletId = testletId;
		this.items = items;
	}

	/**
	 * 
	 * generated randomizedItemsListAsPerInput
	 * 
	 * @return
	 */
	public List<Item> Randomize() {
		// Items private collection has 6 Operational and 4 Pretest Items.
		// Randomize the order of these items as per

		// the requirement (with TDD)
		// The assignment will be reviewed on the basis of – Tests written first,
		// Correct
		// logic, Well structured & clean readable code.

		List<Item> randomizedFullList = new ArrayList<>();

		List<Item> preTestList = new ArrayList<>(items.subList(0, 4));
		List<Item> operationalList = new ArrayList<>(items.subList(4, items.size()));

		randomizedFullList = randomizedItemsListAsPerInput(randomizedFullList, preTestList, 2);
		List<Item> preAndOperationalList = Stream.of(preTestList, operationalList).flatMap(Collection::stream)
				.collect(Collectors.toList());

		randomizedFullList = randomizedItemsListAsPerInput(randomizedFullList, preAndOperationalList, 8);

		return randomizedFullList;
	}

	/**
	 * 
	 * generated randomizedItemsListAsPerInput
	 * 
	 * @param rand
	 * @param randomizedFullList
	 * @param preTestList
	 * @param numberOfItems
	 */
	private List<Item> randomizedItemsListAsPerInput(List<Item> randomizedFullList, List<Item> preTestList,
			int numberOfItems) {
		Random rand = new Random();
		for (int i = 0; i < numberOfItems; i++) {

			// take a random index between 0 to size
			// of given List
			int randomIndex = rand.nextInt(preTestList.size());

			// add element in temporary list
			randomizedFullList.add(preTestList.get(randomIndex));

			// Remove selected element from original list
			preTestList.remove(randomIndex);
		}
		return randomizedFullList;
	}

	/**
	 * 
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<Item> testLetItems = new ArrayList<>();
		testLetItems.add(new Item("Pretest-1", ItemTypeEnums.PRETEST));
		testLetItems.add(new Item("Pretest-2", ItemTypeEnums.PRETEST));
		testLetItems.add(new Item("Pretest-3", ItemTypeEnums.PRETEST));
		testLetItems.add(new Item("Pretest-4", ItemTypeEnums.PRETEST));
		testLetItems.add(new Item("Operational-1", ItemTypeEnums.OPERATIONAL));
		testLetItems.add(new Item("Operational-2", ItemTypeEnums.OPERATIONAL));
		testLetItems.add(new Item("Operational-3", ItemTypeEnums.OPERATIONAL));
		testLetItems.add(new Item("Operational-4", ItemTypeEnums.OPERATIONAL));
		testLetItems.add(new Item("Operational-5", ItemTypeEnums.OPERATIONAL));
		testLetItems.add(new Item("Operational-6", ItemTypeEnums.OPERATIONAL));

		Testlet testlet = new Testlet("TestletId_1", testLetItems);
		List<Item> randomizedList = testlet.Randomize();
		
		//printing randomized list as per requirement
		randomizedList.parallelStream().forEachOrdered(item -> {
			System.out.println(item);
		});
		;
	}

}

/**
 * 
 * @author Swapna
 *
 */
class Item {
	public String ItemId;
	public ItemTypeEnums ItemType;

	public Item(String itemId, ItemTypeEnums itemType) {
		super();
		ItemId = itemId;
		ItemType = itemType;
	}

	@Override
	public String toString() {
		return "Item [ItemId=" + ItemId + ", ItemType=" + ItemType + "]";
	}

}

/**
 * 
 * @author Swapna
 *
 */
enum ItemTypeEnums {
	PRETEST(0), OPERATIONAL(1);

	private int itemType;

	private ItemTypeEnums(int itemType) {
		this.itemType = itemType;
	}
}

