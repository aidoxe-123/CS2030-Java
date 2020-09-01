import java.util.function.Function;
class Voem<T> {
   private T t;
   private String msg;
   Voem(T t, String msg) {
      this.t = t;
      this.msg = msg;
   }
   public static <T> Voem<T> ok(T v) {
      return new Voem<>(v, null);
   }
   public static  <T> Voem<T> fail(String msg) {
      return new Voem<>(null, msg);
   }
   public String toString() {
      if (this.msg != null) {
         return "Oops: " + this.msg;
      } else if (this.t == null) {
         return Voem.fail("null").toString();
      } else {
         return "Done: " + this.t.toString();
      }
   }
   public <R> Voem<R> map(Function<? super T, ? extends R> mapper) {
      try {
         if (this.msg != null) {
            return Voem.fail(msg);
         } else {
            return Voem.ok(mapper.apply(t));
         }
      } catch (Exception e) {
         return Voem.fail(e.getMessage());
      }
   }
   public <R> Voem<R> flatMap(Function<? super T, ? extends Voem<R>> mapper) {
      try {
          if (this.msg != null) {
            return Voem.fail(msg);
         } else {
             return mapper.apply(this.t);
          }
      } catch (Exception e) {
         return Voem.fail(e.getMessage());
      }
   }
   public T orElse(T other) {
       if (this.msg == null) {
         return this.t;
       } else {
          return other;
       }
   }
}
