package com.mads.bankapp.Repository;

import com.mads.bankapp.Model.BankAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class RepoBankAccount {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<BankAccount> BANK_ACCOUNT_MAPPER = (rs, rowNum) -> (
            new BankAccount(
            rs.getInt("accountNumber"),
            rs.getString("name"),
            rs.getDouble("balance")));
    private final DataSource dataSource;

    public RepoBankAccount(JdbcTemplate jdbcTemplate, DataSource dataSource){
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    //Bruger query metoden til at hente data. Tager rowMapper og evt argumenter.
    public List<BankAccount> findAll(){
        String sql = "SELECT * FROM bankAccount";
        return jdbcTemplate.query(sql, BANK_ACCOUNT_MAPPER);
    }

    // Lavet med datasource, i stedet for JdbcTemplate
    public BankAccount findById(int accountNumber){
        String sql = "SELECT * FROM bankAccount where accountNumber=?";
        try (Connection con = dataSource.getConnection()){
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, accountNumber);
             ResultSet rs = ps.executeQuery();
             return new BankAccount(
                     rs.getInt("accountNumber"),
                     rs.getString("name"),
                     rs.getDouble("balance"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Bruger update metoden til at ændre i data. Bruger IKKE rowMapper. Tager kun argumenter.
    public void updateBalance(int accountNumber, double newBalance){
     String sql = "UPDATE bankAccount SET balance = ? where accountNumber=?";
     jdbcTemplate.update(sql, newBalance, accountNumber);
    }

    public void insertInto(BankAccount bankAccount){
        String sql = "INSERT INTO bankAccount(accountNumber, name, balance) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                bankAccount.getAccountNumber(),
                bankAccount.getName(),
                bankAccount.getBalance());
    }

    public void deleteById(int accountNumber){
        String sql = "DELETE FROM bankAccount WHERE accountNumber = ?";
        jdbcTemplate.update(sql, accountNumber);
    }
}
