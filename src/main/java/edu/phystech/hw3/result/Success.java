package edu.phystech.hw3.result;

import java.util.function.Function;

/**
 * @author kzlv4natoly
 */
public final class Success<T> implements Result<T> {
    private final T value;
    public Success(T value) {
        this.value = value;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public T getOrDefault(T defaultValue) {
        return null;
    }

    @Override
    public Throwable getExceptionOrNull() {
        return null;
    }

    @Override
    public <R> Result<R> map(Function<T, R> transform) {
        return null;
    }

}
