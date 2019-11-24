package dz_7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;



public class Main {

    static ExecutorService forkJoinPool = Executors.newWorkStealingPool();
    static HashMap<Integer,BigInteger> vocabulary = new HashMap<>();

    public static void main(String[] args) {


        int[] array = {10,400,240,1000};
        List<Callable<BigInteger>> callables = getCallablesFromArray(array);

        List<BigInteger> results = new ArrayList<>();
        try {
            List<Future<BigInteger>> futuresList = forkJoinPool.invokeAll(callables);

            for (int i = 0; i < futuresList.size() ; i++) {
                results.add(futuresList.get(i).get().multiply(futuresList.get(i+1).get()));
                i++;

            }

        }
        catch (ExecutionException e){

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        results.stream().forEach(s ->{
            System.out.println(s.toString());
        });

    }


    public static List<Callable<BigInteger>> getCallablesFromArray(int[] array){
        List<Callable<BigInteger>> callables = new ArrayList<>();
        for (int n: array) {
            callables.add(new Factorial(1,split(n)));
            callables.add(new Factorial(split(n),n));
        }

        return callables;
    }

    public static int split(int i){
        return i/2;
    }

}
