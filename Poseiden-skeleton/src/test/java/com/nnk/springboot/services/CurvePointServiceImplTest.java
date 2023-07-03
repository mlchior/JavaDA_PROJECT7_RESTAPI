package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurvePointServiceImplTest {

    @InjectMocks
    CurvePointServiceImpl curvePointService;

    @Mock
    CurvePointRepository curvePointRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCurvePoints() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curvePoint));

        List<CurvePoint> curvePoints = curvePointService.getAllCurvePoints();

        assertEquals(1, curvePoints.size());
        assertEquals(1, (int) curvePoints.get(0).getCurveId());
        assertEquals(10.0, (double) curvePoints.get(0).getTerm());
        assertEquals(20.0, (double) curvePoints.get(0).getValue());
    }
}