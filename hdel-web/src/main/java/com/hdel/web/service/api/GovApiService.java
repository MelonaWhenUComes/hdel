package com.hdel.web.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdel.web.domain.GovElevatorInfo;
import com.hdel.web.domain.GovElevatorInfoRepository;
import com.hdel.web.dto.GovElevatorInfoDto;
import com.hdel.web.dto.api.GovElevatorInfoXmlDto;
import com.hdel.web.service.common.ApiHttpRequest;
import com.hdel.web.service.common.ConverterUtil;
import groovy.util.logging.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class GovApiService {
    public static final Logger logger = LoggerFactory.getLogger(GovApiService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    GovElevatorInfoRepository govElevatorInfoRepository;

    @Value("${app.api.Naver-News-URI}")
    private String NAVER_NEWS_API_URI;

    ApiHttpRequest apiHttpRequest;
    ConverterUtil converterUtil;
    public void getSelfCheck() {
        String serviceKey = "U%2Fu1aFs%2B%2FD5EDhaAqwTEBC2NoYbubcKBg3gw8UDWWCYXIa1NX0HKk9dcXf0rqoU7%2F%2FQxvhDh2FXk%2Bfhg7KXZSQ%3D%3D";
        String checkMonth = "202210";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int i = 0 ;
        HashMap<String, String> tempMap = new HashMap<>();
        tempMap.put(String.valueOf(i++), String.format("%07d",Integer.parseInt("8087856")));

        tempMap.put(String.valueOf(i++), String.format("%07d",Integer.parseInt("125876")));

        for(int j = 0 ; j < i ; j++) {
            tempMap.get("" + j);

            Map<String, String> requestHeaders = new HashMap<>();
            String apiURI = "http://apis.data.go.kr/openapi/service/ElevatorSelfCheckService/getSelfCheckList"
                    + "?serviceKey=" + serviceKey
                    + "&yyyymm=" + checkMonth
                    + "&elevator_no=" + tempMap.get("" + j);

            String responseBody = apiHttpRequest.get(apiURI,requestHeaders);
            System.out.println("**" + j + " ********************** " + "\n" + responseBody + "\n");
        }
    }

    public void getSelfCheckDupException() throws Exception {
        String serviceKey = "U%2Fu1aFs%2B%2FD5EDhaAqwTEBC2NoYbubcKBg3gw8UDWWCYXIa1NX0HKk9dcXf0rqoU7%2F%2FQxvhDh2FXk%2Bfhg7KXZSQ%3D%3D";
        String checkMonth = "202210";
        String readFile = "C:\\excelreadwrite\\readExpt.csv";
        String writeFile = "C:\\excelreadwrite\\writeExpt.xlsx";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int iKey = 0 ;
        HashMap<String, String> tempMap = new HashMap<>();

        //*************** START :EXCEL READ
        /******************
        FileInputStream file = new FileInputStream(readFile);

        Workbook workbook = null;

        if (readFile.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(file);
        } else if (readFile.endsWith("xls")) {
            workbook = new HSSFWorkbook(file);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        //XSSFWorkbook workbook = new XSSFWorkbook(file);

        int rowIndex=0;
        int columnIndex=0;

        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
        //행의 수
        int rows = sheet.getPhysicalNumberOfRows();
        for(rowIndex = 0; rowIndex<rows; rowIndex++) {
            //행을읽는다
            XSSFRow row=sheet.getRow(rowIndex);
            if(row != null) {
                int cells=row.getPhysicalNumberOfCells();
                for(columnIndex=0; columnIndex<=cells; columnIndex++){
                    //셀값을 읽는다
                    XSSFCell cell=row.getCell(columnIndex);
                    String value="";
                    //셀이 빈값일경우를 위한 널체크
                    if(cell==null){
                        continue;
                    }else{
                        //타입별로 내용 읽기
                        switch (cell.getCellType()){
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value=cell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                value=cell.getNumericCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value=cell.getStringCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_BLANK:
                                value=cell.getBooleanCellValue()+"";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value=cell.getErrorCellValue()+"";
                                break;
                        }
                    }
                    //System.out.println(rowindex+"번 행 : "+columnindex+"번 열 값은: "+value);
                    tempMap.put(String.valueOf(i++), String.format("%07d",Integer.parseInt(value)));
                }
            }
        }
         ***********/
        //*************** END :EXCEL READ

        // 1. CSV 방식으로 파일 Read(승강기 번호)  -> EXCEL 암호화로 불가
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        logger.debug("test logger !! ");

        for(int y = 1; y< list1.size() ; y++) {//Row 0 라인은 컬럼명
            List<String> line = list1.get(y);
            for(int x = 0; x<line.size(); x++) {    // Column - 0 컬럼 만 READ
                if(!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0)
                        tempMap.put(String.valueOf(iKey++), String.format("%07d", Integer.parseInt(line.get(x))));
                }

            }
        }

        //2. Read 데이터 토대로 api 호출 (get)
        for(int j = 0 ; j < iKey ; j++) {
            String elevatorNo = tempMap.get("" + j);

            //승상기별 API 호출
            Map<String, String> requestHeaders = new HashMap<>();
            String apiURI = "http://apis.data.go.kr/openapi/service/ElevatorSelfCheckService/getSelfCheckList"
                    + "?serviceKey=" + serviceKey
                    + "&yyyymm=" + checkMonth
                    + "&elevator_no=" + elevatorNo;

            String responseBody = apiHttpRequest.get(apiURI,requestHeaders);

            //timeout 0.7초
            Thread.sleep(700);

            HashMap<String, Object> map = converterUtil.jsonString2Map(converterUtil.xml2JsonString(responseBody));
            HashMap<String, Object> itemsHashMap = new HashMap<>();

            //3. API 받아온 데이터 정제
            logger.debug("**** elevatorNo : " + elevatorNo);

            List<HashMap<String, Object>> tempList = new ArrayList<>();
            Class itemClass = (((HashMap<String, Object>) ((HashMap<String, Object>)(((HashMap<String, Object>) map.get("response")).get("body"))).get("items")).get("item")).getClass();
            if(itemClass.getName().equals("java.util.LinkedHashMap")) {
                tempList.add((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>)(((HashMap<String, Object>) map.get("response")).get("body"))).get("items")).get("item"));
            }
            //Origin
            else {
                tempList = (List<HashMap<String, Object>>) ((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("items")).get("item");
            }

            //승강기 ID 별 제외 수량 합계
            int systemManualSendCnt = 0;
            int exceptionCnt = 0;
            String tempElevatorNo = "";

            //4. 받아온 데이터를 통하여 승강기 별 전송 제외 / 시스템으로 전송건수 가져옴
            for(HashMap<String, Object> tempMap1 : tempList) {
                tempElevatorNo = String.valueOf(tempMap1.get("elevatorNo"));
                //제외이며 점검일자가 22년도 10월 인 데이터
                if("제외".equals((String)tempMap1.get("selchkResultNm")) && checkMonth.equals((String.valueOf(tempMap1.get("selchkBeginDate"))).substring(0,6))) {
                    exceptionCnt ++;
                }

                //registDt : 2022-11-11 시스템 오류에 따라 11일 전송 여부 / selchkBeginDate(점검일자) : 2022-10-31 
                // 11월 11일에 매뉴얼 전송 하였고 말일자로 찍힘 
                if("20221111".equals(String.valueOf(tempMap1.get("registDt"))) && "20221031".equals(String.valueOf(tempMap1.get("selchkBeginDate")))) {
                    systemManualSendCnt ++;
                }
            }
            
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("elevatorNo", tempElevatorNo);
            resultMap.put("exceptionCnt", String.valueOf(exceptionCnt));
            resultMap.put("systemManualSendCnt", String.valueOf(systemManualSendCnt));

            logger.debug("elevatorNo : " + tempElevatorNo + " / exceptionCnt : " + String.valueOf(exceptionCnt) + " / systemManualSendCnt : " + String.valueOf(systemManualSendCnt));

            list.add(resultMap);
        }

        //5. 정제된 데이터를 Excel 파일 쓰기
        FileOutputStream fos = new FileOutputStream(writeFile);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("new");    // sheet 생성

        XSSFRow curRow;

        int row = list.size();    // list 크기
        try {
            curRow = sheet.createRow(0);    // 레이블 Row
            curRow.createCell(0).setCellValue("승강기 번호");    // row에 각 cell 저장
            curRow.createCell(1).setCellValue("승강기별 제외 숫자");
            curRow.createCell(2).setCellValue("시스템 전송 건수");

            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt + 1);    // row 생성
                curRow.createCell(0).setCellValue((String)(list.get(tempInt).get("elevatorNo")));    // row에 각 cell 저장
                curRow.createCell(1).setCellValue((String)(list.get(tempInt).get("exceptionCnt")));
                curRow.createCell(2).setCellValue((String)(list.get(tempInt).get("systemManualSendCnt")));
            }
            /****
            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt);    // row 생성
                curRow.createCell(0).setCellValue((String) (list.get(tempInt).get("elevatorNo")));    // row에 각 cell 저장
                curRow.createCell(1).setCellValue((String) (list.get(tempInt).get("exceptionCnt")));
                curRow.createCell(2).setCellValue((String) (list.get(tempInt).get("systemManualCnt")));
            }
            ****/
            workbook.write(fos);
        }catch (Exception e) {
            fos.close();
        } finally {
            fos.close();
        }

    }



    public void getInspectFromGov() throws Exception {
        String serviceKey = "U%2Fu1aFs%2B%2FD5EDhaAqwTEBC2NoYbubcKBg3gw8UDWWCYXIa1NX0HKk9dcXf0rqoU7%2F%2FQxvhDh2FXk%2Bfhg7KXZSQ%3D%3D";
        //String serviceKey = "OZmu7je2Hxr0quOBuwFfWaFAJVFvyZULDDqCQ6MpvkAMLrByrO2CRGKUnZMA3+CV1tT+ckM70uzRYqqun6dZjg==";
        String checkMonth = "202210";
        String readFile = "C:\\excelreadwrite\\readDetail_20240604.csv";
        String writeFile = "C:\\excelreadwrite\\writeDetail_20240604.xlsx";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int iKey = 0 ;
        HashMap<String, String> tempMap = new HashMap<>();

        // 1. CSV 방식으로 파일 Read(승강기 번호)  -> EXCEL 암호화로 불가
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        logger.info("test logger !! ");

        for(int y = 1; y< list1.size() ; y++) {//Row 0 라인은 컬럼명
            List<String> line = list1.get(y);
            for(int x = 0; x<line.size(); x++) {    // Column - 0 컬럼 만 READ
                if(!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0) {
                        try {
                            //tempMap.put(String.valueOf(iKey++), String.format("%07d", line.get(x)));
                            tempMap.put(String.valueOf(iKey++), line.get(x));
                        }catch (Exception e) {
                            tempMap.put(String.valueOf(iKey++), "0");
                        }
                    }
                }
            }
        }

        List<InspectDate> resultList = new ArrayList<>();
        //2. Read 데이터 토대로 api 호출 (get)
        for(int j = 0 ; j < iKey ; j++) {
            String elevatorNo = tempMap.get("" + j);

            //승상기별 API 호출
            Map<String, String> requestHeaders = new HashMap<>();
            String apiURI = //"http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN"
                    "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElvtrInspctInqireM"
                    + "?serviceKey=" + serviceKey
                    + "&elevator_no=" + elevatorNo;

            try {
                String responseBody = apiHttpRequest.get(apiURI,requestHeaders);
                //timeout 0.1초
                Thread.sleep(100);
                //String responseBody = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE.</resultMsg></header><body><items><item><applcBeDt>2023-09-24</applcBeDt><applcEnDt>2024-09-23</applcEnDt><inspctDt>2023-12-11</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>2023-10-18</applcBeDt><applcEnDt>2023-12-17</applcEnDt><inspctDt>2023-10-18</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>2022-09-24</applcBeDt><applcEnDt>2023-09-23</applcEnDt><inspctDt>2022-10-28</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>16</recptnNo></item><item><applcBeDt>2021-09-24</applcBeDt><applcEnDt>2022-09-23</applcEnDt><inspctDt>2021-10-20</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>28</recptnNo></item><item><applcBeDt>2020-09-24</applcBeDt><applcEnDt>2021-09-23</applcEnDt><inspctDt>2020-09-24</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>설치검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>7</recptnNo></item><item><applcBeDt>2020-05-28</applcBeDt><applcEnDt>2020-11-27</applcEnDt><inspctDt>2020-07-03</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>29</recptnNo></item><item><applcBeDt>2019-11-28</applcBeDt><applcEnDt>2020-05-27</applcEnDt><inspctDt>2019-12-12</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>84</recptnNo></item><item><applcBeDt>2019-05-28</applcBeDt><applcEnDt>2019-11-27</applcEnDt><inspctDt>2019-08-09</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>41</recptnNo></item><item><applcBeDt>2019-06-14</applcBeDt><applcEnDt>2019-08-13</applcEnDt><inspctDt>2019-06-14</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>41</recptnNo></item><item><applcBeDt>2018-09-06</applcBeDt><applcEnDt>2019-09-05</applcEnDt><inspctDt>2019-01-04</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>12</recptnNo></item><item><applcBeDt>2018-11-07</applcBeDt><applcEnDt>2019-01-06</applcEnDt><inspctDt>2018-11-07</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>12</recptnNo></item><item><applcBeDt>2017-09-06</applcBeDt><applcEnDt>2018-09-05</applcEnDt><inspctDt>2018-01-04</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정밀검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>34</recptnNo></item><item><applcBeDt>2017-09-06</applcBeDt><applcEnDt>2018-01-05</applcEnDt><inspctDt>2017-09-06</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정밀검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>34</recptnNo></item><item><applcBeDt>2016-08-29</applcBeDt><applcEnDt>2017-08-28</applcEnDt><inspctDt>2016-08-17</inspctDt><inspctInsttNm>한국승강기안전공단 서울북부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>99</recptnNo></item><item><applcBeDt>2015-08-29</applcBeDt><applcEnDt>2016-08-28</applcEnDt><inspctDt>2015-08-06</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>1</recptnNo></item><item><applcBeDt>2014-08-29</applcBeDt><applcEnDt>2015-08-28</applcEnDt><inspctDt>2014-12-01</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>차기안전검사</psexamYn><recptnNo>9</recptnNo></item><item><applcBeDt>2014-08-18</applcBeDt><applcEnDt>2014-12-17</applcEnDt><inspctDt>2014-08-18</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>9</recptnNo></item><item><applcBeDt>2013-08-29</applcBeDt><applcEnDt>2014-08-28</applcEnDt><inspctDt>2013-11-18</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>4</recptnNo></item><item><applcBeDt>2013-08-19</applcBeDt><applcEnDt>2013-11-18</applcEnDt><inspctDt>2013-08-19</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>4</recptnNo></item><item><applcBeDt>2012-08-29</applcBeDt><applcEnDt>2013-08-28</applcEnDt><inspctDt>2012-08-29</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>8</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>--</applcEnDt><inspctDt>2012-08-28</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>불합격</psexamYn><recptnNo>6</recptnNo></item><item><applcBeDt>2011-08-17</applcBeDt><applcEnDt>2012-08-16</applcEnDt><inspctDt>2011-08-23</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>2</recptnNo></item><item><applcBeDt>2010-08-17</applcBeDt><applcEnDt>2011-08-16</applcEnDt><inspctDt>2010-08-17</inspctDt><inspctInsttNm>한국승강기안전기술원 서울동부지사</inspctInsttNm><inspctKind>정밀검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>3</recptnNo></item><item><applcBeDt>2009-09-20</applcBeDt><applcEnDt>2010-09-19</applcEnDt><inspctDt>2009-11-16</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>10</recptnNo></item><item><applcBeDt>2009-09-15</applcBeDt><applcEnDt>2009-11-30</applcEnDt><inspctDt>2009-09-15</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>10</recptnNo></item><item><applcBeDt>2008-09-20</applcBeDt><applcEnDt>2009-09-19</applcEnDt><inspctDt>2008-09-18</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>21</recptnNo></item><item><applcBeDt>2007-09-20</applcBeDt><applcEnDt>2008-09-19</applcEnDt><inspctDt>2007-09-13</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>62</recptnNo></item><item><applcBeDt>2006-09-20</applcBeDt><applcEnDt>2007-09-19</applcEnDt><inspctDt>2006-09-13</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>11</recptnNo></item><item><applcBeDt>2005-09-20</applcBeDt><applcEnDt>2006-09-19</applcEnDt><inspctDt>2005-09-14</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>7</recptnNo></item><item><applcBeDt>2004-09-20</applcBeDt><applcEnDt>2005-09-19</applcEnDt><inspctDt>2004-10-22</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>--</applcEnDt><inspctDt>2004-09-13</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>2004-09-19</applcEnDt><inspctDt>2003-09-15</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>1</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>2003-09-19</applcEnDt><inspctDt>2002-10-01</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>4</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>2002-09-19</applcEnDt><inspctDt>2001-09-13</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>1</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>2001-09-19</applcEnDt><inspctDt>2000-11-17</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>--</applcEnDt><inspctDt>2000-09-19</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>13</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>2000-09-19</applcEnDt><inspctDt>1999-09-27</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>1</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>1999-09-19</applcEnDt><inspctDt>1998-08-24</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>연장검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>1</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>1998-09-19</applcEnDt><inspctDt>1997-09-11</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>연장검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>4</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>1997-11-19</applcEnDt><inspctDt>1996-11-08</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>합격</psexamYn><recptnNo>8510</recptnNo></item><item><applcBeDt>--</applcBeDt><applcEnDt>--</applcEnDt><inspctDt>1996-09-09</inspctDt><inspctInsttNm>한국승강기안전관리원 서울동부지원</inspctInsttNm><inspctKind>정기검사</inspctKind><psexamYn>조건부합격</psexamYn><recptnNo>7118</recptnNo></item></items></body></response>";
                Map<String, GovElevatorInfoXmlDto> result = new HashMap<>();
                try{
                    JAXBContext jaxbContext = JAXBContext.newInstance(GovElevatorInfoXmlDto.class);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                    GovElevatorInfoXmlDto apiResponse = (GovElevatorInfoXmlDto)unmarshaller.unmarshal(new StringReader(responseBody));
                    result.put("response",apiResponse);
                    GovElevatorInfoXmlDto.Body.Items items = apiResponse.getBody().getItems();

                    List<InspectDate> inspectList = new ArrayList<>();
                    // Dto 에 집어넣어 리스트에 담음
                    for(GovElevatorInfoXmlDto.Body.Items.Item item : items.getItem()){
                        if(item.getInspctKind().equals("정기검사")) {
                            InspectDate inspectDate = new InspectDate(elevatorNo, item.getInspctDt());
                            inspectList.add(inspectDate);
                        }
                        //resultMap.put("lastInspctDe", lastInspctDe);    //최종검사
                    }
                    //리스트 최종 검사일로 소팅해서 승강기 최종검사일만 가져옴
                    Collections.sort(inspectList, new DateComparator().reversed());
                    resultList.add(inspectList.get(0));

                }catch (JAXBException e){
                    e.printStackTrace();
                }

                //return "/";
                System.out.println("line cnt : " + (j +1));

            } catch (Exception e) {
                e.printStackTrace();
            }

            //if(j == 100) break;
        }

        //5. 정제된 데이터를 Excel 파일 쓰기
        FileOutputStream fos = new FileOutputStream(writeFile);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("new");    // sheet 생성

        XSSFRow curRow;

        int row = resultList.size();    // list 크기
        try {
            curRow = sheet.createRow(0);    // 레이블 Row
            curRow.createCell(0).setCellValue("승강기 번호");    // row에 각 cell 저장
            curRow.createCell(1).setCellValue("최종검사 일자");

            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt + 1);    // row 생성
                curRow.createCell(0).setCellValue((String)((resultList.get(tempInt)).elevatorNo));    // row에 각 cell 저장
                curRow.createCell(1).setCellValue((String)((resultList.get(tempInt)).inspectDate));
            }
            workbook.write(fos);
        }catch (Exception e) {
            fos.close();
        } finally {
            fos.close();
        }

    }


    class DateComparator implements Comparator<InspectDate> {
        @Override
        public int compare (InspectDate i1, InspectDate i2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = dateFormat.parse(i1.inspectDate);
                Date date2 = dateFormat.parse(i2.inspectDate);

                if(date1.after(date2)) return 1;
                else if(date1.before(date2)) return -1;
                else return 0;
            } catch (Exception e) {

            }
            return -9;
        }
    }
    class InspectDate {
        String inspectDate = "";
        String elevatorNo = "";

        public InspectDate(String elevatorNo, String inspectDate) {
            this.inspectDate = inspectDate;
            this.elevatorNo = elevatorNo;
        }
    }

    @Transactional
    public int insertGovElevatorInfoFromGovApi() throws Exception {
        int insertCnt = 0;

        String serviceKey = "U%2Fu1aFs%2B%2FD5EDhaAqwTEBC2NoYbubcKBg3gw8UDWWCYXIa1NX0HKk9dcXf0rqoU7%2F%2FQxvhDh2FXk%2Bfhg7KXZSQ%3D%3D";
        String readFile = "C:\\excelreadwrite\\readElId.csv";
        String apiUri = "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int iKey = 0;
        HashMap<String, String> tempMap = new HashMap<>();

        // 1. CSV 방식으로 파일 Read(승강기 번호)  -> EXCEL 암호화로 불가
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        for (int y = 1; y < list1.size(); y++) {//Row 0 라인은 컬럼명
            List<String> line = list1.get(y);
            for (int x = 0; x < line.size(); x++) {    // Column - 0 컬럼 만 READ
                if (!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0) {
                        String temp = line.get(x);
                        int tempInt = 0;
                        try {
                            tempInt = Integer.parseInt(line.get(x));
                        } catch (Exception e ) {
                            tempInt = 0;
                        }
                        tempMap.put(String.valueOf(iKey++), String.format("%07d", tempInt));
                    }
                }
            }
        }

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

                    /*
                    govElevatorInfoRepository.save(GovElevatorInfo.builder()
                            .elevatorNo("X000002")
                            .address1("용인시 수지구")
                            .address2("만현마을")
                            .build());
                    */

                    //내역 저장
                    govElevatorInfoRepository.save(govElevatorInfo);

                    insertCnt ++;
                }
                //break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return insertCnt;
    }

}
