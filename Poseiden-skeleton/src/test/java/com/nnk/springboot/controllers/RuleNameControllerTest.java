package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ruleNameController).build();
    }

    @Test
    public void testHome() throws Exception {
        when(ruleNameService.getAllRuleNames()).thenReturn(Arrays.asList(new RuleName()));

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNameList"));
    }

    @Test
    public void testAddRuleForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    public void testValidate() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .flashAttr("ruleName", new RuleName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        when(ruleNameService.getRuleNameById(1)).thenReturn(new RuleName());

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void testUpdateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .flashAttr("ruleName", new RuleName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    public void testDeleteRuleName() throws Exception {
        when(ruleNameService.getAllRuleNames()).thenReturn(Arrays.asList(new RuleName()));

        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
}
