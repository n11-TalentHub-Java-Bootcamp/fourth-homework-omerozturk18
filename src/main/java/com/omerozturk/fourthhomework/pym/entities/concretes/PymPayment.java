package com.omerozturk.fourthhomework.pym.entities.concretes;

import com.omerozturk.fourthhomework.gen.utilities.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PYM_PAYMENT")
@Data
public class PymPayment implements BaseEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "PYM_PAYMENT_ID_SEQ")
    private Long id;
    private String explanation;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private Long dbtDebtId;
    private Long usrUserId;
}
