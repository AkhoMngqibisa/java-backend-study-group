public class StringMethods {

    public static void main(String[] args) {

        String name = "Akhona";

        System.out.println("Length: " + name.length());
        System.out.println("Uppercase: " + name.toUpperCase());
        System.out.println("Lowercase: " + name.toLowerCase());
        System.out.println("First character: " + name.charAt(0));
        System.out.println("Contains 'hon': " + name.contains("hon"));
        System.out.println("Starts with A: " + name.startsWith("A"));
        System.out.println("Ends with a: " + name.endsWith("a"));
    }
}