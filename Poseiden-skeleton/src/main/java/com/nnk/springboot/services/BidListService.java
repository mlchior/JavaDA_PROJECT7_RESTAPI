package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
//tinyLog


import java.util.List;


public interface BidListService {


    public List<BidList> getAllBids();

    public void deleteBid(Integer id);

    public BidList getBidById(Integer id);

    public BidList updateBid(Integer id, BidList bid);

    public BidList saveBid(BidList bid);


}