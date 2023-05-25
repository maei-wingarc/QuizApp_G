import java.util.List;

/**
 * 選択肢型のクイズ機能を提供する
 */
public class SelectionQuiz implements Quiz{
    final String question;
    final List<String> choices;
    final String answer;

    /**
     * 選択肢型クイズクラスを初期化する
     * 
     * @param question 問題文
     * @param choices 解答リスト (順序はランダム化されていることが期待されます)
     * @param answer 正答
     */
    public SelectionQuiz(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    /** {@inheritDoc} */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> getChoices() {
        return this.choices;
    }

    /** {@inheritDoc} */
    @Override
    public boolean check(String in) {
        return answer.equals(in);
    }

    /** {@inheritDoc} */
    @Override
    public String getAnswer() {
        return this.answer;
    }
    
}
