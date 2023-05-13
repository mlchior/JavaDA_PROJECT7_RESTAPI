package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements RuleNameService{
    private RuleNameRepository ruleNameRepository;

    @Autowired
    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    @Override
    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }



    @Override
    public RuleName getRuleNameById(Integer id) {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        return ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName id: " + id));
    }
    @Override
    public RuleName updateRuleName(Integer id, RuleName ruleName) {
        RuleName existingRuleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName id: " + id));
        if(existingRuleName != null){
            existingRuleName.setName(ruleName.getName());
            existingRuleName.setDescription(ruleName.getDescription());
            existingRuleName.setJson(ruleName.getJson());
            existingRuleName.setTemplate(ruleName.getTemplate());
            existingRuleName.setSqlStr(ruleName.getSqlStr());
            existingRuleName.setSqlPart(ruleName.getSqlPart());
            return ruleNameRepository.save(existingRuleName);
        }
        return null;
    }
    @Override
    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }
    @Override
    public void deleteRuleNameById(Integer id) {
        ruleNameRepository.deleteById(id);
    }


}
