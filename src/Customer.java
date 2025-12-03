import java.util.Random;

public class Customer implements Runnable {
    private final String name;
    private final Bank bank;
    private final BankAccount account;
    private final TransactionType operation;
    private final double amount;
    private static final Random r = new Random();

    public Customer(String name, Bank bank, BankAccount account) {
        this.name = name;
        this.bank = bank;
        this.account = account;
        this.operation = TransactionType.values()[r.nextInt(TransactionType.values().length)];
        this.amount = 500 + r.nextDouble() * 15_000;
    }

    @Override
    public void run() {
        try {
            // Клиент думает перед тем как подойти к кассе
            Thread.sleep(1000 + r.nextInt(3000));

            if (!bank.isOpen()) {
                System.out.println(name + " опоздал(а), банк уже закрывается");
                return;
            }

            System.out.println(name + " зашёл(ла) в банк значит " +
                    operation.getTitle() + " на сумму " +
                    String.format("%.2f₽", amount));

            Teller teller = bank.getRandomTeller();
            teller.acceptCustomer(this);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getName() { return name; }
    public BankAccount getAccount() { return account; }
    public TransactionType getOperation() { return operation; }
    public double getAmount() { return amount; }
}