package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceTest {

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Mock
    private TradeRepository tradeRepository;

    private Trade trade;

    @BeforeEach
    public void setup() {
        trade = new Trade();
        trade.setAccount("Account");
        trade.setType("Type");
        // initialisez les autres champs de trade si nécessaire
    }

    @Test
    public void getAllTrades() {
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade));

        List<Trade> trades = tradeService.getAllTrades();

        assertEquals(1, trades.size());
        assertEquals(trade, trades.get(0));
    }

    @Test
    public void getTradeById() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade result = tradeService.getTradeById(1);

        assertEquals(trade, result);
    }

    @Test
    public void getTradeById_NotFound() {
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            tradeService.getTradeById(1);
        });
    }

    @Test
    public void updateTrade() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade result = tradeService.updateTrade(1, trade);

        assertEquals(trade, result);
    }

    @Test
    public void saveTrade() {
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade result = tradeService.saveTrade(trade);

        assertEquals(trade, result);
    }

    @Test
    public void deleteTrade() {
        doNothing().when(tradeRepository).deleteById(1);

        tradeService.deleteTrade(1);

        verify(tradeRepository, times(1)).deleteById(1);
    }
}
