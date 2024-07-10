package Service;

import Entity.Account;
import Entity.Customer;
import Entity.Transaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.List;

public class BankService {
    public static List<Account> accountList;
    public static List<Customer> customerList;
    public static List<Transaction> transactionList;
    public static AccountService accountService;
    public static CustomerService customerService;
    public static TransactionService transactionService;

    public BankService(){}

    //TRANSACTION WITHDRAW
    public static boolean withdraw(int accId, double amount){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            System.out.print();
        }

    }



}
