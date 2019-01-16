package myflink;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import source.SourceFromMySQL;

public class MysqlFlink {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource mysqlStream = env.addSource(new SourceFromMySQL());

        // 等同于 mysqlStream.print();
        mysqlStream.addSink(new PrintSinkFunction());

        env.execute("Flink add data sourc");
    }
}
