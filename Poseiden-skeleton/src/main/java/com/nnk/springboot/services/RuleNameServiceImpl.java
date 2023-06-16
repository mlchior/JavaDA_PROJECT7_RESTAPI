package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;

@Service
public class RuleNameServiceImpl implements RuleNameService {
    private RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    @Override
    public List<RuleName> getAllRuleNames() {
        Logger.info("Retrieving all RuleNames");
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName getRuleNameById(Integer id) {
        Logger.info("Retrieving RuleName with id {}", id);
        return ruleNameRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid ruleName id: {}", id);
            return new IllegalArgumentException("Invalid ruleName id: " + id);
        });
    }

    @Override
    public RuleName updateRuleName(Integer id, RuleName ruleName) {
        Logger.info("Updating RuleName with id {}", id);
        RuleName existingRuleName = ruleNameRepository.findById(id).orElseThrow(() -> {
            Logger.error("Invalid ruleName id: {}", id);
            return new IllegalArgumentException("Invalid ruleName id: " + id);
        });
        if (existingRuleName != null) {
            existingRuleName.setName(ruleName.getName());
            existingRuleName.setDescription(ruleName.getDescription());
            existingRuleName.setJson(ruleName.getJson());
            existingRuleName.setTemplate(ruleName.getTemplate());
            existingRuleName.setSqlStr(ruleName.getSqlStr());
            existingRuleName.setSqlPart(ruleName.getSqlPart());
            Logger.info("RuleName with id {} updated successfully", id);
            return ruleNameRepository.save(existingRuleName);
        }
        Logger.error("Failed to update RuleName with id {}", id);
        return null;
    }

    @Override
    public RuleName saveRuleName(RuleName ruleName) {
        Logger.info("Saving new RuleName");
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void deleteRuleNameById(Integer id) {
        Logger.info("Deleting RuleName with id {}", id);
        ruleNameRepository.deleteById(id);
    }
}
