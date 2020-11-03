package com.javaelf.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@Table(name = "variableSubstitution")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
@DynamicInsert
@DynamicUpdate
public class VariableSubstitution {
    private static final long serialVersionUID = 1L;
    /**
     * url的id
     */
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotBlank(message = "id不能为空")
    @Id
    private Long id;
    /**
     * 变量名
     */
    @NotBlank(message = "变量名不能为空")
    @Length(max = 32,message = "最大32位")
    @Column(nullable = false,length = 32)
    private String name;
    /**
     * 变量值
     */
    private String value;
    /**
     * 反射的类名
     */
    private String reflectCalss;
    /**
     * 反射类的方法
     */
    private String reflectMethod;
    /**
     * 反射方法返回的值
     */
    private String reflectValue;
    /**
     * 备注信息
     */
    private String remark;
}
