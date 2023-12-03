package Part1.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Part1.src.D3App;

public class TestD3App {
    @Test
    public void TestSumOfEngineParts() {
        assertEquals((Integer) 4361, D3App.SumOfEngineParts("03/src/Part1/test/input"));
    }
}
