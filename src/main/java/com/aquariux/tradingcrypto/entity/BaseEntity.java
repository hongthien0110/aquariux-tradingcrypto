package com.aquariux.tradingcrypto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class BaseEntity {

    @LastModifiedDate
    @Builder.Default
    @JsonIgnore
    protected LocalDateTime updatedDate = LocalDateTime.now();

    @CreatedDate
    @Builder.Default
    @JsonIgnore
    protected LocalDateTime createdDate = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

}
