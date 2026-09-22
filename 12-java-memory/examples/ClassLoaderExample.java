public class ClassLoaderExample {
    static void main() {
        Class<?> type = java.util.ArrayList.class;
        System.out.println(type.getName());
        System.out.println(type.getClassLoader());;
    }
}
