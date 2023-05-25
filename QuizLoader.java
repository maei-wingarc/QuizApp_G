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
        try (Scanner sc = new Scanner(fis, "UTF-8")) {
            String questionType = sc.nextLine();
            if (questionType.startsWith("select")) {
                int selectionCount = Integer.parseInt(questionType, 6, questionType.length(), 10);
                if (selectionCount < 2) {
                    throw new RuntimeException("選択肢の数が少なすぎます");
                }
                return createSelectionQuiz(sc, selectionCount);
            } else if (questionType.equals("description")) {
                String question = sc.nextLine();
                String answer = sc.nextLine();
                return new DescriptionQuiz(question, answer);
            } else {
                throw new RuntimeException("サポートしてない問題形式です");
            }
        }
    }

    private static SelectionQuiz createSelectionQuiz(Scanner sc, int n) {
        String question = sc.nextLine();
        List<String> choices = new ArrayList<>(n);
        for (int i=0; i < n; i++) {
            choices.add(sc.nextLine());
        }
        String answer = sc.nextLine();
        return new SelectionQuiz(question, choices, answer);
    }
}
