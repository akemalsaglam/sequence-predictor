package com.tr.cluster.predictor.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileUtil {

    public static String getTrainDataFilePath() throws UnsupportedEncodingException {
        URL url = FileUtil.class.getClassLoader().getResource("sample-small-data.txt");
        return URLDecoder.decode(url.getPath(), "UTF-8");
    }
}
