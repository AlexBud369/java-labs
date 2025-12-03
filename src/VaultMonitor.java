import java.util.List;

public class VaultMonitor implements Runnable {
    private final Bank bank;

    public VaultMonitor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            Vault vault = bank.getVault();
            List<Teller> allTellers = bank.getAllTellers();

            System.out.println("\n----");
            System.out.println("НАБЛЮДАТЕЛЬ: Проверка кассовых аппаратов");

            boolean adjustmentsMade = false;

            for (Teller t : allTellers) {
                CashDrawer d = t.getDrawer();
                double cash = d.getCashLevel();

                System.out.printf("  %s: %.2f₽ ", t.getName(), cash);

                if (cash < d.getMinThreshold()) {
                    double replenish = d.getMaxThreshold() - cash;

                    if (vault.transferToDrawer(replenish)) {
                        d.acceptCash(replenish);
                        System.out.printf("значит пополнение +%.2f₽ → %.2f₽\n", replenish, d.getCashLevel());
                        adjustmentsMade = true;
                    } else {
                        System.out.printf("значит НЕДОСТАТОЧНО СРЕДСТВ В ХРАНИЛИЩЕ (нужно %.2f₽)\n", replenish);
                    }

                } else if (cash > d.getMaxThreshold()) {
                    double excess = cash - (d.getMaxThreshold() * 0.8); // Берем 20% от максимума

                    if (d.dispenseCash(excess)) {
                        vault.receiveFromDrawer(excess);
                        System.out.printf("значит ИЗЪЯТИЕ -%.2f₽ → %.2f₽\n", excess, d.getCashLevel());
                        adjustmentsMade = true;
                    }
                } else {
                    System.out.println("значит НОРМА");
                }
            }

            System.out.printf("Хранилище: %.2f₽\n", vault.getReserve());

            if (!adjustmentsMade) {
                System.out.println("Корректировка не требуется");
            }

            System.out.println("----\n");

        } catch (Exception e) {
            System.err.println("ОШИБКА в мониторе: " + e.getMessage());
        }
    }
}