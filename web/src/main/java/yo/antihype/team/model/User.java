package yo.antihype.team.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Serhii_Vasylenko on 9/19/2017.
 */
@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private  Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;
}
