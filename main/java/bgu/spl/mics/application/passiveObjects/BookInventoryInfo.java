package bgu.spl.mics.application.passiveObjects;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Passive data-object representing a information about a certain book in the inventory.
 * You must not alter any of the given public methods of this class. 
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class BookInventoryInfo implements Serializable {

	private int price,amount;
	private String bookTitle;
	//private ReentrantReadWriteLock locker;

	public BookInventoryInfo(String bookTitle, int amount, int price){
		this.price = price;
		this.amount = amount;
		this.bookTitle = bookTitle;
		//locker = new ReentrantReadWriteLock();
	}
	/**
	 * Retrieves the title of this book.
	 * <p>
	 * @return The title of this book.
	 */
	public String getBookTitle()
	{
		return bookTitle;
	}

	/**
	 * Retrieves the amount of books of this type in the inventory.
	 * <p>
	 * @return amount of available books.
	 */
	public int getAmountInInventory()
	{
		return amount;
	}
	/**
	 * Retrieves the price for  book.
	 * <p>
	 * @return the price of the book.
	 */
	public int getPrice()
	{
		return price;
	}

	//public ReentrantReadWriteLock getLocker(){return locker;}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
