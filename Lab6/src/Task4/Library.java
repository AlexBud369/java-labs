package Task4;

public class Library {
    private LibraryCard[] cards;
    private int cardCount;
    private BookLoan[] loans;
    private int loanCount;

    public Library(int maxCards, int maxLoans) {
        cards = new LibraryCard[maxCards];
        loans = new BookLoan[maxLoans];
        cardCount = 0;
        loanCount = 0;
    }

    public LibraryCard issueCard(String name, String group) {
        LibraryCard card = new LibraryCard(cardCount + 1, name, group);
        cards[cardCount] = card;
        cardCount++;
        return card;
    }

    public void issueBook(String title, String author, LibraryCard card, java.util.Date issueDate, int days) {
        BookLoan loan = new BookLoan(title, author, card.getNumber(), issueDate, days);
        loans[loanCount] = loan;
        loanCount++;
    }

    public void returnBook(String title, int cardNumber, java.util.Date returnDate) {
        for (int i = 0; i < loanCount; i++) {
            // Исправление: используем getter вместо прямого доступа к private полю
            if (loans[i].getTitle().equals(title) && loans[i].getCardNumber() == cardNumber && loans[i].getReturnDate() == null) {
                loans[i].returnBook(returnDate);
                return;
            }
        }
    }

    public void findDebtors(java.util.Date currentDate) {
        System.out.println("Должники:");
        boolean found = false;
        for (int i = 0; i < loanCount; i++) {
            if (loans[i].isOverdue(currentDate)) {
                int cardNum = loans[i].getCardNumber();
                LibraryCard card = findCard(cardNum);
                if (card != null) {
                    System.out.println(card + " просрочил книгу: " + loans[i].getTitle() + " (" + loans[i].getAuthor() + ")");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Должников нет.");
        }
    }

    private LibraryCard findCard(int number) {
        for (int i = 0; i < cardCount; i++) {
            if (cards[i].getNumber() == number) {
                return cards[i];
            }
        }
        return null;
    }
}