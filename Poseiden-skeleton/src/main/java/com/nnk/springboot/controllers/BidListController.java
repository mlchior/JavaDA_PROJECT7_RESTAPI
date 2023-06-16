package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BidListController {

    private final BidListService bidListService;

    @Autowired
    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
        Logger.info("BidListController initialized");
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        Logger.info("Retrieving all bids");
        List<BidList> bidList = bidListService.getAllBids();
        model.addAttribute("bidLists", bidList);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        Logger.info("Adding new bid");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.warn("Bid validation failed");
            return "bidList/add";
        }
        Logger.info("Bid validation successful, saving to database");
        bidListService.saveBid(bid);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Getting bid with id {}", id);
        BidList bidList = bidListService.getBidById(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.warn("Bid update failed for id {}", id);
            return "bidList/update";
        }
        Logger.info("Bid update successful for id {}, updating database", id);
        bidListService.updateBid(id, bidList);
        model.addAttribute("bidList", bidListService.getAllBids());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        Logger.info("Deleting bid with id {}", id);
        bidListService.deleteBid(id);
        model.addAttribute("bidList", bidListService.getAllBids());
        return "redirect:/bidList/list";
    }
}