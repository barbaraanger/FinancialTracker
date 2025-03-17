package com.anger.FinancialTracker.repository.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.CreationTimestamp;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public class Expense {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private BigDecimal amount;

        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn(name = "account_id", nullable = false)
        @JsonIgnore
        private Account account;


        @JsonProperty("accountId")
        public Long getAccountId() {
            return account != null ? account.getId() : null;
        }

        public void setAccountId(Long accountId) {
            if (accountId != null) {
                account = new Account();
                account.setId(accountId);
            }
        }
    }