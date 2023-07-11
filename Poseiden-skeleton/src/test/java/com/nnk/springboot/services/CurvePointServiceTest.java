package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceTest {

    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    private CurvePoint curvePoint;

    @BeforeEach
    public void setup() {
        curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);
    }

    @Test
    public void getAllCurvePoints() {
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curvePoint));

        List<CurvePoint> curvePoints = curvePointService.getAllCurvePoints();

        assertEquals(1, curvePoints.size());
        assertEquals(1, (int) curvePoints.get(0).getCurveId());
        assertEquals(10.0, (double) curvePoints.get(0).getTerm());
        assertEquals(20.0, (double) curvePoints.get(0).getValue());
    }

    @Test
    public void getCurvePointById() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        CurvePoint result = curvePointService.getCurvePointById(1);

        assertEquals(curvePoint, result);
    }

    @Test
    public void getCurvePointById_NotFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            curvePointService.getCurvePointById(1);
        });
    }

    @Test
    public void updateCurvePoint() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint result = curvePointService.updateCurvePoint(1, curvePoint);

        assertEquals(curvePoint, result);
    }

    @Test
    public void saveCurvePoint() {
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint result = curvePointService.saveCurvePoint(curvePoint);

        assertEquals(curvePoint, result);
    }

    @Test
    public void deleteCurvePoint() {
        doNothing().when(curvePointRepository).deleteById(1);

        curvePointService.deleteCurvePoint(1);

        verify(curvePointRepository, times(1)).deleteById(1);
    }
}
