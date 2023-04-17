package com.hdel.web.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdel.web.domain.GovElevatorInfo;
import com.hdel.web.domain.publicAddr.PublicAddress;
import com.hdel.web.domain.publicAddr.PublicAddressRepository;
import com.hdel.web.domain.user.Member;
import com.hdel.web.domain.user.MemberRepository;
import com.hdel.web.dto.GovElevatorInfoDto;
import com.hdel.web.service.common.ApiHttpRequest;
import com.hdel.web.service.common.ConverterUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("local")
class Test {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PublicAddressRepository publicAddressRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    ApiHttpRequest apiHttpRequest;
    ConverterUtil converterUtil;

    Logger log = (Logger) LoggerFactory.getLogger(Test.class);

    @DisplayName("1. GCEN 으로부터 GIS 데이터 가져오기 테스트!")
    @org.junit.jupiter.api.Test
    void getGisFromGcen(){
        {
            int insertCnt = 0;
            int pageCnt = 1;
            int count = 10;
            //String addr = "서울특별시 관악구 신림동 458-25";
            String addr = "#N/A";
            String encodeResult = "";
            try {
                encodeResult = URLEncoder.encode(addr, "UTF-8");
            }catch (Exception e) {

            }

            String apiUri = "http://hgis.hdel.co.kr:8002/gcenmap/wservice/addr"
                    + "?keyword=" + encodeResult
                    + "&page=" + pageCnt
                    + "&count=" + count;

            List<HashMap<String, Object>> list = new ArrayList<>();

            int iKey = 0;
            HashMap<String, String> tempMap = new HashMap<>();

            Map<String, String> requestHeaders = new HashMap<>();
            String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

            ConverterUtil converterUtil = new ConverterUtil();

            try {
                //timeout 0.5초
                //Thread.sleep(500);

                HashMap<String, Object> map = converterUtil.jsonString2Map(responseBody);

                String lon = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lon"));
                String lat = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lat"));

                int xxxx = 1;
            }
            catch (Exception e) {

            }

            // 1. CSV 방식으로 파일 Read -> EXCEL(xlsx) 암호화로 불가
            /***
            ConverterUtil converterUtil = new ConverterUtil();
            List<List<String>> list1 = ConverterUtil.readToList(readFile);

            for (int y = 1; y < list1.size(); y++) {//Row 0 라인은 컬럼명
                List<String> line = list1.get(y);
                for (int x = 0; x < line.size(); x++) {    // Column - 0 컬럼 만 READ
                    if (!line.get(0).equals("") && line.get(0) != null) {
                        if (x == 0)
                            tempMap.put(String.valueOf(iKey++), String.format("%07d", Integer.parseInt(line.get(x))));
                    }
                }
            }
            ***/
            /***
            //2. Read 데이터 토대로 api 호출 (get)
            for (int j = 0; j < iKey; j++) {
                String elevatorNo = tempMap.get("" + j);

                //승상기별 API 호출
                Map<String, String> requestHeaders = new HashMap<>();
                apiUri = "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN"
                        + "?serviceKey=" + serviceKey
                        + "&elevator_no=" + elevatorNo;
                try {
                    String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                    //timeout 0.5초
                    Thread.sleep(500);

                    HashMap<String, Object> map = converterUtil.jsonString2Map(converterUtil.xml2JsonString(responseBody));
                    HashMap<String, Object> itemsHashMap = new HashMap<>();

                    //3. API 받아온 데이터 정제
                    List<HashMap<String, Object>> tempList = new ArrayList<>();
                    Class itemClass = ((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item")).getClass();
                    if (itemClass.getName().equals("java.util.LinkedHashMap")) {
                        tempList.add((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item"));
                    }
                    //Origin
                    else {
                        tempList = (List<HashMap<String, Object>>) (HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item");
                    }

                    //4. 받아온 데이터를 저장
                    for (HashMap<String, Object> tempMap1 : tempList) {
                        ObjectMapper mapper = new ObjectMapper();
                        GovElevatorInfoDto govElevatorInfoDto = mapper.convertValue(tempMap1, GovElevatorInfoDto.class);

                        //Dto to Entity
                        GovElevatorInfo govElevatorInfo = govElevatorInfoDto.toEntity();


                    //govElevatorInfoRepository.save(GovElevatorInfo.builder()
                    //        .elevatorNo("X000002")
                    //        .address1("용인시 수지구")
                    //        .address2("만현마을")
                    //        .build());


                        //내역 저장
                        //govElevatorInfoRepository.save(govElevatorInfo);

                     //   insertCnt ++;
                    }
                    //break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
             */
        }
        //assertThat(memberEntity.getUserId()).isEqualTo(savedMember.getUserId());
    }

