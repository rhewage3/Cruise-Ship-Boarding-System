
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.*;


public class Main {

    public static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cabin_count = 0;
        String option;

        while (true) {
            System.out.println("\n----------------------------------------------------" +
                    "\n---------------------MENU---------------------------" +
                    "\n----------------------------------------------------");

            System.out.println("V : View all cabins" +
                    "\nE : Display empty cabins" +
                    "\nA : Add a customer to a cabin" +
                    "\nD : Delete customer from cabin" +
                    "\nS : Store program data into a file" +
                    "\nF : Find Cabin by customer name" +
                    "\nL : Load program data from file" +
                    "\nO : View passengers ordered in alphabetical order" +
                    "\nT : To view total expense"+
                    "\nQ : Exit the program");

            System.out.println("----------------------------------------------------");
            System.out.println("Select a option : ");
            option = input.next();

//checking whether cabin available or unavailable.
            if ((option.toLowerCase()).charAt(0) == 'a') {
                if (count < 36) {
                    if (count != 0 && count % 3 == 0) {
                        cabin_count++;
                    }
                    new Cabin(input);
                    count++;
                } else {
                    System.out.println("\nCabins currently unavailable.");
                }

            } else if ((option.toLowerCase()).charAt(0) == 'v') {
                if (count > 0) {
//checking if Customer are included before.
                    Cabin.viewAllCabins(cabin_count + 1);

                } else {
                    System.out.println("\nNo Customers included.");
                }

            } else if ((option.toLowerCase()).charAt(0) == 'e') {
                Cabin.vieAllEmptyCabins();
            } else if ((option.toLowerCase()).charAt(0) == 'd') {
                Cabin.remove(input, count);
                cabin_count = count / 3;
            } else if ((option.toLowerCase()).charAt(0) == 'f') {
                Cabin.findByName(input);
            } else if ((option.toLowerCase()).charAt(0) == 's') {
                Cabin.storeInFile(count);
            } else if ((option.toLowerCase()).charAt(0) == 'l') {
                Cabin.loadFromFile();
            } else if ((option.toLowerCase()).charAt(0) == 'o') {
                Cabin.sortCustomers(count);
            } else if ((option.toLowerCase()).charAt(0) == 't') {
                Cabin.total();
            }else if ((option.toLowerCase()).charAt(0) == 'q'){
                break;
            }else {
                System.out.println("please enter a valid input");
            }
        }


    }



    static class Cabin{

        static Customer[][] ship_cabins = new Customer[12][3];
        public Cabin(Scanner in) {
            System.out.println("\nEnter your First name:");
            String f_name = in.next();
            System.out.println("\nEnter your Surname:");
            String s_name = in.next();
            int count = 0;

            while(true){

                try {
                    if(!(ship_cabins[count/3][count%3].f_name.equals(null))){
                        count++;
                    }
                } catch (Exception e) {
                    ship_cabins[count/3][count%3] = new Customer(f_name, s_name);
                    break;
                }
            }
        }

        public static void viewAllCabins(int cab_count){
            for(int i =0; i<cab_count; i++){
                System.out.println(String.format("\nCabin number %d occupied by:", i+1));
                for(int j = 0; j<3; j++){
                    try {
                        if (!(ship_cabins[i][j].f_name).equals(null)){
                            System.out.println(String.format("   %s %s", ship_cabins[i][j].f_name, ship_cabins[i][j].l_name));
                        }
                    } catch (Exception e) {}
                }
            }

        }

        public static void vieAllEmptyCabins(){

            for(int i = 0; i < 12; i++){
                System.out.println(String.format("\n Cabin number %d", i+1));
                int count = 0;
                for(int j =0; j < 3; j++){

                    try {

                        if(!ship_cabins[i][j].f_name.equals(null)){}

                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println(String.format("- Passenger seat %d is empty", j+1));
                        count++;
                    }
                }
                if(count == 0){
                    System.out.println("  No seats currently available \n");
                }
            }
        }

        public static void remove(Scanner in, int count) {
            System.out.println("\nEnter the First Name of customer:");
            String f_name = in.next();
            System.out.println("\nEnter the surname of customer:");
            String l_name = in.next();
            int local_count = count;
            for(int i = 0; i < 12; i++){

                for(int j = 0; j < 3; j++){

                    try {

                        if (ship_cabins[i][j].f_name.equals(f_name) &&
                                ship_cabins[i][j].l_name.equals(l_name)){

                            ship_cabins[i][j] = new Customer(null,null);
                            count--;

                        }

                    } catch(Exception e){}
                }
            }
            if(count == local_count){
                System.out.println("\n\nInvalid Passsenger name !\n");
            }
        }

        public static void findByName(Scanner in) {
            System.out.println("\nPlease enter passenger's first name:");
            String f_name = in.next();
            System.out.println("\nPlease enter passenger's surname:");
            String l_name = in.next();

            for(int i = 0; i < 12; i++){

                for(int j = 0; j < 3; j++){

                    try {

                        if (ship_cabins[i][j].f_name.equals(f_name) &&
                                ship_cabins[i][j].l_name.equals(l_name)){

                            System.out.println(String.format("\n\nPassenger %s %s,\n Located at Cabin number %d, Passenger seat %d", f_name, l_name, i+1, j+1));

                        }

                    } catch(Exception e){}
                }
            }
        }
        public static void storeInFile(int count){
            try {
                FileWriter file = new FileWriter("data_2.txt");
                int local_count = 0;
                while(local_count< count){
                    try {

                        if(!(ship_cabins[local_count/3][local_count%3].f_name.equals(null))){

                            file.write(String.format("\nCabin number %d, Passenger seat %d: %s %s", (local_count/3) + 1, (local_count%3) + 1, ship_cabins[local_count/3][local_count%3].f_name, ship_cabins[local_count/3][local_count%3].l_name));

                        }

                    } catch (Exception e) {}
                    local_count++;
                }
                file.close();
                System.out.println("Cabin Register Updated");
            } catch (Exception e) {
                System.out.println("An error has occurred.");
            }
        }
        public static void loadFromFile(){
            try{
                File file = new File("data_2.txt");
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()){
                    System.out.println(sc.nextLine());
                }
                sc.close();
            } catch (IOException e){
                System.out.println("Store the information before loading");
            }
        }
        public static void sortCustomers(int count) {
            List<String> order = new ArrayList<>();
            int local_count = 0;
            while(local_count < count){
                try {

                    order.add(String.format("%s %s", ship_cabins[local_count/3][local_count%3].f_name, ship_cabins[local_count/3][local_count%3].l_name));
                } catch (Exception e) {}
                local_count++;
            }

            String temp;
            for(int i = order.size(); i > 0; i--){
                for(int j = 0; j < i-1; j++){

                    if(((int) order.get(j+1).toLowerCase().charAt(0)) < ((int) order.get(j).toLowerCase().charAt(0))){
                        temp = order.get(j);
                        order.set(j, order.get(j+1));
                        order.set(j+1, temp);
                    }
                }
            }

            System.out.println("\n  Ordered passenger name\n");
            for(int i = 0; i < order.size(); i++){
                System.out.println(String.format("      %s", order.get(i)));
            }

        }
        public static void total(){
            System.out.println("\nCost per Customer, or all Customers(O/A) ? ");
            Scanner in = new Scanner(System.in);
            double total = 0;
            String n = in.next();
            if(n.toLowerCase().equals("o")){
                System.out.println("\n$10.5 per passenger\n");
            }
            else if(n.toLowerCase().equals("a")){

                for(int i = 0; i < 12; i++){
                    for(int j = 0; j < 3; j++){
                        try {
                            total += ship_cabins[i][j].expense;
                        } catch(Exception e){}
                    }
                }

                System.out.println("\nTotal expenses for "+(int) (total/ 10.5) +" passengers, $"+ total);
            }
        }

    }

    static class Customer {
        double expense;
        String f_name;
        String l_name;
        public Customer(String f_name, String l_name){
            this.f_name = f_name;
            this.l_name = l_name;
            this.expense = 10.5 ;
        }
    }
}