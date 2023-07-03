package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.tinylog.Logger;

@Transactional
@Service
public class BidListServiceImpl implements BidListService{
    private final BidListRepository bidListRepository;

    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findById(Integer id) {
        Logger.info("Getting bid with id {}", id);
        Optional<BidList> bidList = bidListRepository.findById(id);
        if (bidList.isPresent()) {
            return bidList.get();
        } else {
            Logger.warn("Bid with id {} not found", id);
            return null;
        }
    }

    @Override
    public BidList save(BidList bidList) {
        Logger.info("Saving bid");
        return bidListRepository.save(bidList);


    }

    @Override
    public boolean deleteById(Integer id) {
        Logger.info("Deleting bid with id {}", id);
        Optional<BidList> bidList = bidListRepository.findById(id);
        if (bidList.isPresent()) {
            bidListRepository.deleteById(id);
            return true;
        } else {
            Logger.warn("Bid with id {} not found", id);
            return false;
        }
    }
}
