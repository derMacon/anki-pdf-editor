package com.silas.projectlauncher.ui;

import java.io.IOException;

/**
 * https://stackoverflow.com/questions/18198176/java-8-lambda-function-that-throws-exception
 */
@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws IOException;
}
