class Test implements TypeCaster<Integer, Integer> {
    @Override
    public Integer cast(Integer i) {
        return new Integer(0);
    }
}
TypeCaster t
ToString ts
Round r
ToList tl
t = ts
t = r
t = tl
String s1 = new ToString<>().cast(123)
String s2 = new ToString<>().cast(List.of(1,2,3))
String s3 = new ToString<>().cast(Map.of("COM1", 117417))
s1
s2
s3
int i1 = new Round().cast(3.1415)
i1
int i2 = new Round().cast(7.9)
i2
List<Integer> list1 = new ToList<Integer>().cast(new Integer[]{1,2,3})
List<String> list2 = new ToList<String>().cast(new String[]{"Test1", "Test2"})
