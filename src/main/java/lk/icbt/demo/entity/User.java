package lk.icbt.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine01;

    @Column(name = "address_line_2", nullable = false)
    private String addressLine02;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "card_holder", nullable = false)
    private String cardHolder;

    @Column(name = "card_no", nullable = false)
    private String cardNo;

    @Column(name = "exp", nullable = false)
    private String exp;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;


    public enum Status {
        ACTIVE,
        INACTIVE
    }

}
