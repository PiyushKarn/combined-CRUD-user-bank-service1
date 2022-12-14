package abc.first.Domain;


import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name="accounts")

public class BankDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long userId;
    @Column
    private String bankName;
    @Column
    private String accountNumber;
    @Column
    private String ifscCode;
    @Column(columnDefinition = "TEXT")
    private String additionalDetails;

//    @OneToOne(mappedBy = "bank", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private UserDomain user;
    public BankDomain()
    {}
    public BankDomain(Long id, Long userId, String bankName,String accountNumber, String ifscCode, String additionalDetails)
    {
        this.id = id;
        this.userId =userId;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.additionalDetails = additionalDetails;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }
}
