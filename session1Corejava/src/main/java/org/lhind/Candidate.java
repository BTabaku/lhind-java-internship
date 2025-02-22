package org.lhind;

        import java.util.HashMap;
        import java.util.Map;

        public class Candidate {
            private String firstName;
            private String lastName;
            private String email;
            private int age;
            private String phoneNumber;
            private Map<Survey, Map<Question, Integer>> surveyAnswers;

            public Candidate(String firstName, String lastName, String email, int age, String phoneNumber) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.age = age;
                this.phoneNumber = phoneNumber;
                this.surveyAnswers = new HashMap<>();
            }

            public String getName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void answerSurvey(Survey survey, Map<Question, Integer> answers) {
                surveyAnswers.put(survey, answers);
                for (Map.Entry<Question, Integer> entry : answers.entrySet()) {
                    entry.getKey().recordAnswer(entry.getValue());
                }
            }

            public Map<Question, Integer> getAnswers(Survey survey) {
                return surveyAnswers.getOrDefault(survey, new HashMap<>());
            }
        }