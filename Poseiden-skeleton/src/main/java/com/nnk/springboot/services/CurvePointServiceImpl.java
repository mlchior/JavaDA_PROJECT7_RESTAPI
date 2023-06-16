package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @Override
    public List<CurvePoint> getAllCurvePoints() {
        Logger.info("Retrieving all CurvePoints");
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        Logger.info("Retrieving CurvePoint with id {}", id);
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        return curvePoint.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id: " + id));
    }

    @Override
    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
        Logger.info("Updating CurvePoint with id {}", id);
        CurvePoint existingCurvePoint = curvePointRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint id: " + id));
        if (existingCurvePoint != null) {
            existingCurvePoint.setCurveId(curvePoint.getCurveId());
            existingCurvePoint.setTerm(curvePoint.getTerm());
            existingCurvePoint.setValue(curvePoint.getValue());
            Logger.info("CurvePoint with id {} updated successfully", id);
            return curvePointRepository.save(existingCurvePoint);
        }
        Logger.error("Failed to update CurvePoint with id {}", id);
        return null;
    }

    @Override
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        Logger.info("Saving new CurvePoint");
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteCurvePoint(Integer id) {
        Logger.info("Deleting CurvePoint with id {}", id);
        curvePointRepository.deleteById(id);
    }
}
