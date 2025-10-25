package Task4;

public class LibraryCard {
    private int number;
    private String name;
    private String group;

    public LibraryCard(int number, String name, String group) {
        this.number = number;
        this.name = name;
        this.group = group;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Билет " + number + ": " + name + " (" + group + ")";
    }
}