package com.hdel.web.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "GOV_EL_INFO")

@Builder
@AllArgsConstructor
public class GovElevatorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@Id
    @Column(length = 12, nullable = false, name="ELEVATOR_NO")
    private String elevatorNo;

    @Column(length = 2, nullable = true, name="RESULT_CODE")
    private String resultCode;

    @Column(length = 50, nullable = true,name="RESULT_MSG")
    private String resultMsg;

    @Column(length = 150, nullable = true, name="ADDRESS1")
    private String address1;

    @Column(length = 75, nullable = true, name="ADDRESS2")
    private String address2;

    @Column(length = 10, nullable = true, name="APPLC_BE_DT")
    private String applcBeDt;   //최종검사 유효기간 (시작일)

    @Column(length = 10, nullable = true, name="APPLC_EN_DT")
    private String applcEnDt;   //최종검사 유효기간 (종료일 )

    @Column(length = 100, nullable = true, name="RESULT_NM")
    private String resultNm;    //최종검사 판정 결과

    @Column(length = 10, nullable = true, name="AREA_NM")
    private String areaNm;

    @Column(length = 20, nullable = true, name="SIGUNGU_NM")
    private String sigunguNm;

    @Column(length = 3, nullable = true, name="BULD_MGT_NO1")
    private String buldMgtNo1;

    @Column(length = 5, nullable = true, name="BULD_MGT_NO2")
    private String buldMgtNo2;

    @Column(length = 75, nullable = true, name="BULD_NM")
    private String buldNm;

    @Column(length = 3, nullable = true, name="ELVTR_ASIGN_NO")
    private String elvtrAsignNo;

    @Column(length = 30, nullable = true, name="ELVTR_DIV_NM")
    private String elvtrDivNm;      //승강기 구분

    @Column(length = 30, nullable = true, name="ELVTR_FORM")
    private String elvtrForm;       //승강기 형식

    @Column(length = 30, nullable = true, name="ELVTR_DETAIL_FORM")
    private String elvtrDetailForm; //승강기 세부 형식

    @Column(length = 75, nullable = true, name="ELVTR_KIND_NM")
    private String elvtrKindNm;     //승강기 종류

    @Column(length = 10, nullable = true, name="ELVTR_STTS_NM")
    private String elvtrSttsNm;     //승강기 상태

    @Column(length = 10, nullable = true, name="FRST_INSTALLATION_DE")
    private String frstInstallationDe;

    @Column(length = 10, nullable = true, name="INSTALLATION_DE")
    private String installationDe;

    @Column(length = 15, nullable = true, name="INSTALLATION_PLACE")
    private String installationPlace;

    @Column(length = 10, nullable = true, name="LIVE_LOAD")
    private String liveLoad;        //적재하중

    @Column(length = 6, nullable = true, name="RATED_CAP")
    private String ratedCap;        //최대정원

    @Column(length = 15, nullable = true, name="SHUTTLE_SECTION")
    private String shuttleSection;

    @Column(length = 6, nullable = true, name="SHUTTLE_FLOOR_CNT")
    private String shuttleFloorCnt;     //운행층수

    @Column(length = 3, nullable = true, name="GROUND_FLOOR_CNT")
    private String groundFloorCnt;      //지상층수

    @Column(length = 3, nullable = true, name="UNDGRND_FLOOR_CNT")
    private String undgrndFloorCnt;     //지하층수

    @CreatedDate
    @Column(name="CREATE_DATE")
    private LocalDateTime createDate;

}