    @DisplayName("2. CSV 읽어서 GCEN 으로부터 GIS 데이터 가져와서 쓰기")
    @org.junit.jupiter.api.Test
    void getGisFromGcenAtList() throws FileNotFoundException {
        int insertCnt = 0;
        int pageCnt = 1;
        int count = 10;
        HashMap<String, String> tempMap = new HashMap<>();
        String readFile = "C:\\ycchang\\01. 업무\\02. 업무 현황\\2023년도\\19. GCEN좌표변환\\HRTSListConvertExcel.csv";
        String writeFile = "C:\\ycchang\\01. 업무\\02. 업무 현황\\2023년도\\19. GCEN좌표변환\\HRTSListConvertExcelGISWrite.csv";

        // 1. CSV 방식으로 파일 Read -> EXCEL(xlsx) 암호화로 불가
         ConverterUtil converterUtil = new ConverterUtil();
         List<List<String>> list1 = ConverterUtil.readToList(readFile);

        List<GisMap> gislist = new ArrayList<>();


        for (int y = 1; y < list1.size(); y++) {//Row 0 라인은 컬럼명
            //for (int y = 1; y < 10; y++) {//Row 0 라인은 컬럼명
            GisMap gisMap = new GisMap();

            List<String> line = list1.get(y);
            GisMap paramGisMap = new GisMap(line.get(0), line.get(1), line.get(2), "", "", line.get(5)
                    , "", "", line.get(8), "", ""
            );

            gisMap.setProjNo(paramGisMap.getProjNo());
            gisMap.setHoNo(paramGisMap.getHoNo());
            gisMap.setCommonAddress(paramGisMap.getCommonAddress());
            gisMap.setRealAddress(paramGisMap.getRealAddress());
            gisMap.setPublicAddress(paramGisMap.getPublicAddress());

            //공통 db get GIS
            if (!paramGisMap.getCommonAddress().equals("") && paramGisMap.getCommonAddress() != null && paramGisMap.getCommonAddress().length() > 4) { //5자 이상만 ..
                String address = paramGisMap.getCommonAddress();
                try {
                    String encodeAddress = URLEncoder.encode(address, "UTF-8");
                    String apiUri = "http://hgis.hdel.co.kr:8002/gcenmap/wservice/addr"
                            + "?keyword=" + encodeAddress
                            + "&page=" + pageCnt
                            + "&count=" + count;

                    Map<String, String> requestHeaders = new HashMap<>();

                    String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                    HashMap<String, Object> map = new HashMap<>();

                    map = converterUtil.jsonString2Map(responseBody);
                    if (map.size() > 0) {
                        String lon = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lon"));
                        String lat = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lat"));

                        gisMap.setCommonLon(lon);
                        gisMap.setCommonLat(lat);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Get 현장주소 GIS
            if (!paramGisMap.getRealAddress().equals("") && paramGisMap.getRealAddress() != null && paramGisMap.getRealAddress().length() > 4) { //5자 이상만 ..
                String address = paramGisMap.getRealAddress();
                try {
                    String encodeAddress = URLEncoder.encode(address, "UTF-8");
                    String apiUri = "http://hgis.hdel.co.kr:8002/gcenmap/wservice/addr"
                            + "?keyword=" + encodeAddress
                            + "&page=" + pageCnt
                            + "&count=" + count;

                    Map<String, String> requestHeaders = new HashMap<>();

                    String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                    HashMap<String, Object> map = new HashMap<>();

                    map = converterUtil.jsonString2Map(responseBody);
                    if (map.size() > 0) {
                        String lon = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lon"));
                        String lat = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lat"));

                        gisMap.setRealLon(lon);
                        gisMap.setRealLat(lat);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Get 공단주소 GIS
            if (!paramGisMap.getPublicAddress().equals("") && paramGisMap.getPublicAddress() != null && paramGisMap.getPublicAddress().length() > 4) { //5자 이상만 ..
                String address = paramGisMap.getPublicAddress();
                try {
                    String encodeAddress = URLEncoder.encode(address, "UTF-8");
                    String apiUri = "http://hgis.hdel.co.kr:8002/gcenmap/wservice/addr"
                            + "?keyword=" + encodeAddress
                            + "&page=" + pageCnt
                            + "&count=" + count;

                    Map<String, String> requestHeaders = new HashMap<>();

                    String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                    HashMap<String, Object> map = new HashMap<>();

                    map = converterUtil.jsonString2Map(responseBody);
                    if (map.size() > 0) {
                        String lon = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lon"));
                        String lat = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lat"));

                        gisMap.setPublicLon(lon);
                        gisMap.setPublicLat(lat);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //List 에 add
            gislist.add(gisMap);

            //엑셀에 쓰기
            //Excel 파일 쓰기
            FileOutputStream fos = new FileOutputStream(writeFile);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("new");    // sheet 생성

            XSSFRow curRow;

            int row = gislist.size();    // list 크기
            try {
                curRow = sheet.createRow(0);    // 레이블 Row
                curRow.createCell(0).setCellValue("원Proj. NO");    // row에 각 cell 저장
                curRow.createCell(1).setCellValue("호기번호");
                curRow.createCell(2).setCellValue("공통DB 주소");
                curRow.createCell(3).setCellValue("공통DB LON");
                curRow.createCell(4).setCellValue("공통DB LAT");
                curRow.createCell(5).setCellValue("현장주소");
                curRow.createCell(6).setCellValue("현장주소 LON");
                curRow.createCell(7).setCellValue("현장주소 LAT");
                curRow.createCell(8).setCellValue("공단주소");
                curRow.createCell(9).setCellValue("공단주소 LON");
                curRow.createCell(10).setCellValue("공단주소 LAT");

                for (int tempInt = 0; tempInt < row; tempInt++) {
                    curRow = sheet.createRow(tempInt + 1);    // row 생성
                    curRow.createCell(0).setCellValue((String) (gislist.get(tempInt).getProjNo()));    // row에 각 cell 저장
                    curRow.createCell(1).setCellValue((String) (gislist.get(tempInt).getHoNo()));
                    curRow.createCell(2).setCellValue((String) (gislist.get(tempInt).getCommonAddress()));
                    curRow.createCell(3).setCellValue((String) (gislist.get(tempInt).getCommonLon()));
                    curRow.createCell(4).setCellValue((String) (gislist.get(tempInt).getCommonLat()));
                    curRow.createCell(5).setCellValue((String) (gislist.get(tempInt).getRealAddress()));
                    curRow.createCell(6).setCellValue((String) (gislist.get(tempInt).getRealLon()));
                    curRow.createCell(7).setCellValue((String) (gislist.get(tempInt).getRealLat()));
                    curRow.createCell(8).setCellValue((String) (gislist.get(tempInt).getPublicAddress()));
                    curRow.createCell(9).setCellValue((String) (gislist.get(tempInt).getPublicLon()));
                    curRow.createCell(10).setCellValue((String) (gislist.get(tempInt).getPublicLat()));
                }

                workbook.write(fos);
                fos.close();
            }catch (Exception e) {
                //fos.close();
            } finally {
                //fos.close();
            }

            if(y % 100 == 0) log.debug("***************READ ROW : " + y);
        }
        try {
            //fos.close();
        }catch (Exception e) {

        }

/***
         //엑셀에 쓰기
         //Excel 파일 쓰기
         FileOutputStream fos = new FileOutputStream(writeFile);

         XSSFWorkbook workbook = new XSSFWorkbook();
         XSSFSheet sheet = workbook.createSheet("new");    // sheet 생성

         XSSFRow curRow;

         int row = gislist.size();    // list 크기
         try {
             curRow = sheet.createRow(0);    // 레이블 Row
             curRow.createCell(0).setCellValue("원Proj. NO");    // row에 각 cell 저장
             curRow.createCell(1).setCellValue("호기번호");
             curRow.createCell(2).setCellValue("공통DB 주소");
             curRow.createCell(3).setCellValue("공통DB LON");
             curRow.createCell(4).setCellValue("공통DB LAT");
             curRow.createCell(5).setCellValue("현장주소");
             curRow.createCell(6).setCellValue("현장주소 LON");
             curRow.createCell(7).setCellValue("현장주소 LAT");
             curRow.createCell(8).setCellValue("공단주소");
             curRow.createCell(9).setCellValue("공단주소 LON");
             curRow.createCell(10).setCellValue("공단주소 LAT");

             for (int tempInt = 0; tempInt < row; tempInt++) {
                 curRow = sheet.createRow(tempInt + 1);    // row 생성
                 curRow.createCell(0).setCellValue((String) (gislist.get(tempInt).getProjNo()));    // row에 각 cell 저장
                 curRow.createCell(1).setCellValue((String) (gislist.get(tempInt).getHoNo()));
                 curRow.createCell(2).setCellValue((String) (gislist.get(tempInt).getCommonAddress()));
                 curRow.createCell(3).setCellValue((String) (gislist.get(tempInt).getCommonLon()));
                 curRow.createCell(4).setCellValue((String) (gislist.get(tempInt).getCommonLat()));
                 curRow.createCell(5).setCellValue((String) (gislist.get(tempInt).getRealAddress()));
                 curRow.createCell(6).setCellValue((String) (gislist.get(tempInt).getRealLon()));
                 curRow.createCell(7).setCellValue((String) (gislist.get(tempInt).getRealLat()));
                 curRow.createCell(8).setCellValue((String) (gislist.get(tempInt).getCenterAddress()));
                 curRow.createCell(9).setCellValue((String) (gislist.get(tempInt).getCenterLon()));
                 curRow.createCell(10).setCellValue((String) (gislist.get(tempInt).getCenterLat()));
             }

             workbook.write(fos);
             fos.close();
         }catch (Exception e) {
             //fos.close();
         } finally {
             //fos.close();
         }
***/

        /**

         //3. API 받아온 데이터 정제
         List<HashMap<String, Object>> tempList = new ArrayList<>();
         Class itemClass = ((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item")).getClass();
         if (itemClass.getName().equals("java.util.LinkedHashMap")) {
         tempList.add((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item"));
         }
         //Origin
         else {
         tempList = (List<HashMap<String, Object>>) (HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item");
         }

         //4. 받아온 데이터를 저장
         for (HashMap<String, Object> tempMap1 : tempList) {
         ObjectMapper mapper = new ObjectMapper();
         GovElevatorInfoDto govElevatorInfoDto = mapper.convertValue(tempMap1, GovElevatorInfoDto.class);

         //Dto to Entity
         GovElevatorInfo govElevatorInfo = govElevatorInfoDto.toEntity();


         //govElevatorInfoRepository.save(GovElevatorInfo.builder()
         //        .elevatorNo("X000002")
         //        .address1("용인시 수지구")
         //        .address2("만현마을")
         //        .build());


         //내역 저장
         //govElevatorInfoRepository.save(govElevatorInfo);

         //   insertCnt ++;
         }
         //break;
         } catch (Exception e) {
         e.printStackTrace();
         }

         }
         */
    //assertThat(memberEntity.getUserId()).isEqualTo(savedMember.getUserId());
    }

    @DisplayName("3. 공단 데이터 -> gcen 좌표 변환")
    @org.junit.jupiter.api.Test
    void getGisFromGcenOnlyCenterAtList() throws FileNotFoundException {
        int pageCnt = 1;
        int count = 10;
        String readFile = "C:\\ycchang\\01. 업무\\02. 업무 현황\\2023년도\\19. GCEN좌표변환\\HRTSListConvertExcel.csv";

        // 1. CSV 방식으로 파일 Read -> EXCEL(xlsx) 암호화로 불가
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        List<GisMap> gislist = new ArrayList<>();

        for (int y = 1; y < list1.size(); y++) {//Row 0 라인은 컬럼명
            GisMap gisMap = new GisMap();

            List<String> line = list1.get(y);
            GisMap paramGisMap = new GisMap(line.get(0), line.get(1), line.get(2), "", "", line.get(5)
                    , "", "", line.get(8), "", ""
            );

            gisMap.setProjNo(paramGisMap.getProjNo());
            gisMap.setHoNo(paramGisMap.getHoNo());
            gisMap.setCommonAddress(paramGisMap.getCommonAddress());
            gisMap.setRealAddress(paramGisMap.getRealAddress());
            gisMap.setPublicAddress(paramGisMap.getPublicAddress());

            // Get 공단주소 GIS
            if (!paramGisMap.getPublicAddress().equals("") && paramGisMap.getPublicAddress() != null && paramGisMap.getPublicAddress().length() > 4) { //5자 이상만 ..
                String address = paramGisMap.getPublicAddress();
                try {
                    String encodeAddress = URLEncoder.encode(address, "UTF-8");
                    String apiUri = "http://hgis.hdel.co.kr:8002/gcenmap/wservice/addr"
                            + "?keyword=" + encodeAddress
                            + "&page=" + pageCnt
                            + "&count=" + count;

                    Map<String, String> requestHeaders = new HashMap<>();

                    String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                    HashMap<String, Object> map = new HashMap<>();

                    map = converterUtil.jsonString2Map(responseBody);
                    if (map.size() > 0) {
                        String lon = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lon"));
                        String lat = String.valueOf(((ArrayList<Map>) map.get("result")).get(0).get("lat"));

                        gisMap.setPublicLon(lon);
                        gisMap.setPublicLat(lat);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //List 에 add
            gislist.add(gisMap);
            PublicAddress publicAddress = PublicAddress.builder()
                    .projNo(gisMap.getProjNo())
                    .hoNo(gisMap.getHoNo())
                    .publicAddr(gisMap.getPublicAddress())
                    .publicLat(gisMap.getPublicLat() == null ? 0.0 : Double.parseDouble(gisMap.getPublicLat()))
                    .publicLon(gisMap.getPublicLon() == null ? 0.0 : Double.parseDouble(gisMap.getPublicLon()))
                            .build();

            // 테이블 인서트
            PublicAddress savePublicAddress = publicAddressRepository.save(publicAddress);
            //assertThat(publicAddressRepository.getUserId()).isEqualTo(savedMember.getUserId());

            if(y % 100 == 0) log.debug("***************READ ROW : " + y);
        }
    }

}


class GisMap {
    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getHoNo() {
        return hoNo;
    }

    public void setHoNo(String hoNo) {
        this.hoNo = hoNo;
    }

    public String getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(String commonAddress) {
        this.commonAddress = commonAddress;
    }

    public String getCommonLat() {
        return commonLat;
    }

    public void setCommonLat(String commonLat) {
        this.commonLat = commonLat;
    }

    public String getCommonLon() {
        return commonLon;
    }

    public void setCommonLon(String commonLon) {
        this.commonLon = commonLon;
    }

    public String getRealAddress() {
        return realAddress;
    }

    public void setRealAddress(String realAddress) {
        this.realAddress = realAddress;
    }

    public String getRealLat() {
        return realLat;
    }

    public void setRealLat(String realLat) {
        this.realLat = realLat;
    }

    public String getRealLon() {
        return realLon;
    }

    public void setRealLon(String realLon) {
        this.realLon = realLon;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getPublicLat() {
        return publicLat;
    }

    public void setPublicLat(String publicLat) {
        this.publicLat = publicLat;
    }

    public String getPublicLon() {
        return publicLon;
    }

    public void setPublicLon(String publicLon) {
        this.publicLon = publicLon;
    }

    private String projNo;
    private String hoNo;
    private String commonAddress;
    private String commonLat;
    private String commonLon;
    private String realAddress;
    private String realLat;
    private String realLon;
    private String publicAddress;
    private String publicLat;
    private String publicLon;

    public GisMap() {}

    public GisMap(String projNo, String hoNo, String commonAddress, String commonLat, String commonLon, String realAddress
    , String realLat, String realLon, String publicAddress, String publicLat, String publicLon) {
        this.projNo = projNo;
        this.hoNo = hoNo;
        this.commonAddress = commonAddress;
        this.commonLat = commonLat;
        this.commonLon = commonLon;
        this.realAddress = realAddress;
        this.realLat = realLat;
        this.realLon = realLon;
        this.publicAddress = publicAddress;
        this.publicLat = publicLat;
        this.publicLon = publicLon;
    }

}
