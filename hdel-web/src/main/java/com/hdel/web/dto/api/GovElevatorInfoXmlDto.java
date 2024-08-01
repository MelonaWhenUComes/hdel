package com.hdel.web.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hdel.web.domain.GovElevatorInfo;
import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

//@Builder
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class GovElevatorInfoXmlDto {
    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name= "body")
    private Body body;

    @Getter
    @Setter
    @XmlRootElement(name = "header")
    private static class Header{
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    @XmlRootElement(name = "body")
    public static class Body{
        private Items items;
        private String numOfRows;
        private String pageNo;
        private String totalCount;

        @Getter
        @Setter
        @XmlRootElement(name = "items")
        public static class Items{
            private List<Item> item;

            @Getter
            @Setter
            @XmlRootElement(name="item")
            public static class Item{
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
                private String inspctDt;
                private String inspctKind;
            }
        }
    }
}
