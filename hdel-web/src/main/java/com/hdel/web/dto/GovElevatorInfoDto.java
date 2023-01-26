package com.hdel.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hdel.web.domain.GovElevatorInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown =true)
public class GovElevatorInfoDto {
    private String elevatorNo;
    private String address1;
    private String address2;
    private String applcBeDt;
    private String applcEnDt;
    private String resultNm;
    private String areaNm;
    private String sigunguNm;
    private String buldMgtNo1;
    private String buldMgtNo2;
    private String buldNm;
    private String elvtrAsignNo;
    private String elvtrDivNm;
    private String elvtrForm;
    private String elvtrDetailForm;
    private String elvtrKindNm;
    private String elvtrSttsNm;
    private String frstInstallationDe;
    private String installationDe;
    private String installationPlace;
    private String liveLoad;
    private String ratedCap;
    private String shuttleSection;
    private String shuttleFloorCnt;
    private String groundFloorCnt;
    private String undgrndFloorCnt;
    private LocalDateTime createDate;

    @Builder
    public GovElevatorInfoDto(String elevatorNo, String address1, String address2, String applcBeDt, String applcEnDt
        , String resultNm, String areaNm, String sigunguNm, String buldMgtNo1, String buldMgtNo2
        , String buldNm, String elvtrAsignNo, String elvtrDivNm, String elvtrForm, String elvtrDetailForm
        , String elvtrKindNm, String elvtrSttsNm, String frstInstallationDe, String installationDe, String installationPlace
        , String liveLoad, String ratedCap, String shuttleSection, String shuttleFloorCnt, String groundFloorCnt
        , String undgrndFloorCnt, LocalDateTime createDate
        ) {
        this.elevatorNo = elevatorNo;
        this.address1 = address1;
        this.address2 = address2;
        this.applcBeDt = applcBeDt;
        this.applcEnDt = applcEnDt;
        this.resultNm = resultNm;
        this.areaNm = areaNm;
        this.sigunguNm = sigunguNm;
        this.buldMgtNo1 = buldMgtNo1;
        this.buldMgtNo2 = buldMgtNo2;
        this.buldNm = buldNm;
        this.elvtrAsignNo = elvtrAsignNo;
        this.elvtrDivNm = elvtrDivNm;
        this.elvtrForm = elvtrForm;
        this.elvtrDetailForm = elvtrDetailForm;
        this.elvtrKindNm = elvtrKindNm;
        this.elvtrSttsNm = elvtrSttsNm;
        this.frstInstallationDe = frstInstallationDe;
        this.installationDe = installationDe;
        this.installationPlace = installationPlace;
        this.liveLoad = liveLoad;
        this.ratedCap = ratedCap;
        this.shuttleSection = shuttleSection;
        this.shuttleFloorCnt = shuttleFloorCnt;
        this.groundFloorCnt = groundFloorCnt;
        this.undgrndFloorCnt = undgrndFloorCnt;
        this.createDate = createDate;
    }


    public GovElevatorInfo toEntity() {

        return GovElevatorInfo.builder()
                .address1(address1)
                .address2(address2)
                .elevatorNo(elevatorNo)
                .applcBeDt(applcBeDt)
                .applcEnDt(applcEnDt)
                .resultNm(resultNm)
                .areaNm(areaNm)
                .sigunguNm(sigunguNm)
                .buldMgtNo1(buldMgtNo1)
                .buldMgtNo2(buldMgtNo2)
                .buldNm(buldNm)
                .elvtrAsignNo(elvtrAsignNo)
                .elvtrDivNm(elvtrDivNm)
                .elvtrForm(elvtrForm)
                .elvtrDetailForm(elvtrDetailForm)
                .elvtrKindNm(elvtrKindNm)
                .elvtrSttsNm(elvtrSttsNm)
                .frstInstallationDe(frstInstallationDe)
                .installationDe(installationDe)
                .installationPlace(installationPlace)
                .liveLoad(liveLoad)
                .ratedCap(ratedCap)
                .shuttleSection(shuttleSection)
                .shuttleFloorCnt(shuttleFloorCnt)
                .groundFloorCnt(groundFloorCnt)
                .undgrndFloorCnt(undgrndFloorCnt)
                .createDate(createDate)

                .build();
    }


}
