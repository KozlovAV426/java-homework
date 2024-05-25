package edu.phystech.hw2;

import java.util.Objects;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class IntHolder {

    private int value;

    public IntHolder(int value) {
        this.value = value;
    }

    public static IntHolder valueOf(int x) {
        return new IntHolder(x);
    }

    public int getValue() {
        return value;
    }

    public void swap(IntHolder other) {
        int temp = this.value;
        this.value = other.getValue();
        other.value = temp;
    }

    public IntHolder plus(IntHolder rhv) {
        return new IntHolder(this.value + rhv.getValue());
    }

    public IntHolder minus(IntHolder rhv) {
        return new IntHolder(this.value - rhv.getValue());
    }

    public IntHolder times(IntHolder rhv) {
        return new IntHolder(this.value * rhv.getValue());
    }

    public IntHolder div(IntHolder rhv) {
        if (rhv.getValue() != 0) {
            return new IntHolder(this.value / rhv.getValue());
        } else {
            System.out.println("Division by zero error");
            return null;
        }
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntHolder intHolder = (IntHolder) obj;
        return value == intHolder.value;
    }
    public int hashCode() {
        return Objects.hash(value);
    }
}
public class IntHolderTest {

    @Test
    public void swapWorks() {
        IntHolder left = IntHolder.valueOf(10);
        IntHolder right = IntHolder.valueOf(12);

        left.swap(right);

        Assertions.assertEquals(12, left.getValue());
        Assertions.assertEquals(10, right.getValue());

        left.swap(right);

        Assertions.assertEquals(10, left.getValue());
        Assertions.assertEquals(12, right.getValue());

    }

    @Test
    public void equalsAndHashcodeAreConsistent() {
        IntHolder left = IntHolder.valueOf(10);
        IntHolder right = new IntHolder(10);

        Assertions.assertEquals(left, right);
        Assertions.assertEquals(left.hashCode(), right.hashCode());
    }

    @Test
    public void operatorsWork() {
        IntHolder left = IntHolder.valueOf(10);
        IntHolder right = IntHolder.valueOf(2);
        Assertions.assertEquals(10, left.getValue());
        Assertions.assertEquals(2, right.getValue());

        Assertions.assertEquals(12, left.plus(right).getValue());
        Assertions.assertEquals(8, left.minus(right).getValue());
        Assertions.assertEquals(40, left.times(right).times(right).getValue());
        Assertions.assertEquals(5, left.div(right).getValue());

    }

}

