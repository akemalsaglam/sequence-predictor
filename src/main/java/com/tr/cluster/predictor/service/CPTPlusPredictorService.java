package com.tr.cluster.predictor.service;

import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.CPT.CPTPlus.CPTPlusPredictor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class CPTPlusPredictorService extends AbstractPredictorService<CPTPlusPredictor> implements BasePredictorService {

    @PostConstruct
    public void init() {
        String optionalParameters = "CCF:true CBS:true CCFmin:1 CCFmax:6 CCFsup:3 splitMethod:0 splitLength:5 minPredictionRatio:1.0 noiseRatio:1.0";
        train("CPTPlusPredictor", optionalParameters, CPTPlusPredictor.class);
        System.out.println("Training finished for CPTPlusPredictor.");
    }

}
