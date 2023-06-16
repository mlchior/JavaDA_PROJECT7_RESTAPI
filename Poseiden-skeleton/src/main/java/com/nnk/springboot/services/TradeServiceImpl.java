package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

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
        Logger.info("Retrieving all Trades");
        return tradeRepository.findAll();
    }

    @Override
    public Trade getTradeById(Integer id) {
        Logger.info("Retrieving Trade with id {}", id);
        return tradeRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid trade id: {}", id);
            return new IllegalArgumentException("Invalid trade id: " + id);
        });
    }

    @Override
    public Trade updateTrade(Integer id, Trade trade) {
        Logger.info("Updating Trade with id {}", id);
        Trade existingTrade = tradeRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid trade id: {}", id);
            return new IllegalArgumentException("Invalid trade id: " + id);
        });
        if(existingTrade != null) {
            existingTrade.setAccount(trade.getAccount());
            existingTrade.setType(trade.getType());
            existingTrade.setBuyQuantity(trade.getBuyQuantity());
            existingTrade.setSellQuantity(trade.getSellQuantity());
            existingTrade.setBuyPrice(trade.getBuyPrice());
            existingTrade.setSellPrice(trade.getSellPrice());
            existingTrade.setTradeDate(trade.getTradeDate());
            existingTrade.setSecurity(trade.getSecurity());
            existingTrade.setStatus(trade.getStatus());
            existingTrade.setTrader(trade.getTrader());
            existingTrade.setBenchmark(trade.getBenchmark());
            existingTrade.setBook(trade.getBook());
            existingTrade.setCreationName(trade.getCreationName());
            existingTrade.setCreationDate(trade.getCreationDate());
            existingTrade.setRevisionName(trade.getRevisionName());
            existingTrade.setRevisionDate(trade.getRevisionDate());
            existingTrade.setDealName(trade.getDealName());
            existingTrade.setDealType(trade.getDealType());
            existingTrade.setSourceListId(trade.getSourceListId());
            existingTrade.setSide(trade.getSide());
            Logger.info("Trade with id {} updated successfully", id);
            return tradeRepository.save(existingTrade);
        }
        Logger.error("Failed to update Trade with id {}", id);
        return null;
    }

    @Override
    public Trade saveTrade(Trade trade) {
        Logger.info("Saving new Trade");
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteTrade(Integer id) {
        Logger.info("Deleting Trade with id {}", id);
        tradeRepository.deleteById(id);
    }
}
