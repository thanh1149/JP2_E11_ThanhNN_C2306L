package Service;

import Entity.Account;

public class DepositThread extends AccountRepo implements Runnable {
    public DepositThread(Account account, double amount){
        super(account,amount);
    }

    @Override
    public Account transactionRequest() {
        super.getAccount().setBalance(super.getAccount().getBalance()+super.getAmount());
        return super.getAccount();
    }

    @Override
    public void run() {
        transactionRequest();
    }
}
