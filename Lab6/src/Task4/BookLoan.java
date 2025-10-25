package Task4;

import java.util.Date;

public class BookLoan {
    private String title;
    private String author;
    private int cardNumber;
    private Date issueDate;
    private int daysIssued;
    private Date returnDate;

    public BookLoan(String title, String author, int cardNumber, Date issueDate, int daysIssued) {
        this.title = title;
        this.author = author;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.daysIssued = daysIssued;
        this.returnDate = null;
    }

    public void returnBook(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue(Date currentDate) {
        if (returnDate != null) {
            return false;
        }
        long dueTime = issueDate.getTime() + (daysIssued * 24L * 60 * 60 * 1000);
        return currentDate.getTime() > dueTime;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}