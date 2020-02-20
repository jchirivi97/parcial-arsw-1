package edu.eci.arsw.moneylaundering;

import java.util.List;

public class MoneyLaundringThread extends Thread {
	
	
	//Transaction transaction;
	
	List<Transaction> transactions;
	
	TransactionAnalyzer transactionAnalyzer;

	public MoneyLaundringThread(List<Transaction> transactions) {
		
		this.transactions = transactions;
	}
	
	
	public void run () {
		
		while (true) {
			for(Transaction transaction : transactions)
	        {
	           transactionAnalyzer.addTransaction(transaction);
	        }
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
