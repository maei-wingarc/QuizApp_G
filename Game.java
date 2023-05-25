import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game{
    private String dirname;
    private List<String> fileNames;
    private Set<String> challengedFileNamesSet;
    private Random rand;

    public Game(String dirname) {
        this.dirname = dirname;

        fileNames = getAllQuestionFileNames();
        challengedFileNamesSet = new HashSet<String>();

        rand = new Random(LocalDateTime.now().getNano());
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
        int fileIndex = rand.nextInt(fileNames.size());
        String fileName = fileNames.get(fileIndex);
        if(challengedFileNamesSet.contains(fileName)){
            return getRandomFileName();
        }
        challengedFileNamesSet.add(fileName);
        return fileName;
    }

    public void run() {
        System.out.println("クイズです");
        
        int solved = 0, challenged = 0;
        Scanner scanner = new Scanner(System.in);
        while(true){
            Quiz quiz;
            try {
                quiz = QuizLoader.load(dirname + "/" + getRandomFileName());
            } catch (FileNotFoundException e) {
                System.out.println("こわれたよ！");
                scanner.close();
                return;
            }
            ++challenged;
            System.out.println(quiz.getQuestion());
            System.out.println("答えを選択肢から選んでください");
            for (int i=0; i < quiz.getChoices().size(); i++) {
                System.out.print((i+1) + " ");
                System.out.println(quiz.getChoices().get(i));
            }
            
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) {
                if (scanner.hasNextLine()) {
                    String choice = scanner.nextLine();
                    if (quiz.check(choice)) {
                        System.out.println("正解");
                        ++solved;
                    } else {
                        System.out.println("不正解");
                        System.out.println("正解は" + quiz.getAnswer());
                    }
                    break;
                }
            }

            System.out.println("現在の正解数 " + solved + "問(" + challenged +"問中)"); 
            System.out.println("つづけますか (y/n)");
            boolean exitFlag = false;
            while(true){
                String line = scanner.nextLine();
                if(line.equals("y")) break;
                if(line.equals("n")){
                    exitFlag = true;
                    break;
                }
                System.out.println("y もしくは nを入力してください");
            }
            if(exitFlag) break;
        }
        System.out.println("またあそんでねー");
        scanner.close();
    }
}