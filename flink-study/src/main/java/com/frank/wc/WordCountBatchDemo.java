package com.frank.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author: maxinhang.
 */
public class WordCountBatchDemo {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> lineDs = env.readTextFile("/Users/maxinhang/Code/mh_doc/flink-study/input/word.txt");

        FlatMapOperator<String, Tuple2<String, Integer>> flatMapOperator = lineDs.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                for (String s1 : s.split(" ")) {
                    collector.collect(new Tuple2<>(s1, 1));
                }
            }
        });

        UnsortedGrouping<Tuple2<String, Integer>> groupBy = flatMapOperator.groupBy(0);

        AggregateOperator<Tuple2<String, Integer>> sum = groupBy.sum(1);

        sum.print();
    }
}
