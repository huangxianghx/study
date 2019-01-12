package myflink;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class SocketWindowWordCount {

    /**
     * 1.定义环境入口
     * 2.定义数据输入
     * 3.定义数据处理算法
     * 4.定义数据输出
     * 5.执行入口逻辑
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //这是一个入口类，可以用来设置参数和创建数据源以及提交任务
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //DataStream 是 Flink 中做流处理的核心 API，上面定义了非常多常见的操作（如，过滤、转换、聚合、窗口、关联等）
//        DataStream<String> text = env.socketTextStream("192.168.64.129", 9000, "\n");
        DataStream<String> text = env.fromElements("hello","hello","world");
        // 解析数据，按 word 分组，开窗，聚合
        DataStream<Tuple2<String, Integer>> windowCounts = text
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    /**
                     * 将string转变为Tuple2<String, Integer>
                     * @param value 输入
                     * @param out 输出
                     */
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
                        out.collect(Tuple2.of(value, 1));
                    }
                })
                .keyBy(0);
//                .timeWindow(Time.seconds(5))
//                .sum(1);
        // 将数据流打印到控制台
        windowCounts.print();
//        windowCounts.writeAsText("G:\\flink.log");
        // 最后的 env.execute 调用是启动实际Flink作业所必需的。
        // 所有算子操作（例如创建源、聚合、打印）只是构建了内部算子操作的图形。
//        System.out.print(env.getExecutionPlan());
        // 只有在execute()被调用时才会在提交到集群上或本地计算机上执行
        //根据Environment类型觉得在本机执行还是将程序提交到集群执行

        //JobExecutionResult包含执行时间和计算结果
        JobExecutionResult result = env.execute("Socket Window WordCount");
//        System.out.println(result.getNetRuntime());
//        System.out.println(result.getAllAccumulatorResults());
    }
}
