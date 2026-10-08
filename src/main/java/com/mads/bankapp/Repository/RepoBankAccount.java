package com.mads.bankapp.Repository;

import com.mads.bankapp.Model.BankAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RepoBankAccount {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<BankAccount> BANK_ACCOUNT_MAPPER = (rs, rowNum) -> (
            new BankAccount(
            rs.getInt("accountNumber"),
            rs.getString("name"),
            rs.getDouble("balance")));

    public RepoBankAccount(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //Bruger query metoden til at hente data. Tager rowMapper og evt argumenter.
    public List<BankAccount> findAll(){
        String sql = "SELECT * FROM bankAccount";
        return jdbcTemplate.query(sql, BANK_ACCOUNT_MAPPER);
    }

    public BankAccount findById(int accountNumber){
        String sql = "SELECT * FROM bankAccount where accountNumber=?";
        return jdbcTemplate.queryForObject(sql, BANK_ACCOUNT_MAPPER, accountNumber);
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
