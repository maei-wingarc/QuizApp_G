import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

    private String getQuizFileName(){
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
            Quiz quiz = QuizLoader.load(dirname + "/" + getQuizFileName());
            
            ++challenged;
            dumpQuiz(quiz);
            if(checkAnswer(isr, br, quiz)) ++solved;

            System.out.println("現在の正解数 " + solved + "問(" + challenged +"問中)"); 
            if (fileNames.size() == challenged) {
                System.out.println("終わり！");
                break;
            }
            
            if(!checkExit(br)) break;
        }
        System.out.println("またあそんでねー");
    }

    private boolean checkAnswer(InputStreamReader isr, BufferedReader br, Quiz quiz)
            throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.print("答えを書いてね! : ");
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
                    return true;
                } else {
                    System.out.println("不正解");
                    System.out.println("正解は" + quiz.getAnswer());
                    return false;
                }
            }
            Thread.sleep(100);
        }
        return false;
    }

    private void dumpQuiz(Quiz quiz) {
        System.out.println("\n======= 問題 =======");
        System.out.println(quiz.getQuestion());
        System.out.println("答えを選択肢から選んでね！制限時間は10秒！");
        for (int i=0; i < quiz.getChoices().size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(quiz.getChoices().get(i));
        }
    }

    public static boolean checkExit(BufferedReader br) throws IOException{
        System.out.println("つづけますか (y/n)");
        String line = br.readLine();
        if(line == null) return false;

        if(line.length() > 0 && line.charAt(line.length() - 1) == 'y') return true;
        if(line.length() > 0 && line.charAt(line.length() - 1) == 'n') return false;
        System.out.println("y もしくは nを入力してください");
        return checkExit(br);
    }
}