package Task02;

import java.util.List;
import java.util.Set;

public interface IContactStorage {
    void saveContacts(Set<Contact> contacts);
    Set<Contact> loadContacts();
}