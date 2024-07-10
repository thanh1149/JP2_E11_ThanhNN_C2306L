import Entity.Account;
import Entity.Customer;
import Entity.Transaction;
import Service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int choice = 0;

        List<Customer> customerList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();

        AccountService accountService = new AccountService();
        accountService.accounts = accountList;
        CustomerService customerService = new CustomerService();
        customerService.customers = customerList;
        TransactionService transactionService = new TransactionService();
        transactionService.transactions = transactionList;

        FILeService.accountService = accountService;
        FILeService.customerService = customerService;
        FILeService.transactionService = transactionService;
        FILeService.customerList = customerList;
        FILeService.accountList = accountList;
        FILeService.transactionList = transactionList;

        BankService.accountService = accountService;
        BankService.customerService = customerService;
        BankService.transactionService = transactionService;
        BankService.customerList = customerList;
        BankService.accountList = accountList;
        BankService.transactionList = transactionList;

        String rootPath = System.getProperty("user.dir");
        String customerPath = rootPath + "/JP2_E11_ThanhNN/data/Customer.txt";
        String accountPath = rootPath + "\\JP2_E11_ThanhNN\\data\\Account.txt";
        String transactionPath = rootPath + "\\JP2_E11_ThanhNN\\data\\Transaction.txt";

        FILeService.getCustomerFromFile(customerPath);
        FILeService.getTransactionFromFIle(accountPath);
        FILeService.getTransactionFromFIle(transactionPath);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("1.Withdraw money.");
            System.out.println("2.Deposit money.");
            System.out.println("3.Display transaction in specific time.");
            System.out.println("4.Caculate interest.");
            System.out.println("5.Exit");
            try{
                String choiceStr = bufferedReader.readLine();
                choice = Integer.parseInt(choiceStr);
                switch (choice){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (choice!=6);

    }
}