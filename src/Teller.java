import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Teller implements Runnable {
    private final String name;
    private final Bank bank;
    private final CashDrawer drawer;
    private final BlockingQueue<Customer> queue = new LinkedBlockingQueue<>();
    private volatile boolean active = true;
    private static final Random r = new Random();
    private int customersServed = 0;

    public Teller(String name, Bank bank, double initialCash) {
        this.name = name;
        this.bank = bank;
        this.drawer = new CashDrawer(initialCash);
    }

    public void acceptCustomer(Customer c) {
        if (!active) {
            System.out.println(name + " закрыто, " + c.getName() + " не принят");
            return;
        }
        try {
            queue.put(c);
            System.out.println(name + " → " + c.getName() + " встал(а) в очередь");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        System.out.println(name + " готов(а) к работе. Начальная сумма: " +
                String.format("%.2f₽", drawer.getCashLevel()));

        while (active || !queue.isEmpty()) {
            try {
                Customer customer = queue.poll(500, TimeUnit.MILLISECONDS);
                if (customer == null) continue;

                System.out.println(name + " обслуживает " + customer.getName() +
                        " (" + customer.getOperation().getTitle() + ")");
                process(customer);
                customersServed++;
                Thread.sleep(800 + r.nextInt(1200));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println(name + " завершил(а) смену. Обслужено клиентов: " + customersServed);
    }

    private void process(Customer c) {
        TransactionType op = c.getOperation();
        double amount = c.getAmount();
        BankAccount acc = c.getAccount();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        switch (op) {
            case DEPOSIT -> {
                if (acc.deposit(amount)) {
                    drawer.acceptCash(amount);
                    System.out.printf("  %s: Пополнение +%.2f₽. Баланс: %.2f₽\n",
                            name, amount, acc.getBalance());
                }
            }
            case WITHDRAW -> {
                // Сначала проверяем наличные, потом списываем со счета
                if (drawer.dispenseCash(amount)) {
                    if (acc.withdraw(amount)) {
                        System.out.printf("  %s: Снятие -%.2f₽. Баланс: %.2f₽\n",
                                name, amount, acc.getBalance());
                    } else {
                        // Если счет не позволяет снять, возвращаем наличные
                        drawer.acceptCash(amount);
                        System.out.printf("  %s: Недостаточно средств на счете (%.2f₽)\n",
                                name, acc.getBalance());
                    }
                } else {
                    System.out.printf("  %s: Недостаточно наличных в кассе (%.2f₽)\n",
                            name, drawer.getCashLevel());
                }
            }
            case TRANSFER -> {
                BankAccount to = bank.getRandomAccount();
                while (to == acc) {
                    to = bank.getRandomAccount();
                }

                if (BankAccount.transfer(acc, to, amount)) {
                    System.out.printf("  %s: Перевод %.2f₽ значит %s\n",
                            name, amount, to.getNumber());
                } else {
                    System.out.printf("  %s: Перевод не удался (недостаточно средств)\n", name);
                }
            }
            case PAYMENT -> {
                if (acc.withdraw(amount)) {
                    drawer.acceptCash(amount);
                    System.out.printf("  %s: Оплата услуги %.2f₽. Баланс: %.2f₽\n",
                            name, amount, acc.getBalance());
                } else {
                    System.out.printf("  %s: Оплата не удалась (недостаточно средств)\n", name);
                }
            }
            case EXCHANGE -> {
                // Обмен рублей на доллары по курсу 92.5
                if (acc.withdraw(amount)) {
                    double usd = amount / 92.5;
                    // Предполагаем, что доллары есть в кассе
                    drawer.acceptCash(amount); // Рубли поступают в кассу
                    System.out.printf("  %s: Обмен %.2f₽ значит %.2f$\n", name, amount, usd);
                } else {
                    System.out.printf("  %s: Обмен не удался (недостаточно средств)\n", name);
                }
            }
        }
    }

    public void stopServing() {
        active = false;
        System.out.println(name + " прекращает прием клиентов");
    }

    public CashDrawer getDrawer() { return drawer; }
    public String getName() { return name; }
    public int getCustomersServed() { return customersServed; }
}