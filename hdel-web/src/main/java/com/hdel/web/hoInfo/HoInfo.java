package com.hdel.web.hoInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
//@Table(name = "GOLDEN_RAW_TEST")
public class HoInfo {
    @Id
    @GeneratedValue
    Long id;

    @Column(name="PROJ_NO")
    private String projNo;

    @Column(name="REG_DT")
    private Date regDt;

    @Column(name="ACPT_CALL_NO")
    private String acptCallNo;

}
