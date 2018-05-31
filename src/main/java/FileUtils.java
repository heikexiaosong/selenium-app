import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static List<File> loadFiles(String path) {
        if ( path==null || path.length()==0 ) {
            return Collections.EMPTY_LIST;
        }

        File file = new File(path);
        if ( !file.exists() ) {
            return Collections.EMPTY_LIST;
        }

        if ( file.isFile() ) {
            return Collections.singletonList(file);
        }

        if ( file.isDirectory() ) {
            return loadDirFiles(file);
        }

        return Collections.EMPTY_LIST;
    }

    private static List<File> loadDirFiles(File file) {
        File[]  files =  file.listFiles();
        if ( files ==null || files.length==0 ){
            return Collections.EMPTY_LIST;
        }
        List<File> result = new ArrayList<File>();
        for (File file1 : files) {
            if ( file1.isFile() ){
                result.add(file1);
            } else {
                result.addAll(loadDirFiles(file1));
            }

        }
        return result;
    }

    public static void main(String[] args) {
        for (File file : loadFiles("E:\\Document\\公交集团考勤\\data")) {
            System.out.println(file.getName());
        }
    }

}
