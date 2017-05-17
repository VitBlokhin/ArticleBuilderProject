package org.itstep.pps2701.blokhin;

import java.io.File;

/**
 * Created by Vit on 15.05.2017.
 */
public class Utils {

    public static boolean isValidFile(String filename) {
        File file = new File(filename);

        return (file.exists() && file.isFile() && file.length() > 0);
    } // isValid
} // class Utils
