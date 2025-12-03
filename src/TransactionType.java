public enum TransactionType {
    DEPOSIT("Пополнение счёта"),
    WITHDRAW("Снятие наличных"),
    TRANSFER("Перевод средств"),
    PAYMENT("Оплата услуги"),
    EXCHANGE("Обмен валюты");

    private final String title;

    TransactionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}