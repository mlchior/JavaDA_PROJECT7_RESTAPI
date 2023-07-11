package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    private RuleName ruleName;

    @BeforeEach
    public void setup() {
        ruleName = new RuleName();
        ruleName.setName("Test Rule");
        ruleName.setDescription("Test Description");
        ruleName.setJson("Test JSON");
        ruleName.setTemplate("Test Template");
        ruleName.setSqlStr("Test SQL");
        ruleName.setSqlPart("Test SQL Part");
    }

    @Test
    public void getAllRuleNames() {
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(ruleName));

        List<RuleName> ruleNames = ruleNameService.getAllRuleNames();

        assertEquals(1, ruleNames.size());
        assertEquals(ruleName, ruleNames.get(0));
    }

    @Test
    public void getRuleNameById() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName result = ruleNameService.getRuleNameById(1);

        assertEquals(ruleName, result);
    }

    @Test
    public void getRuleNameById_NotFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            ruleNameService.getRuleNameById(1);
        });
    }

    @Test
    public void updateRuleName() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName result = ruleNameService.updateRuleName(1, ruleName);

        assertEquals(ruleName, result);
    }

    @Test
    public void saveRuleName() {
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName result = ruleNameService.saveRuleName(ruleName);

        assertEquals(ruleName, result);
    }

    @Test
    public void deleteRuleNameById() {
        doNothing().when(ruleNameRepository).deleteById(1);

        ruleNameService.deleteRuleNameById(1);

        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}
