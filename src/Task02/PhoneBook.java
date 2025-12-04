package Task02;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private final Set<Contact> contacts;
    private final IContactStorage storage;

    public PhoneBook(IContactStorage storage) {
        this.storage = storage;
        this.contacts = new HashSet<>(storage.loadContacts());
    }

    public boolean addContact(Contact contact) {
        if (contact.getPhones().isEmpty()) {
            System.out.println("Ошибка: у контакта должен быть хотя бы один телефон!");
            return false;
        }
        if (contacts.stream().anyMatch(c -> c.sharesPhoneWith(contact))) {
            System.out.println("Ошибка: один из номеров уже используется в другом контакте!");
            return false;
        }
        contacts.add(contact);
        storage.saveContacts(contacts);
        return true;
    }

    public List<Contact> findByName(String query) {
        String q = query.toLowerCase();
        return contacts.stream()
                .filter(c -> c.getFirstName().toLowerCase().contains(q) ||
                        c.getLastName().toLowerCase().contains(q) ||
                        (c.getNickname() != null && c.getNickname().toLowerCase().contains(q)))
                .sorted(Comparator.comparing(Contact::getLastName)
                        .thenComparing(Contact::getFirstName))
                .collect(Collectors.toList());
    }

    public Contact findByPhone(String phone) {
        String normalized = phone.replaceAll("[\\s\\-()]", "");
        return contacts.stream()
                .filter(c -> c.hasPhone(normalized))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAllNames() {
        return contacts.stream()
                .map(c -> c.getFullName() + (c.getNickname() == null ? "" : " (" + c.getNickname() + ")"))
                .sorted()
                .collect(Collectors.toList());
    }

    public Set<Contact> getAllContacts() {
        return Set.copyOf(contacts);
    }
}