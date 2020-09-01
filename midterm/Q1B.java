   import java.util.*;
   class A {
      private List<Integer> list = new ArrayList<>();
      A(int x) {
         this.list.add(x);
      }
      private A(A oldA, int x) {
         List<Integer> temp = oldA.getList();
         Iterator<Integer> iterator = temp.iterator();
         while (iterator.hasNext()) {
            this.list.add(iterator.next());
         }
         this.list.add(x);
      }
      A next(int x) {
         return new A(this, x);
      }
      List<Integer> getList() {
         List<Integer> temp = new ArrayList<>();
         Iterator<Integer> iterator = this.list.iterator();
         while (iterator.hasNext()) {
            temp.add(iterator.next());
         }
         return temp;
      }
      @Override
      public String toString() {
         String myString = "";
         Iterator<Integer> iterator = this.list.iterator();
         while (iterator.hasNext()) {
            myString = myString + "[A:" + iterator.next() + "]";
         }
         return myString;
      }
   }
