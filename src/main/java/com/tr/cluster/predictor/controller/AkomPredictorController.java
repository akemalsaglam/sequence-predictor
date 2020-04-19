package com.tr.cluster.predictor.controller;

import com.tr.cluster.predictor.service.AkomPredictorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/akom")
public class AkomPredictorController implements BasePredictorController{

    @Autowired
    private AkomPredictorService akomPredictorService;

    @Override
    @PostMapping(path = "/")
    public String getPredictedCluster(@RequestBody List<Integer> clusters) {
        return akomPredictorService.predict(clusters);
    }
}
