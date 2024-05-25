package edu.phystech.hw3.result;

import java.util.function.Supplier;

public class ResultUtil {
     public static <T>  Result<T> execute(Supplier<T> function) {
         try {
             return new Success<>(function.get());
         } catch (Throwable e) {
             return new Failure<>(e);
         }
     }
}
