import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game{
    private final String dirname;
    private final List<String> fileNames;
    private int nextQuizIndex;

    public Game(String dirname) {
        this.dirname = dirname;

        fileNames = getAllQuestionFileNames();

        nextQuizIndex = 0;
    }

    // {dirName}ディレクトリに含まれるすべてのファイル名を取得し、そのリストを返す
    private List<String> getAllQuestionFileNames() {
        List<String> fileNames = new ArrayList<String>();

        File dir = new File(dirname);
        for(File file: dir.listFiles()){
            fileNames.add(file.getName());
        }
        Collections.shuffle(fileNames);
        return fileNames;
    }

    private String getRandomFileName(){
        if(nextQuizIndex == fileNames.size()){
            throw new RuntimeException("既にすべての問題を出題しています");
        }
        String fileName = fileNames.get(nextQuizIndex);
        ++nextQuizIndex;
        
        return fileName;
    }

    public void run() throws IOException, InterruptedException {
        System.out.println("クイズだよ！");
        
        int solved = 0, challenged = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        while(true){
            Quiz quiz;
            try {
                quiz = QuizLoader.load(dirname + "/" + getRandomFileName());
            } catch (FileNotFoundException e) {
                System.out.println("途中でファイル消したらだめだよ！！");
                return;
            }
            
            ++challenged;
            System.out.println(quiz.getQuestion());
            System.out.println("答えを選択肢から選んでね！制限時間は10秒！");
            for (int i=0; i < quiz.getChoices().size(); i++) {
                System.out.print((i+1) + " ");
                System.out.println(quiz.getChoices().get(i));
            }
            
            long startTime = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - startTime > 10000) {
                    System.out.println("時間切れ");
                    System.out.println("正解は" + quiz.getAnswer());
                    break;
                }
                
                if (isr.ready()) {
                    String choice = br.readLine();
                    if (quiz.check(choice)) {
                        System.out.println("正解");
                        ++solved;
                    } else {
                        System.out.println("不正解");
                        System.out.println("正解は" + quiz.getAnswer());
                    }
                    break;
                }
                Thread.sleep(100);
            }

            System.out.println("現在の正解数 " + solved + "問(" + challenged +"問中)"); 
            if (fileNames.size() == challenged) {
                System.out.println("終わり！");
                break;
            }
            System.out.println("つづけますか (y/n)");
            boolean exitFlag = false;
            while(true){
                String line = br.readLine();
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
    }
}