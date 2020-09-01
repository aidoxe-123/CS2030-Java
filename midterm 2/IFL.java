import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;

class IFL<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */
static <T> IFL<T> empty() {
  return new IFL<>(() -> null, null);
}
    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */
if (list.isEmpty()) {
   return IFL.empty();
} else {
   List<T> list2 = new ArrayList<>();
   for (T t : list) {
      list2.add(t);
   }
   T headElement = list2.remove(0);
   return new  IFL<>(() -> headElement, () -> IFL.of(list2));
}
        /* OF END: DO NOT REMOVE!!! */
    }

    boolean allMatch(Predicate<? super T> predicate) {
        /* ALLMATCH START: DO NOT REMOVE!!! */
if (this.tail == null) {
   return head.get() == null ? true : predicate.test(head.get());
} else {
   T headElement = head.get();
   if (headElement == null || predicate.test(headElement)) {
      return tail.get().allMatch(predicate);
   } else {
      return false; 
   }
}
        /* ALLMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */

/* ADDITIONAL CODE END: DO NOT REMOVE */
