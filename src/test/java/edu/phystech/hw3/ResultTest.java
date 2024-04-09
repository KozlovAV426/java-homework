package edu.phystech.hw3;


import edu.phystech.hw3.result.Failure;
import edu.phystech.hw3.result.Result;
import edu.phystech.hw3.result.ResultUtil;
import edu.phystech.hw3.result.Success;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ResultTest {

    @Test
    public void successWorks() {
        int x = 11;
        Result<Integer> result = ResultUtil.execute(() -> x * x);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertFalse(result.isFailure());

        Result<String> stringResult = result.map(Object::toString);
        Assertions.assertTrue(stringResult.isSuccess());
        Assertions.assertFalse(stringResult.isFailure());

        Assertions.assertEquals("121", stringResult.getOrDefault(""));
    }

    @Test
    public void failureWorks() {
        RuntimeException e = new RuntimeException("Something went wrong!");
        Result<Integer> result = ResultUtil.execute(() -> {
            throw e;
        });

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertTrue(result.isFailure());

        Result<String> mapResult = result.map(x -> {
            Assertions.fail("This lambda shouldn't be executed");
            return null;
        });

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertTrue(result.isFailure());

        Assertions.assertEquals("", mapResult.getOrDefault(""));
        Assertions.assertEquals(e, mapResult.getExceptionOrNull());
    }

    // Для того, чтобы этот тест отработал, нужно поменять определение классов Success & Failure
    // (данная функциональность появилась с java19)
//
//    @Test
//    public void resultPatternDeconstructionWorks() {
//        int x = 11;
//        Result<Integer> result = ResultUtil.execute(() -> x * x);
//
//        Integer a = switch (result) {
//            case Success(var value) -> value;
//            case Failure(Throwable e) -> Assertions.fail();
//        };
//
//        Result<Integer> failureResult = ResultUtil.execute(() -> {
//            throw new RuntimeException(":(");
//        });
//
//        Integer b = switch (failureResult) {
//            case Success(var value) -> Assertions.fail();
//            case Failure(Throwable e) -> {
//                Assertions.assertEquals(":(", e.getMessage());
//                yield 1;
//            }
//        };
//    }
}
