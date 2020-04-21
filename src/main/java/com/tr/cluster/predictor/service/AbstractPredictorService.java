package com.tr.cluster.predictor.service;

import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Item;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.Sequence;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceDatabase;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.database.SequenceStatsGenerator;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.Predictor;
import ca.pfv.spmf.algorithms.sequenceprediction.ipredict.predictor.TDAG.TDAGPredictor;
import com.tr.cluster.predictor.util.FileUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractPredictorService<T extends Predictor> implements BasePredictorService {

    private T predictor;

    @Override
    public void train(String tag, String optionalParameters, Class clazz) {
        SequenceDatabase trainingSet = getTrainingSet();
        Constructor<T> constructor = getConstructor(clazz);
        initializePredictor(tag, optionalParameters, constructor);

        Iterator var4 = trainingSet.getSequences().iterator();

        while(var4.hasNext()) {
            Sequence sequence = (Sequence)var4.next();
            System.out.println(sequence.toString());
        }
        System.out.println();
        try {
            SequenceStatsGenerator.prinStats(trainingSet, " training sequences ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.predictor.Train(trainingSet.getSequences());
    }

    private SequenceDatabase getTrainingSet() {
        String inputPath = null;
        try {
            inputPath = FileUtil.getTrainDataFilePath();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SequenceDatabase trainingSet = new SequenceDatabase();
        trainingSet.loadFileSPMFFormat(inputPath, 2147483647, 0, 2147483647);
        return trainingSet;
    }

    private void initializePredictor(String tag, String optionalParameters, Constructor<T> constructor) {
        try {
            if (constructor.getDeclaringClass() == TDAGPredictor.class) {
                this.predictor = constructor.newInstance(tag);
            } else {
                this.predictor = constructor.newInstance(tag, optionalParameters);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Constructor<T> getConstructor(Class clazz) {
        Constructor<T> constructor = null;
        try {
            if (clazz == TDAGPredictor.class) {
                constructor = clazz.getConstructor(String.class);
            } else {
                constructor = clazz.getConstructor(String.class, String.class);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return constructor;
    }

    @Override
    public String predict(List<Integer> clusters) {
        Sequence sequence = convertClustersToSequence(clusters);
        Sequence nextCluster = this.predictor.Predict(sequence);
        return nextCluster.getItems().get(0).toString();
    }

    private Sequence convertClustersToSequence(List<Integer> clusters) {
        Sequence sequence = new Sequence(0);
        clusters.forEach(cluster -> sequence.addItem(new Item(cluster)));
        return sequence;
    }
}
