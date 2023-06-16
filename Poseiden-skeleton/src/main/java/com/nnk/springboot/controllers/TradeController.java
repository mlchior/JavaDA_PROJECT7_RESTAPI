package com.nnk.springboot.controllers;

import com.nnk.springboot.services.TradeService;
import com.nnk.springboot.domain.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TradeController {
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> trade = tradeService.getAllTrades();
        model.addAttribute("trade", trade);
        Logger.info("All Trades retrieved");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        Logger.info("Add Trade form displayed");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in Trade validation");
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        model.addAttribute("trade", trade);
        Logger.info("Trade saved");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        Logger.info("Update form for Trade with id {} displayed", id);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in updating Trade with id {}", id);
            return "trade/update";
        }
        tradeService.saveTrade(trade);
        model.addAttribute("trade", trade);
        Logger.info("Trade with id {} updated", id);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id);
        tradeService.deleteTrade(trade.getTradeId());
        model.addAttribute("trade", trade);
        Logger.info("Trade with id {} deleted", id);
        return "redirect:/trade/list";
    }
}
