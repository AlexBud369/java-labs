import java.util.*;
import java.util.concurrent.*;

public class Bank {
    private final List<Teller> tellers = new ArrayList<>();
    private final List<BankAccount> accounts = new ArrayList<>();
    private final Vault vault = new Vault();
    private final ScheduledExecutorService monitor = Executors.newScheduledThreadPool(1);
    private final ExecutorService customerPool = Executors.newFixedThreadPool(50);
    private final Random rnd = new Random();
    private volatile boolean isOpen = false;

    public Bank() {
        createAccounts();
        createTellers();
        startMonitor();
    }

    private void createAccounts() {
        for (int i = 1; i <= 15; i++) {
            accounts.add(new BankAccount("40817810" + String.format("%08d", i),
                    10_000 + rnd.nextDouble() * 90_000));
        }
    }

    private void createTellers() {
        for (int i = 1; i <= 4; i++) {
            tellers.add(new Teller("Окно №" + i, this,
                    120_000 + rnd.nextDouble() * 50_000));
        }
    }

    private void startMonitor() {
        monitor.scheduleAtFixedRate(new VaultMonitor(this), 5, 4, TimeUnit.SECONDS);
    }

    public void openForBusiness() {
        System.out.println("БАНК ОТКРЫЛСЯ ДЛЯ КЛИЕНТОВ");
        isOpen = true;

        // Запускаем кассиров
        for (Teller t : tellers) {
            new Thread(t, t.getName()).start();
        }

        // Запускаем клиентов
        for (int i = 1; i <= 25; i++) {
            if (!isOpen) break;

            BankAccount acc = accounts.get(rnd.nextInt(accounts.size()));
            Customer c = new Customer("Посетитель-" + i, this, acc);
            customerPool.execute(c);
            try {
                Thread.sleep(400 + rnd.nextInt(800));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void closeForTheDay() {
        System.out.println("\nВНИМАНИЕ! БАНК ЗАКРЫВАЕТСЯ!");
        isOpen = false;

        // 1. Останавливаем прием новых клиентов
        customerPool.shutdown();

        try {
            // 2. Даем время на завершение текущих клиентов
            if (!customerPool.awaitTermination(8, TimeUnit.SECONDS)) {
                customerPool.shutdownNow();
            }

            // 3. Останавливаем кассиров
            tellers.forEach(Teller::stopServing);

            // 4. Ждем завершения кассиров
            Thread.sleep(2000);

            // 5. Останавливаем монитор
            monitor.shutdownNow();
            if (!monitor.awaitTermination(3, TimeUnit.SECONDS)) {
                System.err.println("Монитор не завершился корректно");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            customerPool.shutdownNow();
            monitor.shutdownNow();
        }

        System.out.println("Все клиенты обслужены. Банк закрыт.");
        printFinalReport();
    }

    private void printFinalReport() {
        System.out.println("\n======= ФИНАЛЬНЫЙ ОТЧЕТ =======");
        System.out.printf("Остаток в хранилище: %.2f₽\n", vault.getReserve());

        double totalCash = 0;
        for (Teller t : tellers) {
            double cash = t.getDrawer().getCashLevel();
            totalCash += cash;
            System.out.printf("%s: %.2f₽\n", t.getName(), cash);
        }
        System.out.printf("Всего в кассах: %.2f₽\n", totalCash);

        double totalBalance = 0;
        for (BankAccount acc : accounts) {
            totalBalance += acc.getBalance();
        }
        System.out.printf("Всего на счетах: %.2f₽\n", totalBalance);
    }

    public Teller getRandomTeller() {
        return tellers.get(rnd.nextInt(tellers.size()));
    }

    public BankAccount getRandomAccount() {
        return accounts.get(rnd.nextInt(accounts.size()));
    }

    public List<Teller> getAllTellers() {
        return new ArrayList<>(tellers);
    }

    public Vault getVault() {
        return vault;
    }

    public boolean isOpen() {
        return isOpen;
    }
}