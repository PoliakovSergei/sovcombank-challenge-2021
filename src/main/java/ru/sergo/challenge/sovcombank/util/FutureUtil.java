package ru.sergo.challenge.sovcombank.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.Future;

@UtilityClass
public class FutureUtil {
    public <T> T getFromFutureOrNull(Future<T> inputFuture) {
        try {
            return inputFuture.get();
        } catch (Exception e) {
            return null;
        }
    }
}
