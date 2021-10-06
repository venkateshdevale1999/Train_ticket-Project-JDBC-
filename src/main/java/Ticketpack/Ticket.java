package Ticketpack;

import Passenger_details.Passenger;
import Train_package.Train;
import Train_package.TrainDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Ticket {
    private int count;
    private String pnr=null;
    private String traveldate;
    private Map<Passenger,Double> passengers= new TreeMap<>();
    private Train train;

    public Ticket(String traveldate, Train train) {
        this.traveldate = traveldate;
        this.train = train;
        generatePNR();
    }

    private String generatePNR(){


        count=TrainDAO.getcount();
        char s= train.getSource().charAt(0);
        char d= train.getDestination().charAt(0);
        String[] date1 = traveldate.toString().split("-");
        pnr = s+""+d+"_"+date1[2]+date1[1]+date1[0]+"_"+count;
        return pnr;
    }

    private double calcPassengerFare(Passenger p){
        double price= train.getTkt_price();
        if(p.getAge()<=12)
            price *=0.5;
        else
            if(p.getAge()>=60)
                price *=0.6;
            else
                if(p.getGender()=='F')
                    price *=0.75;
        passengers.put(p,price);
        return price;




    }

    public void addPassenger(String name,int age,char gender){
        Passenger p = new Passenger(name,age,gender);
        calcPassengerFare(p);

    }

    private double calcTotalTicketPrice(){
        double totamt=0;
        for(double p:passengers.values())
            totamt+=p;

        return totamt;
    }


    private StringBuilder generateTicket(){
        StringBuilder s=new StringBuilder();
        s.append("PNR: "+getPnr()+'\n');
        s.append(System.getProperty("line.separator"));
        s.append("TRAIN NO.: "+train.getTrainNo()+"\n");
        s.append(System.lineSeparator());
        s.append("TRAIN NAME: "+train.getTrainName()+"\n");
        s.append(System.lineSeparator());
        s.append("FROM: "+train.getSource()+"\n");
        s.append(System.lineSeparator());
        s.append("TO: "+train.getDestination()+"\n");
        s.append(System.lineSeparator());
        s.append("TRAVEL DATE: "+traveldate+"\n");
        s.append(System.lineSeparator());
        s.append(" \nPASSENGERS \n");
        s.append(System.lineSeparator());
        s.append("Name      age     gender      fare \n");
        s.append(System.lineSeparator());
        for(Passenger p:passengers.keySet()) {
            s.append(p.getName()+"      "+p.getAge()+"      "+p.getGender()+"       "+passengers.get(p));
            s.append(System.lineSeparator());
        }

        s.append("Total Price: "+calcTotalTicketPrice());


        return s;
    }

    public void writeTicket(){




        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\user105\\Desktop\\"+getPnr()+".txt");
            myWriter.write(String.valueOf(generateTicket()));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    public String getPnr() {
        return pnr;
    }

    public String getTraveldate() {
        return traveldate;
    }

    public Map<Passenger, Double> getPassengers() {
        return passengers;
    }

    public Train getTrain() {
        return train;
    }
}
