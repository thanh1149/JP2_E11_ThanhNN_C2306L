package Service;

import Entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FILeService{
    public static CustomerService customerService;
    public static TransactionService transactionService;
    public static AccountService accountService;
    public static List<Account> accountList;
    public static List<Customer> customerList;
    public static List<Transaction> transactionList;

    //FORMAT TIME
    public static LocalDateTime convertStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    //GET DATA FROM FILE

    public static void getCustomerFromFile(String customerPath){
        try {
            BufferedReader customerReader = new BufferedReader(new FileReader(customerPath));
            String line;
            while ((line = customerReader.readLine()) != null) {
                String[] data = line.split(";");
                if (!line.isEmpty()) {
                    customerList.add(new Customer(Integer.parseInt(data[0]), data[1], data[2]));
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getTransactionFromFIle(String transactionPath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(transactionPath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (!line.isEmpty()) {
                    Account accountInsert = accountService.findById(Double.parseDouble(data[1])).get();
                    Type type = Type.valueOf(data[3]);
                    Status status = Status.valueOf(data[5]);
                    transactionList.add(new Transaction(
                            Integer.parseInt(data[0])
                            ,accountInsert
                            ,Float.parseFloat(data[2])
                            ,type
                            ,convertStringToLocalDateTime(data[4])
                            ,status));
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getAccountFromFile(String accountPath){
        try{
            BufferedReader accountReader = new BufferedReader(new FileReader(accountPath));
            String line;
            while ((line = accountReader.readLine()) != null) {
                String[] data = line.split(";");
                if (!line.isEmpty()) {
                    Customer customerInsert = customerService.findById(Integer.parseInt(data[1])).get();
                    Currency status = Currency.valueOf(data[3]);
                    accountList.add(new Account(
                            Double.parseDouble(data[0])
                            ,customerInsert
                            ,Float.parseFloat(data[2])
                            ,status));
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
