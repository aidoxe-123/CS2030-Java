import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
class Addable {
      List<Addable> list = new ArrayList<>();
      Addable add(Addable element) {
         this.list.add(element);
         return this;
      }
   }
   class B extends Addable {
      B() {
      }
      @Override
      public String toString() {
         String myString = "B";
         Iterator<Addable> iterator = super.list.iterator();
         while (iterator.hasNext()) {
            myString = myString + iterator.next();
         }
         return myString;
      }

   }
   class C extends Addable{
      C() {
      }
   @Override
      public String toString() {
         String myString = "C";
         Iterator<Addable> iterator = list.iterator();
         while (iterator.hasNext()) {
            myString = myString + iterator.next();
         }
         return myString;
      }
   }
