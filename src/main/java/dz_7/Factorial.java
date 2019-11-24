package dz_7;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Класс подсчета факториала
 */
public class Factorial implements Callable<BigInteger> {
    private int start;
    private int end;

    public Factorial(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public BigInteger call() throws Exception {
        BigInteger acc = BigInteger.valueOf(start);
        for (int i = start; i <= end; i++) {
            acc = acc.multiply(BigInteger.valueOf(i));
        }
        return acc;
    }
}
