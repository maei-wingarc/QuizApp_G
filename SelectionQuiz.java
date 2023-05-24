import java.util.List;

public class SelectionQuiz implements Quiz{
    final String question;
    final List<String> choices;
    final String answer;

    public SelectionQuiz(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public List<String> getChoices() {
        return this.choices;
    }

    @Override
    public boolean check(String in) {
        return answer.equals(in);
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }
    
}
