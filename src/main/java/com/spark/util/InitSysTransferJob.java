package com.spark.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InitSysTransferJob {
    public static final String sqlprefix="insert into sys_transfer_job(source_jdbc, source_user,source_passw,source_table,source_class,to_jdbc,to_user,to_passw,to_table,to_class)" +
            "values ('jdbc:postgresql://192.168.0.115:5432/ods','postgres','pg123456'," ;
    public static final String middleprefix=",'org.postgresql.Driver','jdbc:postgresql://192.168.0.115:5432/sync?currentSchema=financial','postgres','pg123456',";
    public static final String afterprefix=",'org.postgresql.Driver');";
    public static final String sqlfrom="select tablename from pg_tables where schemaname='financial'";

    public static  String str="%s 123 %s";
    public static void main(String[] args) throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.250:5432/sync?currentSchema=financial", "ddl_dds", "ddl(%#ya927!");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sqlfrom);
        while (rs.next()) {
           String table= rs.getString(1);
            System.out.println(sqlprefix+"'"+table+"'"+middleprefix+"'"+table+"'"+afterprefix);

        }

    }
}