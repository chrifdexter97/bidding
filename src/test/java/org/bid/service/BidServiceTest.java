package org.bid.service;

import org.bid.entities.Lot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BidServiceTest {
    private BidService bidService;

    private static final int BIDDERSNUMBER = 5;

    private static final double[] INITBIDSARRAY = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};
    private static final double[] BIDSARRAY = new double[] {0.0, 1.0, 2.0, 3.0, 4.0};

    BidServiceTest(){
        bidService = BidService.getBidService();
    }

    @BeforeEach
    public void initTest() {
        bidService.setLot(new Lot());
        bidService.initBidders(BIDDERSNUMBER);
    }

    @Test
    public void setBidAtPosition() throws Exception {
        bidService.setBidAtPosition(0,0.0);
        bidService.setBidAtPosition(1,1.0);
        bidService.setBidAtPosition(2,2.0);
        bidService.setBidAtPosition(3,3.0);
        bidService.setBidAtPosition(4,4.0);

        assertThat(Arrays.compare(bidService.getLot().getBids(),BIDSARRAY)==0).isTrue();
    }

    @Test
    public void initBidders() throws Exception {
        bidService.initBidders(5);
        assertThat(Arrays.compare(bidService.getLot().getBids(),INITBIDSARRAY)==0).isTrue();
    }

}
