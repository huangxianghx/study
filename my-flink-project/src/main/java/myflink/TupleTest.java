package myflink;

import org.apache.flink.api.java.tuple.Tuple3;

import java.util.ArrayList;
import java.util.List;

public class TupleTest {
    public static void main(String[] args) {

        List<Tuple3<Integer,String,Long>> tuple3List = new ArrayList<>();
        Tuple3<Integer,String,Long> A = new Tuple3<>();
        A.setField(1,0);
        A.setField("1",1);
        A.setField(1l,2);
        tuple3List.add(A);

        Tuple3<Integer,String,Long> B = new Tuple3<>();
        B.setField(2,0);
        B.setField("2",1);
        B.setField(2l,2);
        tuple3List.add(B);
        System.out.println(tuple3List);
    }
}
