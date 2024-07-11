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
import java.util.stream.Collectors;

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

    //Map acc with transaction
    public static Map<Double,List<Transaction>> mapAccTransaction(LocalDateTime starDate, LocalDateTime endDate){
        return transactionList.stream()
                .filter(t -> t.getDateTime().isAfter(starDate) && t.getDateTime().isBefore(endDate))
                .collect(Collectors.groupingBy(
                        t -> t.getAccount().getId(),
                        Collectors.toList()
                ));
    }

    //GET TRANSACTION IN SPECIFIC TIME
    public static List<Transaction> getTransactionByDate() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter account you want to check transaction: ");
            double accID = Double.parseDouble(bufferedReader.readLine());

            Optional<Account> getAccID = accountService.findById(accID);
            if (!getAccID.isPresent()) {
                System.out.println("No account found.");
                return null;
            }
            Account account = getAccID.get();

            System.out.print("Enter start date (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime startDate = FILeService.convertStringToLocalDateTime(bufferedReader.readLine());

            System.out.print("Enter end date (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime endDate = FILeService.convertStringToLocalDateTime(bufferedReader.readLine());

            Optional<Map.Entry<Double,List<Transaction>>> listTransactionByDate = mapAccTransaction(startDate,endDate).entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().equals(account.getId()))
                    .findFirst();

            if (listTransactionByDate.isPresent()) {
                List<Transaction> transactions = listTransactionByDate.get().getValue();
                transactions.forEach(System.out::println);
                return transactions;
            } else {
                System.out.println("No transactions found in given date.");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
