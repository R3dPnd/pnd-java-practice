package pnd.practice.questions.q2894;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DifferenceOfSumsTest {
    private DifferenceOfSums differenceOfSums;

    @BeforeEach
    void setUp() {
        differenceOfSums = new DifferenceOfSums();
    }

    @Test
    void testBasicCase() {
        // n=10, m=3
        // Numbers not divisible by 3: 1,2,4,5,7,8,10 = 37
        // Numbers divisible by 3: 3,6,9 = 18
        // Difference: 37 - 18 = 19
        assertEquals(19, differenceOfSums.differenceOfSums(10, 3));
    }

    @Test
    void testWhenAllNumbersDivisible() {
        // n=4, m=1
        // All numbers are divisible by 1
        // Numbers not divisible by 1: none = 0
        // Numbers divisible by 1: 1,2,3,4 = 10
        // Difference: 0 - 10 = -10
        assertEquals(-10, differenceOfSums.differenceOfSums(4, 1));
    }

    @Test
    void testWithLargerNumbers() {
        // n=20, m=7
        // Numbers not divisible by 7: 1,2,3,4,5,6,8,9,10,11,12,13,15,16,17,18,19,20 = 189
        // Numbers divisible by 7: 7,14 = 21
        // Difference: 189 - 21 = 168
        assertEquals(168, differenceOfSums.differenceOfSums(20, 7));
    }

    @Test
    void testWithEqualNAndM() {
        // n=5, m=5
        // Numbers not divisible by 5: 1,2,3,4 = 10
        // Numbers divisible by 5: 5 = 5
        // Difference: 10 - 5 = 5
        assertEquals(5, differenceOfSums.differenceOfSums(5, 5));
    }

    @Test
    void testWithSmallNumbers() {
        // n=1, m=2
        // Numbers not divisible by 2: 1 = 1
        // Numbers divisible by 2: none = 0
        // Difference: 1 - 0 = 1
        assertEquals(1, differenceOfSums.differenceOfSums(1, 2));
    }

    @Test
    void testWithConsecutiveDivisibleNumbers() {
        // n=8, m=2
        // Numbers not divisible by 2: 1,3,5,7 = 16
        // Numbers divisible by 2: 2,4,6,8 = 20
        // Difference: 16 - 20 = -4
        assertEquals(-4, differenceOfSums.differenceOfSums(8, 2));
    }
} 