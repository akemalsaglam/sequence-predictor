package com.tr.cluster.predictor.controller;

import java.util.List;

public interface BasePredictorController {

    String getPredictedCluster(List<Integer> clusters);
}
