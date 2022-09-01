package org.bid.service;

import org.bid.entities.Lot;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/*
I made use of the sigleton design pattern because at most one instance of BidService is reauired throughout the application run
 */
public class BidService {

    private static BidService bidService;

    // normally we should have a dao sigleton and subsequent logic for querying the lots from a datasource... but we're gonna skip it for the sake of simplicity.
    private Lot lot;
    private BidService(){
        lot = new Lot();
    }
    public static synchronized BidService getBidService(){
        if (bidService == null) {
            bidService = new BidService();
        }
        return bidService;
    }
    public Lot getLot(){return lot;}

    public void initBidders(int biddersNumber){
        double[] bids = new double[biddersNumber];
        Arrays.fill(bids,0.0);
        bidService.getLot().setBids(bids);
    }
    public void setLot(Lot lot) {this.lot = lot;}
    public void setBidAtPosition(int pos, double bid){

        this.lot.setBidAtPosition(pos,bid);
    }
    public void calculateWinner() {
        calculateWinningBidder();
    }
    private void calculateWinningBidder(){
        IntStream.range(0, this.lot.getBids().length)
                // remove bids below the reserve price
                .filter(pos-> this.lot.getBids()[pos]>= lot.getReservePrice())
                // determine the max bid
                .reduce((a,b)->this.lot.getBids()[a]<this.lot.getBids()[b]? b: a)
                // nothing in the description shows what to do if two bidds are equal, I'm gonna take the first corresponding bid
                .stream().findFirst()
                .ifPresent(ix->{
                    this.lot.setWinningBidder(ix);
                    calculateWinningPrice();
                });
    }
    private void calculateWinningPrice(){
        Arrays.stream(this.lot.getBids())
                .filter(el-> el <this.lot.getBids()[this.getLot().getWinningBidder()])
                .max().ifPresent(value->this.lot.setWinPrice(value));
    }
}
