package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CurveController {
    private final CurvePointService curvePointService;

    @Autowired
    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> curvePoint = curvePointService.getAllCurvePoints();
        model.addAttribute("curvePoint", curvePoint);
        Logger.info("All curve points retrieved");
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        Logger.info("Add curve point form displayed");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in curve point validation");
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        Logger.info("Curve point saved");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        Logger.info("Update form for curve point with id {} displayed", id);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in updating curve point with id {}", id);
            return "curvePoint/update";
        }
        curvePointService.saveCurvePoint(curvePoint);
        Logger.info("Curve point with id {} updated", id);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        curvePointService.deleteCurvePoint(id);
        Logger.info("Curve point with id {} deleted", id);
        return "redirect:/curvePoint/list";
    }
}
