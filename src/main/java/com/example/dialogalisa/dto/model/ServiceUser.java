package com.example.dialogalisa.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Nullable
    @Column(name = "ID", columnDefinition = "bigint", nullable = false)
    Long id;

    @Column(columnDefinition = "varchar(255)", name = "USER_NAME")
    @Nullable
    String name;

    @Column(columnDefinition = "varchar(255)", name = "EXT_USER_SOURCE")
    @NonNull
    @Enumerated(EnumType.STRING)
    UserSource userSource;

    @Column(columnDefinition = "varchar(255)", name = "EXT_USER_ID")
    @Nullable
    String extUserId;

    @Column(columnDefinition = "varchar(255)", name = "EXT_APPLICATION_ID")
    String extAppId;

    @NonNull
    @Column(columnDefinition = "char(1)", name = "LAST_USED", nullable = false)
    String lastUsed;
}
