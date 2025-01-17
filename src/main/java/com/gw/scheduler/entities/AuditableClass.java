package com.gw.scheduler.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditableClass
{
	@CreatedDate
	@Column(updatable = false)
	@Schema(description = "Date when the entity was created")
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Schema(description = "Date when the entity was last updated")
	private LocalDateTime modifiedDate;

	@CreatedBy
	@Column(updatable = false)
	@Schema(description = "User ID who created the entity. Defaulting to 0 without user auth in place")
	private int createdBy = 0;

	@LastModifiedBy
	@Schema(description = "User ID who last updated the entity. Defaulting to 0 without user auth in place")
	private int modifiedBy = 0;
}
