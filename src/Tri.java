import java.util.stream.Stream;
import java.util.Random;




    public class Tri {

        public static void main(String[] args) {
            Integer[] randomsInt = Stream.generate(() -> new Random().nextInt(21))
                    .limit(6).sorted().toArray(Integer[]::new);
            Stream.of(randomsInt).forEach(System.out::println);

            System.out.println("Le plus petit est : " + Stream.of(randomsInt).findFirst().get());


            System.out.println("La somme des nombres supérieurs à 3 est : " + Stream.of(randomsInt).filter(i -> i > 3).mapToInt(Integer::intValue).sum());
        }
    }
