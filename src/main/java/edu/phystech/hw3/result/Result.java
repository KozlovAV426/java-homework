package edu.phystech.hw3.result;

import java.util.function.Function;

public sealed interface Result<T> permits Success, Failure {
    boolean isFailure();
    boolean isSuccess();
    T getOrDefault(T defaultValue);
    Throwable getExceptionOrNull();
    <R> Result<R> map(Function<T, R> transform);
}
