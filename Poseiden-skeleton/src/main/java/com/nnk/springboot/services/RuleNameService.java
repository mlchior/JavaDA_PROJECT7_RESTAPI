package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RuleNameService {

    public RuleName getRuleNameById(Integer id);

    public RuleName updateRuleName(Integer id, RuleName ruleName);

    public RuleName saveRuleName(RuleName ruleName);

    public List<RuleName> getAllRuleNames();

    void deleteRuleNameById(Integer id);
}
