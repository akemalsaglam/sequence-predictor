package com.tr.cluster.predictor.service;

import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.Markov.MarkovAllKPredictor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class AkomPredictorService extends AbstractPredictorService<MarkovAllKPredictor> implements BasePredictorService {

    @PostConstruct
    public void init() {
        String optionalParameters = "order:5";
        train("AKOM", optionalParameters, MarkovAllKPredictor.class);
        System.out.println("Training finished for Akom.");
    }

}
