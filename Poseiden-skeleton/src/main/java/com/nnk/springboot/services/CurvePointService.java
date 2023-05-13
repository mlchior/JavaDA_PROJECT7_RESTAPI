package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {


    public void deleteCurvePoint(Integer id);

    public CurvePoint getCurvePointById(Integer id);

    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint);

    public CurvePoint saveCurvePoint(CurvePoint curvePoint);

    public List<CurvePoint> getAllCurvePoints();


}
