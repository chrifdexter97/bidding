package org.bid.entities;

public class Lot {
    private double reservePrice;
    private double[] bids;
    private int winningBidder;
    private double winPrice;

    public Lot(){
        // initialize winner values this way to make a difference between whether we have a winner or not.
        this.winningBidder = -1;
        this.winPrice = Double.NEGATIVE_INFINITY;
    }
    public double getReservePrice() {return reservePrice;}
    public void setReservePrice(double reservePrice) {this.reservePrice = reservePrice;}
    public double[] getBids() {return bids;}
    public void setBids(double[] bids) {this.bids = bids;}
    public void setBidAtPosition(int pos, double bid){this.bids[pos] = bid;}
    public int getWinningBidder() {return winningBidder;}
    public void setWinningBidder(int winningBidder) {this.winningBidder = winningBidder;}
    public double getWinPrice() {return winPrice;}
    public void setWinPrice(double winPrice) {this.winPrice = winPrice;}
}
