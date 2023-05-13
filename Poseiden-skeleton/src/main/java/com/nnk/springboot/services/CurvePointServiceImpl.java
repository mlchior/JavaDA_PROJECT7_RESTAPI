package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements CurvePointService{

    private CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }
    @Override
    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }
    @Override
    public CurvePoint getCurvePointById(Integer id) {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id: " + id));
    }
    @Override
    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
        CurvePoint existingCurvePoint = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id: " + id));
        if(existingCurvePoint != null){
            existingCurvePoint.setCurveId(curvePoint.getCurveId());
            existingCurvePoint.setTerm(curvePoint.getTerm());
            existingCurvePoint.setValue(curvePoint.getValue());
            return curvePointRepository.save(existingCurvePoint);
        }
        return null;
    }
    @Override
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }
    @Override
    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
