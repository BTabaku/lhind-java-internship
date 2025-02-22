package org.lhind;

import java.util.*;

public class SurveyManager {
    private List<Survey> surveys;

    public SurveyManager() {
        this.surveys = new ArrayList<>();
    }

    public void addSurvey(Survey survey) {
        if (survey.isValid()) {
            surveys.add(survey);
        } else {
            System.out.println("Survey validation failed: Must have between 10 and 40 unique questions.");
        }
    }

    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }

    public String findMostGivenAnswer(Survey survey) {
        int[] totalAnswers = new int[4]; // [Agree, Slightly Agree, Slightly Disagree, Disagree]
        for (Question question : survey.getQuestions()) {
            int[] answers = question.getAnswers();
            for (int i = 0; i < answers.length; i++) {
                totalAnswers[i] += answers[i];
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < totalAnswers.length; i++) {
            if (totalAnswers[i] > totalAnswers[maxIndex]) {
                maxIndex = i;
            }
        }
        return getAnswerText(maxIndex);
    }

    public void printSurveyResults(Survey survey) {
        for (Question question : survey.getQuestions()) {
            System.out.println("Question: " + question.getQuestion());
            int[] answers = question.getAnswers();
            System.out.println("Agree: " + answers[0]);
            System.out.println("Slightly Agree: " + answers[1]);
            System.out.println("Slightly Disagree: " + answers[2]);
            System.out.println("Disagree: " + answers[3]);

            // Print detailed answers by candidates
            for (Candidate candidate : survey.getCandidates()) {
                Map<Question, Integer> candidateAnswers = candidate.getAnswers(survey);
                if (candidateAnswers.containsKey(question)) {
                    System.out.println(candidate.getName() + " " + candidate.getLastName() + ": " + getAnswerText(candidateAnswers.get(question)));
                } else {
                    System.out.println(candidate.getName() + " " + candidate.getLastName() + ": Not Answered");
                }
            }
            System.out.println();
        }
    }

    public Map<Question, Integer> findAnswersByCandidate(Survey survey, Candidate candidate) {
        return candidate.getAnswers(survey);
    }

    public Candidate findCandidateWithMostSurveys() {
        Map<Candidate, Integer> surveyCounts = new HashMap<>();
        for (Survey survey : surveys) {
            for (Candidate candidate : survey.getCandidates()) {
                surveyCounts.put(candidate, surveyCounts.getOrDefault(candidate, 0) + 1);
            }
        }
        return surveyCounts.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public void addQuestionToSurvey(Survey survey, Question question) {
        survey.addQuestion(question);
    }

    public void removeQuestionFromSurvey(Survey survey, Question question) {
        survey.getQuestions().remove(question);
    }

    public void checkAndRemoveUnansweredQuestions(Survey survey) {
        int candidateCount = survey.getCandidates().size();
        survey.getQuestions().removeIf(q -> Arrays.stream(q.getAnswers()).sum() < candidateCount / 2);
    }

    private String getAnswerText(int index) {
        String[] options = {"Agree", "Slightly Agree", "Slightly Disagree", "Disagree"};
        return (index >= 0 && index < options.length) ? options[index] : "No Answer";
    }
}