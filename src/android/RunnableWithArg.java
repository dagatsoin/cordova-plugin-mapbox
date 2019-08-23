package com.dagatsoin.plugins.mapbox;

public interface RunnableWithArg<T> {

    /**
     * Similar to the runnable interface but it accepts an argument
     *
     * @param arg any type of argument
     */
    void run(T arg);
}
