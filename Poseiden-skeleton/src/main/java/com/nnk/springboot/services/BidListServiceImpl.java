package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService{
    private BidListRepository bidListRepository;

    @Autowired
    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }


    @Override
    public List<BidList> getAllBids() {
        return bidListRepository.findAll();
    }
    public BidList getBidById(Integer id) {
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid id: " + id));
    }

    public BidList updateBid(Integer id, BidList bid) {
        BidList existingBid = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid id: " + id));
        if(existingBid != null){
            existingBid.setAccount(bid.getAccount());
            existingBid.setType(bid.getType());
            existingBid.setBidQuantity(bid.getBidQuantity());

            return bidListRepository.save(existingBid);
        }
        return null;
    }

    @Override
    public BidList saveBid(BidList bid) {
        return bidListRepository.save(bid);
    }

    public void deleteBid(Integer id) {
        bidListRepository.deleteById(id);
    }
}
