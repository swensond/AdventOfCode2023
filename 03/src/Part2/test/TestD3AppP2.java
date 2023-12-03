package Part2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part2.src.D3AppP2;

public class TestD3AppP2 {
    @Test
    public void TestSumOfGearRatios() {
        assertEquals((Integer) 467835, D3AppP2.SumOfGearRatios("03/src/Part2/test/input"));
    }
}
