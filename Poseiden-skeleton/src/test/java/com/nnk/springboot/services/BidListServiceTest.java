package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    private BidListRepository bidListRepository;

    private BidList bidList;

    @BeforeEach
    public void setup() {
        bidList = new BidList("Test Account", "Test Type", 10d);
    }

    @Test
    public void getAllBids() {
        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bidList));

        List<BidList> bidLists = bidListService.getAllBids();

        assertEquals(1, bidLists.size());
        assertEquals("Test Account", bidLists.get(0).getAccount());
        assertEquals("Test Type", bidLists.get(0).getType());
        assertEquals(10d, bidLists.get(0).getBidQuantity(), 0.001);
    }

    @Test
    public void getBidById() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        BidList result = bidListService.getBidById(1);

        assertEquals(bidList, result);
    }

    @Test
    public void getBidById_NotFound() {
        when(bidListRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            bidListService.getBidById(1);
        });
    }

    @Test
    public void updateBid() {
        BidList updatedBid = new BidList("Updated Account", "Updated Type", 20d);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
        when(bidListRepository.save(any(BidList.class))).thenReturn(updatedBid);

        BidList result = bidListService.updateBid(1, updatedBid);

        assertEquals(updatedBid, result);
        assertEquals("Updated Account", result.getAccount());
        assertEquals("Updated Type", result.getType());
        assertEquals(20d, result.getBidQuantity(), 0.001);
    }

    @Test
    public void saveBid() {
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);

        BidList result = bidListService.saveBid(bidList);

        assertEquals(bidList, result);
    }

    @Test
    public void deleteBid() {
        doNothing().when(bidListRepository).deleteById(1);

        bidListService.deleteBid(1);

        verify(bidListRepository, times(1)).deleteById(1);
    }
}
