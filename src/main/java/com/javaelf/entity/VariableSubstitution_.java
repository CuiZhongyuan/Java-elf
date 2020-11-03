package com.javaelf.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( VariableSubstitution.class)
public class VariableSubstitution_ {
    public static volatile SingularAttribute<VariableSubstitution,Long> id;
    public static volatile SingularAttribute<VariableSubstitution,String> name;
    public static volatile SingularAttribute<VariableSubstitution,String> value;
    public static volatile SingularAttribute<VariableSubstitution,String> reflectCalss;
    public static volatile SingularAttribute<VariableSubstitution,String> reflectMethod;
    public static volatile SingularAttribute<VariableSubstitution,String> reflectValue;
    public static volatile SingularAttribute<VariableSubstitution,String> remark;
}
