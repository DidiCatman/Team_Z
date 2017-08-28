package entities.items;

import java.util.Random;

import main.Handler;

public class ItemManager {

	private Handler handler;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		System.out.println("ItemManager was initialized");
	}
	
	public void tick(){
		
	}
	
	public void render(){
		
	}
	
	public Item randomItem(){
		Random rand = new Random();
		int r = rand.nextInt(Item.getItems().length);
		return Item.getItems()[r];
	}
}
