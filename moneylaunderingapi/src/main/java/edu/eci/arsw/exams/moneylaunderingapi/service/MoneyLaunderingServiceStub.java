package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;


import java.util.List;




public class MoneyLaunderingServiceStub implements MoneyLaunderingService {
	
	SuspectAccount sup ;
	List<SuspectAccount> susp;
	
	public void addsup(){
		sup.setId("data8");
		sup.settrans(8);
		susp.add(sup);
	} 
    @Override
    public void updateAccountStatus(SuspectAccount suspectAccount) {
        sup.setId(suspectAccount.getId());
        sup.settrans(suspectAccount.gettrans());
    }
    

    @Override
    public SuspectAccount getAccountStatus(String accountId) {
        for (int i =0; i < susp.size(); i++) {
        	if(susp.get(i).getId().equals(accountId)) {
        		return susp.get(i);
        	}
        }
		return sup;
        
    }

    @Override
    public List<SuspectAccount> getSuspectAccounts() {
        return null;
    }
}
