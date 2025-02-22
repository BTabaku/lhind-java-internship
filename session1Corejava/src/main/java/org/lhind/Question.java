package org.lhind;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Question {
    private String question;
    private Map<Integer, Integer> answerCounts;

    public Question(String question) {
        if (StringUtils.isNotBlank(question)) {
            this.question = question;
        } else {
            throw new IllegalArgumentException("Question text cannot be blank");
        }
        this.answerCounts = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            answerCounts.put(i, 0);
        }
    }

    public String getQuestion() {
        return question;
    }

    public void recordAnswer(int answer) {
        if (answerCounts.containsKey(answer)) {
            answerCounts.put(answer, answerCounts.get(answer) + 1);
        }
    }

    public int[] getAnswers() {
        int[] answers = new int[4];
        for (int i = 0; i < 4; i++) {
            answers[i] = answerCounts.getOrDefault(i, 0);
        }
        return answers;
    }
}