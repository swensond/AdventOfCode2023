package Part1.src;

import java.util.stream.Stream;

public class D5Map {
    public Long source;
    public long destination;
    public long range;

    public D5Map(String input) {
        long[] inputs = Stream
                .of(input.split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        source = inputs[1];
        destination = inputs[0];
        range = inputs[2];
    }

    public boolean inRange(Long value) {
        return value >= source && value < source + range;
    }

    public long calculateDestination(long value) {
        return value + destination - source;
    }
}
