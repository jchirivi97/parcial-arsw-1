package edu.eci.arsw.exams.moneylaunderingapi;


import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import edu.eci.arsw.exams.moneylaunderingapi.service.MoneyLaunderingService;
import edu.eci.arsw.exams.moneylaunderingapi.SuspectAccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.exams.moneylaunderingapi.*;

@RestController
public class MoneyLaunderingController
{
	
	
	@Autowired
    MoneyLaunderingService moneyLaunderingService;

    @RequestMapping( value = "/fraud-bank-accounts")
    public List<SuspectAccount> offendingAccounts() {
        return moneyLaunderingService.getSuspectAccounts();
    }
    
    @RequestMapping(method = RequestMethod.GET, path = {"accountId"})
    public ResponseEntity<?> mostrarporID (@PathVariable("accountId") String accountId ) throws SuspectAccountException   {
    	ResponseEntity ans;
    	
    	List<SuspectAccount> suspectaccount = moneyLaunderingService.getSuspectAccounts();
    	List<SuspectAccount> suspectaccount2 = null;
    	for(SuspectAccount x :suspectaccount) {
			if(x.getId().equals(accountId))
			suspectaccount2.add(x);
		}
		if(suspectaccount2.size()==0){
			ans = new ResponseEntity<>("Error 404",HttpStatus.NOT_FOUND);
		    }
		else{
			ans = new ResponseEntity<>(suspectaccount2,HttpStatus.ACCEPTED);
		    }
		return ans;
    	
    }
    
    
    
    
    
    
    

    //TODO
}
