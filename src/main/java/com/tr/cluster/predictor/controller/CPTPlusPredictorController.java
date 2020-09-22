package com.tr.cluster.predictor.controller;

import com.tr.cluster.predictor.service.CPTPlusPredictorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cptplus")
public class CPTPlusPredictorController implements BasePredictorController {

    @Autowired
    private CPTPlusPredictorService cptPlusPredictorService;

    @Override
    @PostMapping(path = "/")
    public String getPredictedCluster(@RequestBody List<Integer> clusters) {
        if(clusters.size() == 0) return null;
        String predictionResult = cptPlusPredictorService.predict(clusters);
        System.out.println("cptPlus prediction executed. predicted data: "
                + clusters.stream().map(Object::toString).collect(Collectors.joining(","))
                + ", result: " + predictionResult);
        return predictionResult;
    }
}
