   import java.util.*;
   class D {
      static <E, T extends E> List<E> add(List<E> list, T element) {
         List<E> temp = new ArrayList<>();
         Iterator<E> iterator = list.iterator();
         while (iterator.hasNext()) {
            temp.add(iterator.next());
         }
         temp.add(element);
         return temp;
      }
      static <E, T extends E> List<E> join(List<E> list1, List<T> list2) {
         if (list1 == list2) {
            return list1;
         }
         List<E> temp = new ArrayList<>();
         Iterator<E> iterator1 = list1.iterator();
         while (iterator1.hasNext()) {
            temp.add(iterator1.next());
         }
         Iterator<T> iterator2 = list2.iterator();
         while (iterator2.hasNext()) {
            temp.add(iterator2.next());
         }
         return temp;
      }
   }
