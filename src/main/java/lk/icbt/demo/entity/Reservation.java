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
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "date")
    private String date;

    @Column(name = "head_count")
    private int headCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "buffet", nullable = false)
    private Buffet buffet;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch", nullable = false)
    private Branch branch;

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

    public enum Buffet {
        LUNCH,
        DINNER,
        HIGH_TEA
    }

    public enum Branch {
        KANDY,
        COLOMBO,
        GALLE
    }
}
