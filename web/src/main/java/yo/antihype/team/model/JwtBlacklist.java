package yo.antihype.team.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JWT_BLACKLIST")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JwtBlacklist {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private  Long id;

    @Column(name = "TOKEN")
    private String token;
}

