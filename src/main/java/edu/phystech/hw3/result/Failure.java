package edu.phystech.hw3.result;

import java.util.function.Function;

public final class Failure<T> implements Result<T> {
    private final Throwable e;
    public Failure(Throwable e) {
        this.e = e;
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
