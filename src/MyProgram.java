import java.util.function.Consumer;
import java.util.stream.IntStream;


public class MyProgram {



    public static void main(String[] args) {
        int[] tab = {-9, 3, -8, 7, -6, 2, -1};
        System.out.println("Les entiers positifs (MonFiltre): ");
        affichagePositifInterfaced(tab);
        System.out.println("Les entiers positifs (Consumer): ");
        affichagePositifConsumed(tab);
        System.out.println("Les entiers positifs (Lambda): ");
        affichagePositif(tab);
        System.out.println("Les entiers négatifs : ");
        affichageNegatif(tab);
        System.out.println("Les entiers  pairs : ");
        affichagePair(tab);
    }

    public static void affichagePositifInterfaced(int[] tab) {
        MyFilter<int[]> filter = vars ->   {
            for (int i : vars) {
                if (i > 0) System.out.println(i);
            }
        };
        filter.apply(tab);
    }

    public static void affichagePositifConsumed(int[] tab) {
        Consumer<int[]> consume = vars -> {
            for (int i : vars) {
                if (i > 0) System.out.println(i);
            }
        };
        consume.accept(tab);
    }

    public static void affichagePositif(int[] tab) {
        IntStream.of(tab).filter(i -> i > 0).forEach(System.out::println);
    }
    public static void affichageNegatif(int[] tab) {
        IntStream.of(tab).filter(i -> i < 0).forEach(System.out::println);
    }
    public static void affichagePair(int[] tab) {
        IntStream.of(tab).filter(i -> i % 2 == 0).forEach(System.out::println);
    }
}











