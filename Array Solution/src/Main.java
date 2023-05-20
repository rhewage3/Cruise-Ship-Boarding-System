import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Main {
//    creating all the variables and arrays that needed.
        static String[] ship_cabin = new String[12];
        static String[] ship_cabin_sort = new String[ship_cabin.length];

        static String customer_Name;
        static int cabin_Number;

        static boolean looper;

        static Scanner input = new Scanner(System.in);

        public static void main(String[] arg){
                initialise();



               looper =true;
                while (looper){


                       System.out.println("\n----------------------------------------------------" +
                               "\n---------------------MENU---------------------------" +
                               "\n----------------------------------------------------");

                        System.out.println("V : View all cabins" +
                                "\nE : Display empty cabins" +
                                "\nA : Add a customer to a cabin" +
                                "\nD : Delete customer from cabin" +
                                "\nS : Store program data into a file" +
                                "\nF : Find Cabin by customer name"+
                                "\nL : Load program data from file" +
                                "\nO : View passengers ordered in alphabetical order" +
                                "\nQ : Exit the program");

                        System.out.println("----------------------------------------------------");
                        System.out.println("Select a option : ");
                        String option = input.next().toUpperCase();


                        if (option.equals("Q")){
                                looper = false;
                        }else continueit(option);


                }
        }


//      checking the selected option from the menu and calling the corresponding method.
        public static void continueit(String option){
                if (option.equals("V")){ viewAllCabins();}
                else if (option.equals("E")){ viewAllEmptyCabins();}
                else if (option.equals("A")){ addCustomer();}
                else if (option.equals("D")){ removeCustomer();}
                else if (option.equals("F")){ findbyname();}
                else if (option.equals("S")){ storeInFile();}
                else if (option.equals("L")){ loadFromFile();}
                else if (option.equals("O")){ sortCustomers();}
                else {
                        System.out.println("Please Enter a Valid Input");}
        }

//        initiate the ship_cabin array elements
        public static void initialise(){
                for (int x=0; x<12; x++){
                        ship_cabin[x] = "e";
                }
                System.out.println("initialised");
        }
//this method is getting called when the user enter "V" as the input, and it will show all the cabins.
        public static void viewAllCabins(){
            for (int i = 0; i < ship_cabin.length; i++){
                System.out.println("Cabin No."+ (i+1)+" : ");
                if (ship_cabin[i] == "e")
                    System.out.println("---Empty---");
                else
                    System.out.println(ship_cabin[i]);

            }
        }
    //this method is getting called when the user enter "E" as the input, and it will show all the empty cabins.
        public static void viewAllEmptyCabins(){
            for (int i = 0; i < ship_cabin.length; i ++){
                if (ship_cabin[i] == "e")
                    System.out.println("Cabin No. " + (i+1) + " is Empty.");

            }
        }

    //this method is getting called when the user enter "A" as the input, and it will add customers to cabins that they choose.
        public static void addCustomer(){
            System.out.println("Add a Customer");
            System.out.println("Enter a Cabin Number(1-12): ");
            cabin_Number = input.nextInt();


            looper = true;
            while (looper){
//                checking whether the entered cabin number in range.
                if (cabin_Number <= 12 && cabin_Number >= 1){
                    System.out.println("Enter you're Name: ");
                    ship_cabin[cabin_Number-1] = input.next();
                    System.out.println("Customer added to Cabin No."+ cabin_Number);
                    looper = false;


                }else{
                    System.out.println("Please enter a valid Cabin Number (1-12): ");
                    cabin_Number = input.nextInt();

                }
            }
            looper = true;
        }

    //this method is getting called when the user enter "D" as the input, and it will remove any customer that in the given
//            cabin number.
        public static void removeCustomer(){
            System.out.println("Remove Customer");
            System.out.println("Enter the Cabin No.: ");
            cabin_Number = input.nextInt();

            looper = true;
            while (looper){
                //                checking whether the entered cabin number in range.
                if (cabin_Number <= 12 && cabin_Number >= 1){
                    ship_cabin[cabin_Number - 1 ] = "e";
                    System.out.println("Customer removed from Cabin No." + cabin_Number);
                    looper = false;
                }else{
                    System.out.println("Please enter a valid Cabin Number (1-12): ");
                    cabin_Number = input.nextInt();
                }
            }
            looper = true;
        }



        public static void findbyname(){
            System.out.println("Please enter Customers name: ");
            String c_name = input.next();
            c_name = c_name.toLowerCase();

            for (int i = 0; i < ship_cabin.length; i++){
                if (ship_cabin[i].equals(c_name)){
                    System.out.println("Cabin No. of "+c_name+" is "+(i+1) );
                    break;
                }else
                    System.out.println("Sorry we can't find a Customer by that Name.");
                    break;
            }



            
        }

//this method is getting called when the user enter "S" as the input, and it record all the details about cabins.

        public static void storeInFile(){
            try{
                FileWriter writer = new FileWriter("cabinArray.txt");
                writer.write("Cabin No.\tCustomer Name\n");
//writing the details in to the file.
                for (int x = 0; x < ship_cabin.length; x++){
                    writer.write("  "+(x+1)+"\t\t");
                    if (ship_cabin[x].equals("e"))
                        customer_Name = "---Empty---";
                    else
                        customer_Name = ship_cabin[x];
                    writer.write (customer_Name+"\n");
                }
                writer.close();
                System.out.println("Cabin Register Updated");


            } catch (IOException e) {
                System.out.println("An Error occurred, Try Again");
                e.printStackTrace();
            }
        }
    //this method is getting called when the user enter "L" as the input, and it will take the information about cabins
//    from the file and will display in the console.

    public static void loadFromFile()  {
            try {
                File reader = new File("cabinArray.txt");
                Scanner regi = new Scanner(reader);

                while (regi.hasNextLine()){
                    String data = regi.nextLine();
                    System.out.println(data);
                }
                regi.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
//this method is getting called when the user enter "o" as the input, and it will sort all the names of customers
//    in alphabetical order.

        public static void sortCustomers(){
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < ship_cabin.length; i++){
//                copying all the customers name in to a new array.
                ship_cabin_sort[i] = ship_cabin[i];
            }
//sorting the names
            for (int i = 0; i<ship_cabin_sort.length; i++){
                for (int j = 1; j <ship_cabin_sort.length; j++){
                    if (ship_cabin_sort[j-1].compareToIgnoreCase(ship_cabin_sort[j]) > 0){
                        String x = ship_cabin_sort[j-1];
                        ship_cabin_sort[j-1] = ship_cabin_sort[j];
                        ship_cabin_sort[j] = x;
                    }
                }
            }
//            printing the sorted names in alphabetical order.
            for (int i=0; i<ship_cabin_sort.length; i++){
                if (!ship_cabin_sort[i].equals("e"))
                    System.out.println(ship_cabin_sort[i]);
            }
        }




}
