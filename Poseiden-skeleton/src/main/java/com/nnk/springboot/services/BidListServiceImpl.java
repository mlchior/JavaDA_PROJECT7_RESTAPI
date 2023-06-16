package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {
    private BidListRepository bidListRepository;

    @Autowired
    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @Override
    public List<BidList> getAllBids() {
        Logger.info("Retrieving all Bids");
        return bidListRepository.findAll();
    }

    public BidList getBidById(Integer id) {
        Logger.info("Retrieving Bid with id {}", id);
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid id: " + id));
    }

    public BidList updateBid(Integer id, BidList bid) {
        Logger.info("Updating Bid with id {}", id);
        BidList existingBid = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid id: " + id));
        if(existingBid != null){
            existingBid.setAccount(bid.getAccount());
            existingBid.setType(bid.getType());
            existingBid.setBidQuantity(bid.getBidQuantity());

            Logger.info("Bid with id {} updated successfully", id);
            return bidListRepository.save(existingBid);
        }
        Logger.error("Failed to update Bid with id {}", id);
        return null;
    }

    @Override
    public BidList saveBid(BidList bid) {
        Logger.info("Saving new Bid");
        return bidListRepository.save(bid);
    }

    public void deleteBid(Integer id) {
        Logger.info("Deleting Bid with id {}", id);
        bidListRepository.deleteById(id);
    }
}
