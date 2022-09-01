package org.bid.presentation;

import org.bid.service.BidService;

import java.util.Arrays;
import java.util.Scanner;

/*
I made use of the sigleton design pattern because at most one instance of CommandLineInteractor is reauired throughout the application run
 */
public class CommandLineInteractor {
    private static CommandLineInteractor commandLineInteractor;
    private BidService bidService;
    private String YES = "yes";
    private CommandLineInteractor(){
        bidService = BidService.getBidService();
    }
    public static synchronized CommandLineInteractor getCommandLineInteractor(){
        if (commandLineInteractor== null) {
            commandLineInteractor = new CommandLineInteractor();
        }
        return commandLineInteractor;
    }
    public void start(Scanner sc){
        initReservePrice(sc);
        initBidders(sc);
        startBidLoop(sc);
    }
    public void initBidders(Scanner sc){
        System.out.println("please type in the bidders num:");

        try
        {
            int biddersNumber = sc.nextInt();
            bidService.initBidders(biddersNumber);
        }
        catch (java.util.InputMismatchException e) {sc.nextLine();initBidders(sc);}
    };
    public void initReservePrice(Scanner sc){
        System.out.println("please type in the reserve price:");
        try
        {
            double reservePrice = sc.nextDouble();
            bidService.getLot().setReservePrice(reservePrice);
        }
        catch (java.util.InputMismatchException e) {sc.nextLine();initReservePrice(sc);}
    };
    public void startBidLoop(Scanner sc){
        int bidRound = 0;
        while(true){
            System.out.println("start bidding round "+(bidRound+1)+" ?(yes/no)");
            // get whether the user wants to start another bidding round
            String decision = sc.next();

            if (decision.equals(YES)){
                getBids(sc);
                calculateAndDisplayResult();
            }else{
                break;
            }
        }
    }
    private void getBids(Scanner sc){
       for(int i=0; i<bidService.getLot().getBids().length; i++) {
           System.out.println("bid value for bidder "+(i+1)+" (if the bidder skipped the bid please type in 0) :");
           try
           {
               double bid = sc.nextDouble();
               bidService.setBidAtPosition(i,bid);
           }
           catch (java.util.InputMismatchException e) {sc.nextLine();}
       }
    }
    private void calculateAndDisplayResult(){
        bidService.calculateWinner();
        if(bidService.getLot().getWinningBidder()>0){
            System.out.println("winning bidder : "+(bidService.getLot().getWinningBidder()+1)+" winning bidvalue: "+bidService.getLot().getWinPrice());
        }else{
            System.out.println("no winner !!!");
        }
    }

}
