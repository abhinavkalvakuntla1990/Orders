package org.egen.ecommerce.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/***
 * Description: This class provides created and last modified date automatically
 * to the schema as well as the user who created or modified it. Every time a
 * row is created in the table. It will automatically put the timestamp for the
 * created_at column and the created_by field will be updated. It also updates
 * the last_modified_at and last_modified_by column on any entity modification
 * so programmers do not need to update them every time.
 *
 *
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractAuditableEntity {

  @CreatedDate
  @Column(updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  private Instant modifiedAt;

  @CreatedBy
  @Column(updatable = false)
  private String createdBy;

  @LastModifiedBy
  private String modifiedBy;

}