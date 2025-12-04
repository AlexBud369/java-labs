package Task02;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final Integer birthYear;
    private final Map<String, String> phones; // номер → тип
    private final Set<String> emails;

    public Contact(String firstName, String lastName, String nickname, Integer birthYear) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.nickname = nickname == null || nickname.trim().isEmpty() ? null : nickname.trim();
        this.birthYear = birthYear;
        this.phones = new HashMap<>();
        this.emails = new HashSet<>();
    }

    public void addPhone(String phone, String type) {
        phones.put(normalizePhone(phone), type.trim());
    }

    public void addEmail(String email) {
        emails.add(email.trim().toLowerCase());
    }

    public boolean hasPhone(String phone) {
        return phones.containsKey(normalizePhone(phone));
    }

    public boolean sharesPhoneWith(Contact other) {
        return !Collections.disjoint(this.phones.keySet(), other.phones.keySet());
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNickname() { return nickname; }
    public Integer getBirthYear() { return birthYear; }
    public Map<String, String> getPhones() { return Map.copyOf(phones); }
    public Set<String> getEmails() { return Set.copyOf(emails); }

    private String normalizePhone(String phone) {
        return phone.replaceAll("[\\s\\-()]", "");
    }

    public static boolean isValidPhone(String phone) {
        String normalized = phone.replaceAll("[\\s\\-()]", "");
        return Pattern.matches("^\\+?[0-9]{10,15}$", normalized);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", email);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName());
        if (nickname != null) sb.append(" (").append(nickname).append(")");
        if (birthYear != null) sb.append(", ").append(birthYear).append(" г.р.");
        sb.append("\n");

        if (!phones.isEmpty()) {
            sb.append("Телефоны:\n");
            phones.forEach((phone, type) -> sb.append("  ").append(type).append(": ").append(phone).append("\n"));
        }
        if (!emails.isEmpty()) {
            sb.append("Email:\n");
            emails.forEach(e -> sb.append("  ").append(e).append("\n"));
        }
        return sb.toString().trim();
    }
}