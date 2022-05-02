# DeepCopyBenchmark

## The tests of different deep copy java tools:

### The tests:

```
           import com.fasterxml.jackson.databind.JsonNode;
           import com.fasterxml.jackson.databind.ObjectMapper;
           import com.fasterxml.jackson.databind.util.TokenBuffer;
           import com.rits.cloning.Cloner; //kostaskougios
           import org.apache.commons.lang3.SerializationUtils;

           static ObjectMapper om = new ObjectMapper();
           //Snippets ...
1)         //ObjectMapper plain
           String as = om.writeValueAsString(old);
           MyObject new = om.readValue(as, MyObject.class);
 
2)         //ObjectMapper with TokenBuffer
           TokenBuffer tb = new TokenBuffer(om, false);
           om.writeValue(tb, old);
           MyObject new  = om.readValue(tb.asParser(), MyObject.class);

3)         //ObjectMapper.valueToTree
           JsonNode jsonNode = om.valueToTree(old);
           MyObject new = om.treeToValue(jsonNode, MyObject.class);

4)         //commons
           MyObject new = (BotDetails) SerializationUtils.clone(old);  

5)         //kostaskougios Cloner
           Cloner cloner=new Cloner();                                 
           MyObject new=cloner.deepClone(old);
```

### The test results:
```
Benchmark    Mode  Cnt       Score      Error  Units
1)           thrpt   18  209365.201 ± 3866.063  ops/s
2)           thrpt   18  256798.691 ± 3846.831  ops/s
3)           thrpt   18  181536.212 ± 1892.652  ops/s
4)           thrpt   18   28112.084 ±  286.265  ops/s
5)           thrpt   18   80987.632 ± 1318.502  ops/s
```


### The fastest solution is ObjectMapper + TokenBuffer (the slowest one is commons util):

```
TokenBuffer tb = new TokenBuffer(objectmapperInstance, false);
om.writeValue(tb, old);
MyClass new  = om.readValue(tb.asParser(), MyClass.class);
```

<img src="https://media.istockphoto.com/vectors/big-smile-emoticon-with-thumbs-up-vector-id1124532572?k=20&m=1124532572&s=612x612&w=0&h=IXpPDP4EXROUqjakNqxhq-pxrUURTO1jwy7SQKmP6Rw=" width=140 height=120>

