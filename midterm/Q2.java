   import java.util.List;
   import java.util.ArrayList;
   import java.util.Iterator;

   interface TypeCaster<S, T> {
      T cast(S s);
   }
   class ToString<S> implements TypeCaster<S, String> {
      @Override
      public String cast(S s) {
         return s.toString();
      }
   }
   class Round implements TypeCaster<Double, Integer> {
      @Override
      public Integer cast(Double s) {
         return (int) Math.round(s);
      }
   }
   class ToList<T> implements TypeCaster<T[], List<T>> {
      @Override
      public List<T> cast(T[] arr) {
         List<T> list = new ArrayList<>();
         for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
         }
         return list;
      }
   }
   class ListCaster {
      static <S,T> List<T> castList(List<S> list, TypeCaster<? super S, ? extends T> typeCaster) {
         List<T> newList = new ArrayList<>();
         Iterator<S> iterator = list.iterator();
         while (iterator.hasNext()) {
            newList.add(typeCaster.cast(iterator.next()));
         }
         return newList;
      }
   }
