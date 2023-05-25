import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * クイズ定義ファイルを読み込む機能をまとめたクラス
 */
public class QuizLoader {

    /**
     * ファイル一つを行分割のリストで読み込む
     * 
     * @param filename クイズ定義ファイルへのパス
     * @return ファイルに書き込まれている内容から生成した{Quiz}
     * @throws FileNotFoundException ファイルが存在しなかった
     */
    public static Quiz load(String filename) throws FileNotFoundException{
        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
        }
        catch (FileNotFoundException e){
            System.out.println(filename + "はありません。");
            throw e;
        }
        Scanner sc = new Scanner(fis, "UTF-8");
        String questionType = sc.nextLine();
        if (questionType.equals("select4")) {
            String question = sc.nextLine();
            List<String> choices = new ArrayList<>(4);
            for (int i=0; i < 4; i++) {
                choices.add(sc.nextLine());
            }
            String answer = sc.nextLine();
            return new SelectionQuiz(question, choices, answer);
        }
        else {
            throw new RuntimeException("サポートしてない問題形式です");
        }
    }
}
