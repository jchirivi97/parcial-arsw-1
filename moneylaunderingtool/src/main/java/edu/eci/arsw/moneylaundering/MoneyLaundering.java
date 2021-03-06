package edu.eci.arsw.moneylaundering;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyLaundering
{
    private TransactionAnalyzer transactionAnalyzer;
    private static TransactionReader transactionReader;
    private static int amountOfFilesTotal;
    private static AtomicInteger amountOfFilesProcessed;
    public static MoneyLaundringThread ml1; 

    public MoneyLaundering()
    {
        transactionAnalyzer = new TransactionAnalyzer();
        transactionReader = new TransactionReader();
        amountOfFilesProcessed = new AtomicInteger();
    }

    public static void processTransactionData()
    {
        amountOfFilesProcessed.set(0);
        List<File> transactionFiles = getTransactionFileList();
        amountOfFilesTotal = transactionFiles.size();
        
        int cantFile = amountOfFilesTotal / 5;
        int cont=0;
        
        for(File transactionFile : transactionFiles)
        {  
        	while (cont <= cantFile) {
        		
        		List<Transaction> transactions = transactionReader.readTransactionsFromFile(transactionFile);
        		if (cont == 5) {
        			MoneyLaundringThread ml1 = new MoneyLaundringThread(transactions);
            		ml1.run();
        		}
        		
        		cont ++;
        		
        	}
        	cont = 0;
        	
            amountOfFilesProcessed.incrementAndGet();
        }
    }

    public List<String> getOffendingAccounts()
    {
        return transactionAnalyzer.listOffendingAccounts();
    }

    private static List<File> getTransactionFileList()
    {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }

    public static void main(String[] args)
    {
        System.out.println(getBanner());
        System.out.println(getHelp());
        MoneyLaundering moneyLaundering = new MoneyLaundering();
        
        
        Thread processingThread = new Thread(() -> MoneyLaundering.processTransactionData());
        //processingThread.start();
        
        
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
           
            if (line.isEmpty()) {
            	processingThread.start();
            }
            
            
            
            String message = "Processed %d out of %d files.\nFound %d suspect accounts:\n%s";
            List<String> offendingAccounts = moneyLaundering.getOffendingAccounts();
            String suspectAccounts = offendingAccounts.stream().reduce("", (s1, s2)-> s1 + "\n"+s2);
            message = String.format(message, MoneyLaundering.amountOfFilesProcessed.get(), MoneyLaundering.amountOfFilesTotal, offendingAccounts.size(), suspectAccounts);
            System.out.println(message);
        }
    }

    private static String getBanner()
    {
        String banner = "\n";
        try {
            banner = String.join("\n", Files.readAllLines(Paths.get("src/main/resources/banner.ascii")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return banner;
    }

    private static String getHelp()
    {
        String help = "Type 'exit' to exit the program. Press 'Enter' to get a status update\n";
        return help;
    }
}