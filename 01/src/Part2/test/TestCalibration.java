package Part2.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import Part2.src.Calibration;

public class TestCalibration {
    private final Calibration calibrator = new Calibration();

    @Test
    public void sample() throws IOException {
        assertEquals(Integer.valueOf(281), calibrator.calculate("01/src/Part2/test/input"));
    }
}
