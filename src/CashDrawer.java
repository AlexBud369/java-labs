import java.util.concurrent.locks.ReentrantLock;

public class CashDrawer {
    private double cashLevel;
    private final double minThreshold = 30_000;
    private final double maxThreshold = 250_000;
    private final ReentrantLock lock = new ReentrantLock();

    public CashDrawer(double initialCash) {
        this.cashLevel = initialCash;
    }

    public boolean dispenseCash(double amount) {
        if (amount <= 0) {
            return false;
        }

        lock.lock();
        try {
            if (cashLevel >= amount) {
                cashLevel -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void acceptCash(double amount) {
        if (amount <= 0) {
            return;
        }

        lock.lock();
        try {
            cashLevel += amount;
        } finally {
            lock.unlock();
        }
    }

    public double getCashLevel() {
        lock.lock();
        try {
            return cashLevel;
        } finally {
            lock.unlock();
        }
    }

    public double getMinThreshold() { return minThreshold; }
    public double getMaxThreshold() { return maxThreshold; }
}