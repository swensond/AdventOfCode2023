package Part1.src;

import java.lang.reflect.Field;

public class CubeSet {
    public int red;
    public int green;
    public int blue;

    public CubeSet(int numberOfRedCubes, int numberOfGreenCubes, int numberOfBlueCubes) {
        red = numberOfRedCubes;
        green = numberOfGreenCubes;
        blue = numberOfBlueCubes;
    }

    public boolean greaterThanOrEqual(CubeSet a) {
        return red >= a.red && green >= a.green && blue >= a.blue;
    }

    public boolean equals(CubeSet a) {
        return red == a.red && green == a.green && blue == a.blue;
    }

    public void setNumber(int number, String color) throws NoSuchFieldException, IllegalAccessException {
        Field field = getClass().getDeclaredField(color);
        field.set(this, number);
    }
}
