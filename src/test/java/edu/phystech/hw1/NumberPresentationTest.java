package edu.phystech.hw1;

import java.util.Iterator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class SystemNumbersGenerator implements Iterator<String> {
    private int n;
    private int system;

    private String getSymbol(int digit) {
        if (digit < 10) { return String.valueOf(digit); }
        if (digit == 10) { return "a"; }
        if (digit == 11) { return "b"; }
        if (digit == 12) { return "c"; }
        if (digit == 13) { return "d"; }
        if (digit == 14) { return "e"; }
        return "F";
    }

    public SystemNumbersGenerator(int n, int system) {
        this.n = n;
        this.system = system;
    }

    @Override
    public boolean hasNext() {
        return n > 0;
    }

    @Override
    public String next() {
        int result = this.n % this.system;
        this.n /= this.system;
        return this.getSymbol(result);
    }
}

public class NumberPresentationTest {

    private static String generateString(Iterator<String> iterator) {

        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        return sb.reverse().toString();
    }

    private static String toBinary(int x) {
//        return Integer.toBinaryString(x); можно сделать так,
//        но подозреваю, что в задачнии подразумевалось каписать код самому
        if (x == 0) { return "0"; }
        return generateString(new SystemNumbersGenerator(x, 2));
    }

    private static String toOct(int x) {
        if (x == 0) { return "0"; }
        return generateString(new SystemNumbersGenerator(x, 8));
    }

    private static String toHex(int x) {
        if (x == 0) { return "0"; }
        return generateString(new SystemNumbersGenerator(x, 16));
    }

    @Test
    public void binaryPresentation() {
        Assertions.assertEquals("0", toBinary(0));
        Assertions.assertEquals("1", toBinary(1));

        Assertions.assertEquals("10", toBinary(2));
        Assertions.assertEquals("100", toBinary(4));
        Assertions.assertEquals("10000000", toBinary(128));

        Assertions.assertEquals("11111111", toBinary(255));
        Assertions.assertEquals("11111111", toBinary(255));

        Assertions.assertEquals("100101100100110000010", toBinary(1231234));
    }


    @Test
    public void octPresentation() {
        for (int i = 0; i < 1; ++i) {
            Assertions.assertEquals(String.valueOf(i), toOct(i));
        }

        Assertions.assertEquals("10", toOct(8));
        Assertions.assertEquals("100", toOct(64));
        Assertions.assertEquals("200", toOct(128));

        Assertions.assertEquals("377", toOct(255));

        Assertions.assertEquals("4544602", toOct(1231234));
    }

    @Test
    public void hexPresentation() {
        for (int i = 0; i < 10; ++i) {
            Assertions.assertEquals(String.valueOf(i), toHex(i));
        }

        Assertions.assertEquals("10", toHex(16));
        Assertions.assertEquals("100", toHex(256));
        Assertions.assertEquals("200", toHex(512));

        Assertions.assertEquals("21e", toHex(542));

        Assertions.assertEquals("45b0c2", toHex(4567234));
    }


}

