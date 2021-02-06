package ir.maktab.online_exam.domains;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_multiple_choice_question")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "getQuestionChoices",
                attributeNodes = {
                       @NamedAttributeNode(
                               value = "questionChoices"
                       )
                }
        )
})
public class MultipleChoiceQuestion extends Question {
    //TODO CHECK CASCADE TYPES
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @NotEmpty
    private Set<QuestionChoice> questionChoices = new HashSet<>();

    public Set<QuestionChoice> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(Set<QuestionChoice> questionChoices) {
        this.questionChoices = questionChoices;
    }
}
