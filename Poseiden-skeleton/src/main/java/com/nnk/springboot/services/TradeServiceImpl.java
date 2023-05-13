package com.nnk.springboot.services;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    private TradeRepository tradeRepository;


    @Autowired
    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
    @Override
    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade id: " + id));
    }
    @Override
    public Trade updateTrade(Integer id, Trade trade) {
        Trade existingTrade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade id: " + id));
        if(existingTrade != null){
            existingTrade.setAccount(trade.getAccount());
            existingTrade.setType(trade.getType());
            existingTrade.setBuyQuantity(trade.getBuyQuantity());
            return tradeRepository.save(existingTrade);
        }
        return null;
    }
    @Override
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }
    @Override
    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }




}
