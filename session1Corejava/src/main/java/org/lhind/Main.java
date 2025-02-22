package org.lhind;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create questions
        Question q1 = new Question("Do you agree with the statement?");
        Question q2 = new Question("Is the sky blue?");
        Question q3 = new Question("Is water wet?");
        Question q4 = new Question("Is fire hot?");
        Question q5 = new Question("Is ice cold?");
        Question q6 = new Question("Is Java a programming language?");
        Question q7 = new Question("Is Python a snake?");
        Question q8 = new Question("Is HTML a programming language?");
        Question q9 = new Question("Is CSS used for styling?");
        Question q10 = new Question("Is JavaScript used for web development?");
        Question q11 = new Question("Is C++ an extension of C?");
        Question q12 = new Question("Is SQL used for databases?");
        Question q13 = new Question("Is Git a version control system?");
        Question q14 = new Question("Is Linux an operating system?");
        Question q15 = new Question("Is Windows an operating system?");

        // Create a survey and add questions
        Survey survey = new Survey("General Knowledge and Programming Questions");
        survey.addQuestion(q1);
        survey.addQuestion(q2);
        survey.addQuestion(q3);
        survey.addQuestion(q4);
        survey.addQuestion(q5);
        survey.addQuestion(q6);
        survey.addQuestion(q7);
        survey.addQuestion(q8);
        survey.addQuestion(q9);
        survey.addQuestion(q10);
        survey.addQuestion(q11);
        survey.addQuestion(q12);
        survey.addQuestion(q13);
        survey.addQuestion(q14);
        survey.addQuestion(q15);

        // Create candidates with Albanian names
        Candidate candidate1 = new Candidate("Arben", "Hoxha", "arben.hoxha@example.com", 30, "+35569725839x");
        Candidate candidate2 = new Candidate("Blerina", "Krasniqi", "blerina.krasniqi@example.com", 25, "+35569725838x");
        Candidate candidate3 = new Candidate("Dritan", "Berisha", "dritan.berisha@example.com", 28, "+35569725837x");
        Candidate candidate4 = new Candidate("Elira", "Gashi", "elira.gashi@example.com", 35, "+35569725836x");
        Candidate candidate5 = new Candidate("Fatos", "Rama", "fatos.rama@example.com", 32, "+35569725835x");
        Candidate candidate6 = new Candidate("Gentiana", "Leka", "gentiana.leka@example.com", 29, "+35569725834x");

        // Add candidates to the survey
        survey.addCandidate(candidate1);
        survey.addCandidate(candidate2);
        survey.addCandidate(candidate3);
        survey.addCandidate(candidate4);
        survey.addCandidate(candidate5);
        survey.addCandidate(candidate6);

        // Candidates answer the survey
        Map<Question, Integer> answers1 = new HashMap<>();
        answers1.put(q1, 0); // Agree
        answers1.put(q2, 1); // Slightly Agree
        answers1.put(q3, 2); // Slightly Disagree
        answers1.put(q4, 3); // Disagree
        answers1.put(q5, 0); // Agree
        answers1.put(q6, 0); // Agree
        answers1.put(q7, 3); // Disagree
        answers1.put(q8, 2); // Slightly Disagree
        answers1.put(q9, 0); // Agree
        answers1.put(q10, 0); // Agree
        answers1.put(q11, 0); // Agree
        answers1.put(q12, 0); // Agree
        answers1.put(q13, 0); // Agree
        answers1.put(q14, 0); // Agree
        answers1.put(q15, 0); // Agree
        candidate1.answerSurvey(survey, answers1);

        Map<Question, Integer> answers2 = new HashMap<>();
        answers2.put(q1, 3); // Disagree
        answers2.put(q2, 0); // Agree
        answers2.put(q3, 1); // Slightly Agree
        answers2.put(q4, 2); // Slightly Disagree
        answers2.put(q5, 3); // Disagree
        answers2.put(q6, 0); // Agree
        answers2.put(q7, 3); // Disagree
        answers2.put(q8, 2); // Slightly Disagree
        answers2.put(q9, 0); // Agree
        answers2.put(q10, 0); // Agree
        answers2.put(q11, 0); // Agree
        answers2.put(q12, 0); // Agree
        answers2.put(q13, 0); // Agree
        answers2.put(q14, 0); // Agree
        answers2.put(q15, 0); // Agree
        candidate2.answerSurvey(survey, answers2);

        Map<Question, Integer> answers3 = new HashMap<>();
        answers3.put(q1, 1); // Slightly Agree
        answers3.put(q2, 2); // Slightly Disagree
        answers3.put(q3, 3); // Disagree
        answers3.put(q4, 0); // Agree
        answers3.put(q5, 1); // Slightly Agree
        answers3.put(q6, 0); // Agree
        answers3.put(q7, 3); // Disagree
        answers3.put(q8, 2); // Slightly Disagree
        answers3.put(q9, 0); // Agree
        answers3.put(q10, 0); // Agree
        answers3.put(q11, 0); // Agree
        answers3.put(q12, 0); // Agree
        answers3.put(q13, 0); // Agree
        answers3.put(q14, 0); // Agree
        answers3.put(q15, 0); // Agree
        candidate3.answerSurvey(survey, answers3);

        Map<Question, Integer> answers4 = new HashMap<>();
        answers4.put(q1, 2); // Slightly Disagree
        answers4.put(q2, 3); // Disagree
        answers4.put(q3, 0); // Agree
        answers4.put(q4, 1); // Slightly Agree
        answers4.put(q5, 2); // Slightly Disagree
        answers4.put(q6, 0); // Agree
        answers4.put(q7, 3); // Disagree
        answers4.put(q8, 2); // Slightly Disagree
        answers4.put(q9, 0); // Agree
        answers4.put(q10, 0); // Agree
        answers4.put(q11, 0); // Agree
        answers4.put(q12, 0); // Agree
        answers4.put(q13, 0); // Agree
        answers4.put(q14, 0); // Agree
        answers4.put(q15, 0); // Agree
        candidate4.answerSurvey(survey, answers4);

        Map<Question, Integer> answers5 = new HashMap<>();
        answers5.put(q1, 0); // Agree
        answers5.put(q2, 0); // Agree
        answers5.put(q3, 0); // Agree
        answers5.put(q4, 0); // Agree
        answers5.put(q5, 0); // Agree
        answers5.put(q6, 0); // Agree
        answers5.put(q7, 3); // Disagree
        answers5.put(q8, 2); // Slightly Disagree
        answers5.put(q9, 0); // Agree
        answers5.put(q10, 0); // Agree
        answers5.put(q11, 0); // Agree
        answers5.put(q12, 0); // Agree
        answers5.put(q13, 0); // Agree
        answers5.put(q14, 0); // Agree
        answers5.put(q15, 0); // Agree
        candidate5.answerSurvey(survey, answers5);

        Map<Question, Integer> answers6 = new HashMap<>();
        answers6.put(q1, 3); // Disagree
        answers6.put(q2, 3); // Disagree
        answers6.put(q3, 3); // Disagree
        answers6.put(q4, 3); // Disagree
        answers6.put(q5, 3); // Disagree
        answers6.put(q6, 0); // Agree
        answers6.put(q7, 3); // Disagree
        answers6.put(q8, 2); // Slightly Disagree
        answers6.put(q9, 0); // Agree
        answers6.put(q10, 0); // Agree
        answers6.put(q11, 0); // Agree
        answers6.put(q12, 0); // Agree
        answers6.put(q13, 0); // Agree
        answers6.put(q14, 0); // Agree
        answers6.put(q15, 0); // Agree
        candidate6.answerSurvey(survey, answers6);

        // Create a SurveyManager and add the survey
        SurveyManager surveyManager = new SurveyManager();
        surveyManager.addSurvey(survey);

        // Print survey results
        surveyManager.printSurveyResults(survey);

        // Find the most given answer
        String mostGivenAnswer = surveyManager.findMostGivenAnswer(survey);
        System.out.println("Most given answer: " + mostGivenAnswer);

        // Find answers by a specific candidate
        Map<Question, Integer> candidate1Answers = surveyManager.findAnswersByCandidate(survey, candidate1);
        System.out.println("Answers by " + candidate1.getName() + ": " + formatAnswers(candidate1Answers));

        // Find the candidate with the most surveys
        Candidate topCandidate = surveyManager.findCandidateWithMostSurveys();
        System.out.println("Candidate with most surveys: " + (topCandidate != null ? topCandidate.getName() : "None"));
    }

    private static String formatAnswers(Map<Question, Integer> answers) {
        StringBuilder formattedAnswers = new StringBuilder();
        for (Map.Entry<Question, Integer> entry : answers.entrySet()) {
            formattedAnswers.append(entry.getKey().getQuestion()).append(": ").append(getAnswerText(entry.getValue())).append(", ");
        }
        return formattedAnswers.toString();
    }

    private static String getAnswerText(int index) {
        String[] options = {"Agree", "Slightly Agree", "Slightly Disagree", "Disagree"};
        return (index >= 0 && index < options.length) ? options[index] : "No Answer";
    }
}