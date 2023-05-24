import java.util.List;

/**
 * 様々な形式のクイズそれぞれが備える必要があるクラス
 */
public interface Quiz {
    /**
     * 問題文を取得する
     * 
     * @return 問題文
     */
    String getQuestion();

    /**
     * 選択肢の配列を取得する
     * 初期化時に選択肢の順番はシャッフルされ、その後は順番が変化しないことが期待されます
     * 
     * @return 選択肢の配列 (存在しない場合は空のList)
     */
    List<String> getChoices();

    /**
     * 解答入力に基づいて正誤を判定する
     * 
     * @param in 正誤判定する解答文字列 (選択肢方式であれば数字、自由入力であればその文字列)
     * @return 正誤判定結果
     */
    boolean check(String in);

    /**
     * 正答を取得する
     * 
     * @return 正答の文字列
     */
    String getAnswer();
}
