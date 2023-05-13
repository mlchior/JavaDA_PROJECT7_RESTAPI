package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    public RuleName getRuleNameById(Integer id);

    public RuleName updateRuleName(Integer id, RuleName ruleName);

    public RuleName saveRuleName(RuleName ruleName);

    public List<RuleName> getAllRuleNames();

    void deleteRuleNameById(Integer id);
}
