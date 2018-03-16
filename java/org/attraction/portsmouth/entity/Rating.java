package org.attraction.portsmouth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "rating")
public class Rating {

		@Id
		@Column(name = "idrating")
		@GeneratedValue
		private Integer idrole;

		@NotEmpty
		@Column(name = "idattraction")
		private Integer idattraction;
		
		@NotNull(message="Rating Value needs to be filled")
		@Column(name = "value")
		private Integer value;


}
