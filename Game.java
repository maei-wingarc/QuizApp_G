import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game{
    private String dirname;
    private List<String> fileNames;
    private Set<String> challengedFileNamesSet;

    public Game(String dirname) {
        this.dirname = dirname;

        fileNames = getAllQuestionFileNames();
        challengedFileNamesSet = new HashSet<String>();
    }

    // {dirName}ディレクトリに含まれるすべてのファイル名を取得し、そのリストを返す
    private List<String> getAllQuestionFileNames() {
        List<String> fileNames = new ArrayList<String>();

        File dir = new File(dirname);
        for(File file: dir.listFiles()){
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    private String getRandomFileName(){
        if(challengedFileNamesSet.size() == fileNames.size()){
            throw new RuntimeException("既にすべての問題を出題しています");
        }
        int fileIndex = new Random().nextInt(fileNames.size());
        String fileName = fileNames.get(fileIndex);
        if(challengedFileNamesSet.contains(fileName)){
            return getRandomFileName();
        }
        challengedFileNamesSet.add(fileName);
        return fileName;
    }

    public void run() {
        // TODO
    }
}