import java.util.function.Consumer;
import java.util.stream.IntStream;



    @FunctionalInterface
    interface MyFilter<E> {
        void apply(E e);
    }



