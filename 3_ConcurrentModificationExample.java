import java.util.*;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        // Create a list
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        try {
            System.out.println("Incorrect way: Throwing ConcurrentModificationException");
            for (String name : names) {
                if (name.equals("Bob")) {
                    names.remove(name);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught: " + e.getMessage());
        }
        System.out.println("\nCorrect way: Using an Iterator");
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name.equals("Bob")) {
                iterator.remove();
            }
        }
        System.out.println("Updated list: " + names);
    }
}
