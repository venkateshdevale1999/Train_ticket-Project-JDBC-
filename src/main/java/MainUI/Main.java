package MainUI;



import Passenger_details.Passenger;
import Ticketpack.Ticket;
import Train_package.Train;
import Train_package.TrainDAO;



import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Main {
    public static void main(String[] args ) throws ParseException {
        Scanner sc=new Scanner(System.in);



        TrainDAO passenger = new TrainDAO();



        System.out.println("enter train number");
        int TRAIN_NO=sc.nextInt();






        if(passenger.findtrain(TRAIN_NO)!=null) {

            System.out.println("enter the date(dd-mm-yyyy)");
//            String d = sc.next();
//            Date d2 = new SimpleDateFormat("yyyy-mm-dd").parse(d);
//            LocalDate now = LocalDate.now();
//            Date d3 = new SimpleDateFormat("yyyy-mm-dd").parse(String.valueOf(now));

            String d=sc.next();
            Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(d);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            Date todayDate = dateFormatter.parse(dateFormatter.format(new Date() ));

            if(d1.after(todayDate)){
                Ticket t= new Ticket(d, passenger.findtrain(TRAIN_NO));


                System.out.println("Enter no. of passengers:");
                int n = sc.nextInt();
                int i = 0;
                while (i < n) {
                    System.out.println("Enter passenger details:");
                    System.out.print("Enter the name: ");
                    String name = sc.next();
                    System.out.print("Enter the Age");
                    Integer age = sc.nextInt();
                    System.out.println("Enter F for Female " + " Enter M for Male");
                    String gender = sc.next();
                    char c = gender.charAt(0);
                    t.addPassenger(name, age, c);
                    i++;
                }


                t.writeTicket();           }
            else {

                System.out.println("date not correct");


            }




        }

        else
            System.out.println("Train not found......");
















    }
}


