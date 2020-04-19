package com.tr.cluster.predictor.service;

import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.TDAG.TDAGPredictor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class TDAGPredictorService extends AbstractPredictorService<TDAGPredictor> implements BasePredictorService {

    @PostConstruct
    public void init() {
        train("TDAGPredictor", null, TDAGPredictor.class);
        System.out.println("Training finished for TDAGPredictor.");
    }

}
