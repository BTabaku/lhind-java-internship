package com.internship.session1;

        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

        public class Survey {
            private String title;
            private List<Question> questions;
            private List<Candidate> candidates;

            public Survey(String title) {
                this.title = title;
                this.questions = new ArrayList<>();
                this.candidates = new ArrayList<>();
            }

            public String getTitle() {
                return title;
            }

            public List<Question> getQuestions() {
                return questions;
            }

            public List<Candidate> getCandidates() {
                return candidates;
            }

            public void addQuestion(Question question) {
                questions.add(question);
            }

            public void addCandidate(Candidate candidate) {
                candidates.add(candidate);
            }

            public boolean isValid() {
                if (questions.size() < 10 || questions.size() > 40) {
                    return false;
                }
                Set<Question> uniqueQuestions = new HashSet<>(questions);
                return uniqueQuestions.size() == questions.size();
            }
        }