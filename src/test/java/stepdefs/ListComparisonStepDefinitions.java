package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Item;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ListComparisonStepDefinitions {
    private List<Item> firstList;
    private List<Item> secondList;
    private final List<String> mismatches = new ArrayList<>();


    @Given("I have the following items in the first list:")
    public void haveTheFollowingItemsInTheFirstList(List<Map<String, String>> items) {
        firstList = createItemList(items);
    }

    @And("I have the following items in the second list:")
    public void haveTheFollowingItemsInTheSecondList(List<Map<String, String>> items) {
        secondList = createItemList(items);
    }

    @When("I compare both lists")
    public void compareBothLists() {
        Map<String, Item> firstListMap = itemListToMap(firstList);
        Map<String, Item> secondListMap = itemListToMap(secondList);

        for (String itemName : firstListMap.keySet()) {
            Item firstListItem = firstListMap.get(itemName);
            Item secondListItem = secondListMap.get(itemName);

            if (secondListItem == null) {
                mismatches.add("Item missing in second list: " + firstListItem);
            } else {
                if (firstListItem.price() != secondListItem.price()) {
                    mismatches.add("Mismatch in '" + itemName + "': Price differs (first list: " + firstListItem.price() + ", second list: " + secondListItem.price() + ")");
                }
                if (!firstListItem.category().equals(secondListItem.category())) {
                    mismatches.add("Mismatch in '" + itemName + "': Category differs (first list: '" + firstListItem.category() + "', second list: '" + secondListItem.category() + "')");
                }
            }
        }

        for (String itemName : secondListMap.keySet()) {
            if (!firstListMap.containsKey(itemName)) {
                mismatches.add("Item missing in first list: " + secondListMap.get(itemName));
            }
        }
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void theListsShouldContainTheSameItems() {
        if (!mismatches.isEmpty()) {
            Assert.fail("List are not the same:\n" + String.join("\n", mismatches));
        }
    }

    private List<Item> createItemList(List<Map<String, String>> items) {
        return items.stream()
                .map(map -> new Item(map.get("name"),
                        Double.parseDouble(map.get("price")),
                        map.get("category")))
                .collect(Collectors.toList());
    }

    private static Map<String, Item> itemListToMap(List<Item> list) {
        return list.stream().collect(Collectors.toMap(
                Item::name,  // Key: Item name
                item -> item // Value: The Item object itself
        ));
    }
}
