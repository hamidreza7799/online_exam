package ir.maktab.online_exam.domains;

import javax.persistence.*;

@Entity
@Table(name = "tbl_manager")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "getRoleInAuthorization",
                attributeNodes = {
                        @NamedAttributeNode(value = "roles", subgraph = "getRoleOperations")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "getRoleOperations",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "operations")
                                }
                        )
                }
        )
})
public class Manager extends User{
}
