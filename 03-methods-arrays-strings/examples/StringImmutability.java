public class StringImmutability {

    public static void main(String[] args) {

        String name = "John";

        name.toUpperCase();

        System.out.println(name);

        name = name.toUpperCase();

        System.out.println(name);
    }
}