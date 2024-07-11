package Service;

import Entity.*;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BankService {
    public static List<Account> accountList;
    public static List<Customer> customerList;
    public static List<Transaction> transactionList;
    public static AccountService accountService;
    public static CustomerService customerService;
    public static TransactionService transactionService;

    public BankService(){}

    //TRANSACTION WITHDRAW
    public static boolean withdraw(double accId, double amount){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            while (true){
                try{
                    System.out.print("Enter your account id: ");
                    accId = Double.parseDouble(bufferedReader.readLine());
                    Optional<Account> getAccID = accountService.findById(accId);
                    if(!getAccID.isPresent()){
                        System.out.println("No account found.");
                        continue;
                    }
                    Account account = getAccID.get();

                    System.out.print("Enter withdraw amount: ");
                    amount = Double.parseDouble(bufferedReader.readLine());
                    if(amount % 10 != 0){
                        System.out.println("Withdraw must be in multiples of 10.");
                        continue;
                    }
                    if(accountService.checkBalance(account,amount)){
                        WithdrawThread withdrawThread = new WithdrawThread(account,amount);
                        Thread wdThread = new Thread(withdrawThread);
                        try {
                            wdThread.start();
                            wdThread.join();
                        }catch (IOError | InterruptedException e){
                            System.out.println(e.getMessage());
                        }

                        Transaction transaction = new Transaction(
                                transactionList.size()+1,
                                account,
                                amount,
                                Type.WITHDRAWAL,
                                LocalDateTime.now(),
                                Status.C
                        );
                        transactionList.add(transaction);

                        System.out.println("Withdraw succesfull, current balance: " + account.getBalance() + account.getCurrency());
                        return true;
                    }else{
                        System.out.println("Not enough balance.");
                        return false;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Please enter numeric values.");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //TRANSACTION DEPOSIT
    public static boolean deposit(double accId, double amount){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            while (true){
                try{
                    System.out.print("Enter your account id: ");
                    accId = Double.parseDouble(bufferedReader.readLine());
                    Optional<Account> getAccID = accountService.findById(accId);
                    if(!getAccID.isPresent()){
                        System.out.println("No account found.");
                        continue;
                    }
                    Account account = getAccID.get();

                    System.out.print("Enter deposit amount: ");
                    amount = Double.parseDouble(bufferedReader.readLine());
                    if(amount % 10 != 0){
                        System.out.println("Deposit must be in multiples of 10.");
                        continue;
                    }
                    DepositThread depositThread = new DepositThread(account,amount);
                    Thread dpThread = new Thread(depositThread);
                    try {
                        dpThread.start();
                        dpThread.join();
                    }catch (IOError | InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                    Transaction transaction = new Transaction(
                            transactionList.size()+1,
                            account,
                            amount,
                            Type.DEPOSIT,
                            LocalDateTime.now(),
                            Status.C
                    );
                    transactionList.add(transaction);

                    System.out.println("Deposit succesfull, current balance: " + account.getBalance() + account.getCurrency());
                    return true;
                }catch (NumberFormatException e){
                    System.out.println("Please enter numeric values.");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //DISPLAY BALANCE VIA ACC ID
    public static Optional<Account> getAccBalance(double accID){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Enter account id to check balance: ");
            accID = Double.parseDouble(bufferedReader.readLine());
            Optional<Account> getAccID = accountService.findById(accID);

            if(getAccID.isPresent()){
                Account account = getAccID.get();
                System.out.println("Account balance: " + account.getBalance());
                return getAccID;
            }else {
                System.out.println("No account found.");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    


}
