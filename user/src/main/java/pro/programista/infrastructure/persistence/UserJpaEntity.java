package pro.programista.infrastructure.persistence;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "system_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserJpaEntity {

  @Id
  @GeneratedValue
  UUID id;

  @Column
  String firstName;

  @Column
  String lastName;

  @Column
  String email;

}
