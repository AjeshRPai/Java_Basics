package PerformanceComparisons;

import org.apache.commons.lang3.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 10, time = 1)
public class StringComparisons {

    @Param({"", "nonEmptyString"})
    private String strParams;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public boolean nullAndIsEmpty() {
        return strParams == null || strParams.isEmpty();
    }

    @Benchmark
    public boolean postEquals() {
        return strParams == null || strParams.equals("");
    }

    @Benchmark
    public boolean preEquals() {
        return "".equals(strParams);
    }

    @Benchmark
    public boolean apacheEquals() {
        return StringUtils.isEmpty(strParams);
    }

}