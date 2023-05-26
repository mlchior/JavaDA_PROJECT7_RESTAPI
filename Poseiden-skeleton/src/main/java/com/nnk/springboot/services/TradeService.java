package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TradeService {
    public void deleteTrade(Integer id);

    public Trade getTradeById(Integer id);

    public Trade updateTrade(Integer id, Trade trade);

    public Trade saveTrade(Trade trade);

    public List<Trade> getAllTrades();
}
