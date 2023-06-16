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
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCurvePoints() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curvePoint));

        List<CurvePoint> curvePoints = curvePointService.getAllCurvePoints();

        assertEquals(1, curvePoints.size());
        assertEquals(1, curvePoints.get(0).getCurveId());
        assertEquals(10.0, curvePoints.get(0).getTerm());
        assertEquals(20.0, curvePoints.get(0).getValue());
    }

    @Test
    void getCurvePointById() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));

        CurvePoint foundCurvePoint = curvePointService.getCurvePointById(1);

        assertNotNull(foundCurvePoint);
        assertEquals(1, foundCurvePoint.getCurveId());
        assertEquals(10.0, foundCurvePoint.getTerm());
        assertEquals(20.0, foundCurvePoint.getValue());
    }

    @Test
    void updateCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint updatedCurvePoint = curvePointService.updateCurvePoint(1, curvePoint);

        assertNotNull(updatedCurvePoint);
        assertEquals(1, updatedCurvePoint.getCurveId());
        assertEquals(10.0, updatedCurvePoint.getTerm());
        assertEquals(20.0, updatedCurvePoint.getValue());
    }

    @Test
    void saveCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(curvePoint);

        assertNotNull(savedCurvePoint);
        assertEquals(1, savedCurvePoint.getCurveId());
        assertEquals(10.0, savedCurvePoint.getTerm());
        assertEquals(20.0, savedCurvePoint.getValue());
    }
    @Test
    void deleteCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        doNothing().when(curvePointRepository).deleteById(anyInt());

        curvePointService.deleteCurvePoint(1);

        verify(curvePointRepository, times(1)).deleteById(anyInt());
    }
}