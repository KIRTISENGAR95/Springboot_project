
package com.api.billhostpro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base Entity
 *
 * Common fields used by all entities.
 *
 * @author Kirti
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Created By
     */
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    /**
     * Updated By
     */
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Created Date
     */
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    /**
     * Updated Date
     */
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}