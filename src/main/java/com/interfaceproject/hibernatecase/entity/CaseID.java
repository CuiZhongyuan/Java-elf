package com.interfaceproject.hibernatecase.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@Table(name = "c_caseuserID")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class CaseID {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String casename;
    private String caseresult;
    @NotBlank(message = "不能为空")
    private String userId;
    private Boolean flag;

}
