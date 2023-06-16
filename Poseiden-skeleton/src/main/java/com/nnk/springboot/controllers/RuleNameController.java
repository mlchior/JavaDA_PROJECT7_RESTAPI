package com.nnk.springboot.controllers;

import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.domain.RuleName;
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
public class RuleNameController {
    private final RuleNameService ruleNameService;

    @Autowired
    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        List<RuleName> ruleNames = ruleNameService.getAllRuleNames();
        model.addAttribute("ruleNameList", ruleNames);
        Logger.info("All RuleNames retrieved");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        Logger.info("Add RuleName form displayed");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in RuleName validation");
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        Logger.info("RuleName saved");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        Logger.info("Update form for RuleName with id {} displayed", id);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        if(result.hasErrors()){
            Logger.error("Error in updating RuleName with id {}", id);
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(id, ruleName);
        Logger.info("RuleName with id {} updated", id);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleNameById(id);
        Logger.info("RuleName with id {} deleted", id);
        model.addAttribute("ruleName", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }
}
