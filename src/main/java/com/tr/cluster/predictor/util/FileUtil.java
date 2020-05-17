package com.tr.cluster.predictor.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileUtil {

    private static final String TRAINING_DATA_FILE_NAME = "100taxi-month1-40.txt";

    public static String getTrainDataFilePath() throws UnsupportedEncodingException {
        URL url = FileUtil.class.getClassLoader().getResource(TRAINING_DATA_FILE_NAME);
        return URLDecoder.decode(url.getPath(), "UTF-8");
    }
}
