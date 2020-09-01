import java.util.Scanner;
class Main6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num_c = sc.nextInt();
        Loader[] loaders = new Loader[270];
        //initialize loaders:
        for (int i = 0; i < 270; i++) {
            if ((i + 1) % 3 == 0) {
                loaders[i] = new RecycledLoader(i + 1);
            } else {
                loaders[i] = new Loader(i + 1);
            }
        }
        for (int i = 0; i < num_c; i++) {
            //read the data of each Cruise
            String reader = sc.next();
            reader += sc.nextLine();
            String[] Cruise_data = reader.split(" ");
            Cruise c;
            if (Cruise_data[0].charAt(0) == 'B') {
                c = new BigCruise(Cruise_data[0],
                        Integer.parseInt(Cruise_data[1]),
                        Integer.parseInt(Cruise_data[2]),
                        Integer.parseInt(Cruise_data[3]));
            } else {
                c = new SmallCruise(Cruise_data[0],
                        Integer.parseInt(Cruise_data[1]));
            }
            int c_LoadersRequired = c.getNumOfLoadersRequired();
            int counter = 0;
            while(counter < 270 && c_LoadersRequired > 0) {
                if (loaders[counter].canServe(c)) {
                    loaders[counter] = loaders[counter].serve(c);
                    c_LoadersRequired--;
                    System.out.println(loaders[counter].toString());
                } else { }
                counter++;
            }
        }
    }
}