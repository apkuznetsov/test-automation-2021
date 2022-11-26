package ssau.kuznetsov.webquizengine.dtos;

import ssau.kuznetsov.webquizengine.models.Quiz;

public class Feedback {

    private final boolean isSuccess;
    private final String feedback;

    public Feedback(Quiz quiz, Answer answer) {
        if (quiz.getAnswer().equals(answer.getAnswer())) {
            isSuccess = true;
            feedback = "Congratulations, you're right!";
        } else {
            isSuccess = false;
            feedback = "Wrong answer! Please, try again.";
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getFeedback() {
        return feedback;
    }
}
