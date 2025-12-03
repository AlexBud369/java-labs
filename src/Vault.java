import java.util.concurrent.locks.ReentrantLock;

public class Vault {
    private double reserve = 5_000_000;
    private final ReentrantLock lock = new ReentrantLock();

    public boolean transferToDrawer(double amount) {
        if (amount <= 0) {
            return false;
        }

        lock.lock();
        try {
            if (reserve >= amount) {
                reserve -= amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void receiveFromDrawer(double amount) {
        if (amount <= 0) {
            return;
        }

        lock.lock();
        try {
            reserve += amount;
        } finally {
            lock.unlock();
        }
    }

    public double getReserve() {
        lock.lock();
        try {
            return reserve;
        } finally {
            lock.unlock();
        }
    }
}