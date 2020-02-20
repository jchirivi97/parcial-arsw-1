package edu.eci.arsw.exams.moneylaunderingapi.model;

public class SuspectAccount {
    public String accountId;
    public int amountOfSmallTransactions;
    
    
    public String getId () {
    	return  accountId;
    }
    
    public int gettrans () {
    	return   amountOfSmallTransactions;
    }
    
    
    public void setId (String accountId) {
    	this.accountId = accountId;
    }
    
    public void settrans (int amountOfSmallTransactions) {
    	this.amountOfSmallTransactions = amountOfSmallTransactions;
    }
}
