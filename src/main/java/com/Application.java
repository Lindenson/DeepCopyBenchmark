package com;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.rits.cloning.Cloner;
import org.apache.commons.lang3.SerializationUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@State(Scope.Benchmark)
public class Application {

    static ObjectMapper om = new ObjectMapper();
    static PodamFactory factory = new PodamFactoryImpl();
    public Data1 bd;

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }


    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @Fork(value =3)
    @BenchmarkMode(Mode.Throughput)
    public void testMapper(Application plan, Blackhole blackhole) {
        try {
                String as = om.writeValueAsString(plan.bd);
                Data1 new_bd = om.readValue(as, Data1.class);
//                if (!new_bd.equals(bd)) throw new RuntimeException();
//                new_bd.setExpireDateTime(new Date());
                if (new_bd.equals(plan.bd)) throw new RuntimeException();
                blackhole.consume(new_bd);
        }
        catch (Exception e) {}
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @Fork(value = 3)
    @BenchmarkMode(Mode.Throughput)
    public void testMapperBuffer(Application plan, Blackhole blackhole) {
        try {
                TokenBuffer tb = new TokenBuffer(om, false);
                om.writeValue(tb, plan.bd);
                Data1 bd_n  = om.readValue(tb.asParser(), Data1.class);
//                if (!bd_n.equals(bd)) throw new RuntimeException();
//                bd_n.setExpireDateTime(new Date());
                if (bd_n.equals(plan.bd)) throw new RuntimeException();
                blackhole.consume(bd_n);
        }
        catch (Exception e) {}
    }

    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @Fork(value = 3)
    @BenchmarkMode(Mode.Throughput)
    public void testMapperTreeNode(Application plan, Blackhole blackhole) {
        try {
            JsonNode jsonNode = om.valueToTree(plan.bd);
            Data1 new_bd = om.treeToValue(jsonNode, Data1.class);
//            if (!new_bd.equals(bd)) throw new RuntimeException();
//            new_bd.setExpireDateTime(new Date());
            if (new_bd.equals(plan.bd)) throw new RuntimeException();
            blackhole.consume(new_bd);
        }
        catch (Exception e) {}
    }


    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @Fork(value = 3)
    @BenchmarkMode(Mode.Throughput)
    public void testCommonsDeepClone(Application plan, Blackhole blackhole) {
        try {
                Data1 new_bd = (Data1) SerializationUtils.clone(plan.bd);
//                if (!new_bd.equals(bd)) throw new RuntimeException();
//                new_bd.setExpireDateTime(new Date());
                 if (new_bd.equals(plan.bd)) throw new RuntimeException();
                 blackhole.consume(new_bd);
        }
        catch (Exception e) {}
    }


    @Benchmark
    @Warmup(iterations = 2)
    @Measurement(iterations = 6)
    @Fork(value = 3)
    @BenchmarkMode(Mode.Throughput)
    public void testJavaClone(Application plan, Blackhole blackhole) {
        try {
            Cloner cloner=new Cloner();
            Data1 new_bd=cloner.deepClone(plan.bd);
            if (new_bd.equals(plan.bd)) throw new RuntimeException();
            blackhole.consume(new_bd);
        }
        catch (Exception e) {}
    }


    @Setup(Level.Iteration)
    public void setUp() {
        bd = factory.manufacturePojoWithFullData(Data1.class);
    }
}


