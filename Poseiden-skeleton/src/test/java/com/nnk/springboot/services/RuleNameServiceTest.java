package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRuleNames() {
        List<RuleName> ruleNameList = new ArrayList<>();
        RuleName ruleName1 = new RuleName("Name1", "Description1", "Json1", "Template1", "SqlStr1", "SqlPart1");
        RuleName ruleName2 = new RuleName("Name2", "Description2", "Json2", "Template2", "SqlStr2", "SqlPart2");

        ruleNameList.add(ruleName1);
        ruleNameList.add(ruleName2);

        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);

        List<RuleName> result = ruleNameService.getAllRuleNames();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetRuleNameById() {
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));

        RuleName foundRuleName = ruleNameService.getRuleNameById(1);

        assertNotNull(foundRuleName);
        assertEquals(ruleName.getName(), foundRuleName.getName());
    }

    @Test
    public void testSaveRuleName() {
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName savedRuleName = ruleNameService.saveRuleName(ruleName);

        assertNotNull(savedRuleName);
        assertEquals(ruleName.getName(), savedRuleName.getName());
    }

    @Test
    public void testUpdateRuleName() {
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName updatedRuleName = ruleNameService.updateRuleName(1, ruleName);

        assertNotNull(updatedRuleName);
        assertEquals(ruleName.getName(), updatedRuleName.getName());
    }

    @Test
    public void testDeleteRuleName() {
        RuleName ruleName = new RuleName("Name", "Description", "Json", "Template", "SqlStr", "SqlPart");
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        doNothing().when(ruleNameRepository).deleteById(anyInt());

        ruleNameService.deleteRuleNameById(1);

        verify(ruleNameRepository, times(1)).deleteById(anyInt());
    }
}
