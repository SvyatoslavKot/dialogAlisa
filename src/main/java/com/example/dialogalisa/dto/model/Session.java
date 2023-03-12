package com.example.dialogalisa.dto.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@Data
@Table(name = "SESSION_STATE")
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Session{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Nullable
    @Column(name = "ID", columnDefinition = "bigint", nullable = false)
    Long id;

    @Column(name = "state", columnDefinition = "varchar(255)", nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    SessionState state;

    @Column(columnDefinition = "varchar(255)", nullable = false, name = "SESSION_ID")
    @NonNull
    String sessionId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(columnDefinition = "bigint", name = "SERVICE_USER_ID", referencedColumnName = "ID", nullable = false)
    ServiceUser serviceUser;

    @Column(columnDefinition = "varchar(255)", name = "WORD")
    @Nullable
    String word;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(32)", name = "LANGUAGE")
    @Nullable
    Language language;


    public Session(@NonNull String sessionId) {
        this.sessionId = sessionId;
    }

    public void clearWordInfo() {
        word = null;
        language = null;
    }

}
