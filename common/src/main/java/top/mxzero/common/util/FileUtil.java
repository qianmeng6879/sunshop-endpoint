package top.mxzero.common.util;

import java.util.UUID;

public class FileUtil {
    private FileUtil(){}

    public static String getExtendName(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public static String generateFilename(String filename){
        return String.format("%s.%s", UUID.randomUUID(),FileUtil.getExtendName(filename));
    }
}

