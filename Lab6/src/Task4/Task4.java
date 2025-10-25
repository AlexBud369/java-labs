package Task4;

import java.util.Date;

/*
* В библиотеке решили автоматизировать учет книг, выданных студентам.
* Студент, для того чтобы получить книгу, должен получить читательский билет, если не получал ранее.
*  В читательском билете содержится информация:
- номер билета;
- фамилия и имя студента;
- номер группы.
При выдаче книги библиотекарь фиксирует в журнале:
- название и автора книги;
- номер билета, на который выдана книга;
- дата выдачи;
- на какое время выдана книга (в сутках).
При приеме книги библиотекарь фиксирует в журнале:
- название и автора книги;
- номер билета студента, который вернул книгу;
- дата возврата.
Разработать модель программы, которая бы позволила находить должников (студентов, которые не сдали книгу вовремя).

* */


public class Task4 {
    public static void main(String[] args) {
        Date currentDate = new Date(125, 9, 24);

        Library library = new Library(10, 10);

        LibraryCard card1 = library.issueCard("Иванов Иван", "Группа 101");
        LibraryCard card2 = library.issueCard("Петров Петр", "Группа 102");

        Date issueDate1 = new Date(125, 9, 10);
        Date issueDate2 = new Date(125, 9, 15);

        library.issueBook("Война и мир", "Толстой", card1, issueDate1, 10);
        library.issueBook("Преступление и наказание", "Достоевский", card2, issueDate2, 14);

        Date returnDate = new Date(125, 9, 20);
        library.returnBook("Преступление и наказание", card2.getNumber(), returnDate);

        library.findDebtors(currentDate);
    }
}