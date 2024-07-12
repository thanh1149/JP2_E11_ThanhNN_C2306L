package Service;

import Entity.Account;
import Entity.Currency;

public class AddInterestThread extends AccountRepo implements Runnable{
    public AddInterestThread(Account account, double amount){
        super(account,amount);
    }

    @Override
    public Account transactionRequest() {
        return null;
    }

    @Override
    public Account addInterest() {
        double interest = 0;
        if(super.getAccount().getCurrency().equals(Currency.VND)){
            interest = 0.002;
        }
        super.getAccount().setBalance(super.getAccount().getBalance()+(super.getAccount().getBalance()*interest));
        return super.getAccount();
    }

    @Override
    public void run() {
        addInterest();
    }
}
