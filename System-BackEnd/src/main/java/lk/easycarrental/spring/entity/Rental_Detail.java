package lk.easycarrental.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author : Danuja 6/15/21 12:10 AM
 * @version 1.0
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rental_Detail {
    @Id
    private String rentId;
    private String getOnDate;
    private String getOffDate;
    private int duration;
    private String status;

    @ManyToOne
    @JoinColumn(name = "custEmail",referencedColumnName = "custEmail")
    private Customer custEmail;
    @ManyToOne
    @JoinColumn(name = "regNumber",referencedColumnName = "regNumber")
    private Car regNumber;
    @ManyToOne
    @JoinColumn(name = "driverEmail",referencedColumnName = "driverEmail")
    private Driver driverEmail;
}
