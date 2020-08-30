package love.moon.java8.stream;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SinkChain {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void main(String[] args) {
        int[] source = {1,2,3,4,5};
        Spliterator spliterator = Arrays.stream(source).spliterator();

        //setup upstream
        Sink<Integer> sink0 = new Sink<Integer>("sink0", null);
        Sink<Integer> sink4 = sink0.op("sink1").op("sink2").op("sink3").op("sink4 terminal");

        //setup downstream chain
        Sink wrappedSink = wrapSink(sink4);
        //now get the first (source) stage
        assert(wrappedSink == sink0);
        //in one loop, handle elements: 1,2,3,4,5
        spliterator.forEachRemaining(wrappedSink);
    }
    public static Sink wrapSink(Sink sink) {
        while(sink.upstream != null) {
            sink.upstream.downstream = sink;
            sink = sink.upstream;
        }
        return sink;
    }

    static class Sink<T> implements Consumer<T>{
        private Sink upstream;
        private Sink downstream;
        private String name;  //intermediate operation name

        public Sink(String name, Sink upstream) {
            this.name = name;
            this.upstream = upstream;
        }

        public Sink op(String name) {
            return new Sink(name, this);
        }

        @Override
        public void accept(T t) {
            System.out.println(name + ": handles " + t);
            if (downstream != null)
                downstream.accept(t);
        }
    }
}