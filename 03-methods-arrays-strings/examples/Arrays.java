public class Arrays {

    public static void main(String[] args) {

        int[] numbers = {10, 20, 30, 40, 50};

        System.out.println("First: " + numbers[0]);
        System.out.println("Second: " + numbers[1]);

        numbers[0] = 100;

        System.out.println("Updated first: " + numbers[0]);
        System.out.println("Length: " + numbers.length);
    }
}
