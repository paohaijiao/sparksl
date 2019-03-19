package com.spark.bean;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_transfer_job")
public class TransferInfo {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="sourceJdbc")
    private String sourceJdbc;
    @Column(name="sourceUser")
    private String sourceUser;
    @Column(name="sourcePassw")
    private String sourcePassw;
    @Column(name="sourceTable")
    private String sourceTable;
    @Column(name="sourceClass")
    private String sourceClass;
    @Column(name="toJdbc")
    private String toJdbc;
    @Column(name="toUser")
    private String toUser;
    @Column(name="toPassw")
    private String toPassw;
    @Column(name="toTable")
    private String toTable;
    @Column(name="toClass")
    private String toClass;
    @Column(name="sourceSchema")
    private String sourceSchema;
    @Column(name="toSchema")
    private String toSchema;
    @Column(name="selectClause")
    private String selectClause;
    @Column(name="whereClause")
    private String whereClause;
    @Column(name="status")
    private String status;

    public String getSourceSchema() {
        return sourceSchema;
    }

    public void setSourceSchema(String sourceSchema) {
        this.sourceSchema = sourceSchema;
    }

    public String getToSchema() {
        return toSchema;
    }

    public void setToSchema(String toSchema) {
        this.toSchema = toSchema;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceJdbc() {
        return sourceJdbc;
    }

    public void setSourceJdbc(String sourceJdbc) {
        this.sourceJdbc = sourceJdbc;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getSourcePassw() {
        return sourcePassw;
    }

    public void setSourcePassw(String sourcePassw) {
        this.sourcePassw = sourcePassw;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public String getToJdbc() {
        return toJdbc;
    }

    public void setToJdbc(String toJdbc) {
        this.toJdbc = toJdbc;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToPassw() {
        return toPassw;
    }

    public void setToPassw(String toPassw) {
        this.toPassw = toPassw;
    }

    public String getToTable() {
        return toTable;
    }

    public void setToTable(String toTable) {
        this.toTable = toTable;
    }

    public String getToClass() {
        return toClass;
    }

    public void setToClass(String toClass) {
        this.toClass = toClass;
    }
}
