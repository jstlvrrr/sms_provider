package dasturlash.uz.entity;

import dasturlash.uz.enums.GeneralStatus;
import dasturlash.uz.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneralStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Boolean visible;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.visible = true;
        if (this.status == null) this.status = GeneralStatus.ACTIVE;
        if (this.role == null) this.role = Role.ADMIN;
    }
}
