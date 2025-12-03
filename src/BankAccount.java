import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String number;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(String number, double initialBalance) {
        this.number = number;
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }

        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        lock.lock();
        try {
            balance += amount;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public static boolean transfer(BankAccount from, BankAccount to, double amount) {
        if (from == to || amount <= 0) {
            return false;
        }

        // Упорядочиваем замки по ID, чтобы избежать deadlock
        ReentrantLock firstLock = from.number.compareTo(to.number) < 0 ? from.lock : to.lock;
        ReentrantLock secondLock = from.number.compareTo(to.number) < 0 ? to.lock : from.lock;

        firstLock.lock();
        try {
            secondLock.lock();
            try {
                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;
                    return true;
                }
                return false;
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public String getNumber() {
        return number;
    }
}