package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("Quality = 11.", 11, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_SellInNegative() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals("SellIn = -1.", -1, itemBrie.getSellIn());
	}
    
	@Test
	public void testUpdateEndOfDay_DexterityVest_QualityDecrease() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20));
		//Act
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals("Quality = 19", 19, itemVest.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_DexterityVest_SellInDecreases() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("+5 Dexterity Vest", 10, 20));
		//Act
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemVest = items.get(0);
		assertEquals("SellIn = 9", 9, itemVest.getSellIn());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_QualityIncrease() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 0));
		//Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(" Quality = 2", 2, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_AgedBrie_QualityIncreaseTriple() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 0));
		//Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(" Quality = 6", 6, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_MongooseElixir_IsQualityNegative() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Elixir of the Mongoose", 5, 7));
		//Act
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
	
		List<Item> items = store.getItems();
		Item itemElixir = items.get(0);
		assertEquals("Quality = 0", 0, itemElixir.getQuality());
	}
	

	@Test
	public void testUpdateEndOfDay_Sulfuras_QualitySame() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		//Act
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Quality should be same", 80, itemSulfuras.getQuality());
	
	}
	
	@Test
	public void testUpdateEndOfDay_Sulfuras_SellInSame() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		//Act
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Quality should be same", 0, itemSulfuras.getSellIn());
	
	}
	@Test
	public void testUpdateEndOfDay_BackStagePass_QualityIncreases1() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		//Act
		//should cause error
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBackStagePass = items.get(0);
		assertEquals("Quality = 21", 21, itemBackStagePass.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_BackStagePass_QualityIncreases2() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		//Act
		//should cause error
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBackStagePass = items.get(0);
		assertEquals("Quality = 22", 22, itemBackStagePass.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_BackStagePass_QualityIncreases3() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		//Act
		//should cause error
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBackStagePass = items.get(0);
		assertEquals("Quality = 22", 23, itemBackStagePass.getQuality());
	}
	@Test
	public void testUpdateEndOfDay_BackStagePass_Quality0() {
		//Arrange 
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		//Act
		//should cause error
		store.updateEndOfDay();
		
		List<Item> items = store.getItems();
		Item itemBackStagePass = items.get(0);
		assertEquals("Quality = 0", 0, itemBackStagePass.getQuality());
	}
}
