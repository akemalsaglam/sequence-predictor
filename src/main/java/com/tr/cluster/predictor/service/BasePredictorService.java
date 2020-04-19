package com.tr.cluster.predictor.service;

import java.util.List;

public interface BasePredictorService {

    void train(String tag, String optionalParameters, Class clazz);

    String predict(List<Integer> clusters);

}
