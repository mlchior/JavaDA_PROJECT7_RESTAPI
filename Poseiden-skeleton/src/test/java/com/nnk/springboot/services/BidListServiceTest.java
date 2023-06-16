package com.nnk.springboot.services;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
// list
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidListServiceTest {

    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    private BidListRepository bidListRepository;

    @BeforeEach
    public void setUp() {
    MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetBidById() {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10.0);

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bid));

        BidList foundBid = bidListService.getBidById(1);

        assertNotNull(foundBid);
        assertEquals(bid.getAccount(), foundBid.getAccount());
        assertEquals(bid.getType(), foundBid.getType());
        assertEquals(bid.getBidQuantity(), foundBid.getBidQuantity());
    }
    @Test
    public void testGetAllBids() {
        List<BidList> bidList = new ArrayList<>();
        BidList bid1 = new BidList();
        bid1.setAccount("Account1");
        bid1.setType("Type1");
        bid1.setBidQuantity(100.0);

        BidList bid2 = new BidList();
        bid2.setAccount("Account2");
        bid2.setType("Type2");
        bid2.setBidQuantity(200.0);

        bidList.add(bid1);
        bidList.add(bid2);

        when(bidListRepository.findAll()).thenReturn(bidList);

        List<BidList> result = bidListService.getAllBids();
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveBid() {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10.0);

        when(bidListRepository.save(any(BidList.class))).thenReturn(bid);

        BidList savedBid = bidListService.saveBid(bid);

        assertNotNull(savedBid);
        assertEquals(bid.getAccount(), savedBid.getAccount());
    }

    @Test
    public void testUpdateBid() {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10.0);

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bid));
        when(bidListRepository.save(any(BidList.class))).thenReturn(bid);

        BidList updatedBid = bidListService.updateBid(1, bid);

        assertNotNull(updatedBid);
        assertEquals(bid.getAccount(), updatedBid.getAccount());
    }

    @Test
    public void testDeleteBid() {
        BidList bid = new BidList();
        bid.setAccount("Account Test");
        bid.setType("Type Test");
        bid.setBidQuantity(10.0);

        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bid));
        doNothing().when(bidListRepository).deleteById(anyInt());

        bidListService.deleteBid(1);

        verify(bidListRepository, times(1)).deleteById(anyInt());
    }


}
