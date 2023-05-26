import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 記述型のクイズ機能を提供する
 */
public class DescriptionQuiz implements Quiz {
    final private String question;
    final private List<String> answers;

    /**
     * 記述型クイズクラスを初期化する
     * 
     * @param question 問題文
     * @param answer 正答と別解のリスト
     */
    public DescriptionQuiz(String question, List<String> answers) {
        if (answers.isEmpty()) throw new RuntimeException("記述問題の正答が空です");
        this.question = question;
        this.answers = answers;
    }

    /**
     * 記述型クイズクラスを初期化する
     * 答えが一つだけのとき用、互換性のため
     * 
     * @param question 問題文
     * @param answer 正答
     */
    public DescriptionQuiz(String question, String answer) {
        this.question = question;
        this.answers = Arrays.asList(answer);
    }

    /** {@inheritDoc} */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public List<String> getChoices() {
        return Collections.emptyList(); /// XXX: とりあえず空のリスト返すよ
    }

    /** {@inheritDoc} */
    @Override
    public boolean check(String in) {
        return answers.stream().anyMatch(s -> s.equals(in));
    }

    /** {@inheritDoc} */
    @Override
    public String getAnswer() {
        return this.answers.get(0);
    }
    
}
