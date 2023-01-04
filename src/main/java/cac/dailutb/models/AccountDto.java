package cac.dailutb.models;

public class AccountDto {
    private String userId;
    private String accountId;
    private String accountTypeId;

    private String amount;

    private String transferCbu;
    private String transferAmount;

    public AccountDto() {
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountTypeId() {
        return accountTypeId;
    }

    public String getAmount() {
        return amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getTransferCbu() {
        return transferCbu;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setTransferCbu(String transferCbu) {
        this.transferCbu = transferCbu;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }
}
