class LastDigitsOfHashCode implements Transformer<Object,Integer> {
 private int numOfDigits;
 LastDigitsOfHashCode(int i) {
  numOfDigits = i;
 }
 @Override
 public Integer transform(Object o) {
  int hashCode = o.hashCode();
  int sum = 0;
  for (int i = 0; i < numOfDigits; i++) {
   sum+= Math.pow(10, i) * (Math.abs(hashCode) % 10);
   hashCode = hashCode / 10;
  }
  return sum;
 }
}
