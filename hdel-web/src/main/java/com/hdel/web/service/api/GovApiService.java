package com.hdel.web.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdel.web.domain.GovElevatorInfo;
import com.hdel.web.domain.GovElevatorInfoRepository;
import com.hdel.web.dto.GovElevatorInfoDto;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //?????? ???
        int rows = sheet.getPhysicalNumberOfRows();
        for(rowIndex = 0; rowIndex<rows; rowIndex++) {
            //???????????????
            XSSFRow row=sheet.getRow(rowIndex);
            if(row != null) {
                int cells=row.getPhysicalNumberOfCells();
                for(columnIndex=0; columnIndex<=cells; columnIndex++){
                    //????????? ?????????
                    XSSFCell cell=row.getCell(columnIndex);
                    String value="";
                    //?????? ?????????????????? ?????? ?????????
                    if(cell==null){
                        continue;
                    }else{
                        //???????????? ?????? ??????
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
                    //System.out.println(rowindex+"??? ??? : "+columnindex+"??? ??? ??????: "+value);
                    tempMap.put(String.valueOf(i++), String.format("%07d",Integer.parseInt(value)));
                }
            }
        }
         ***********/
        //*************** END :EXCEL READ

        // 1. CSV ???????????? ?????? Read(????????? ??????)  -> EXCEL ???????????? ??????
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        logger.debug("test logger !! ");

        for(int y = 1; y< list1.size() ; y++) {//Row 0 ????????? ?????????
            List<String> line = list1.get(y);
            for(int x = 0; x<line.size(); x++) {    // Column - 0 ?????? ??? READ
                if(!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0)
                        tempMap.put(String.valueOf(iKey++), String.format("%07d", Integer.parseInt(line.get(x))));
                }

            }
        }

        //2. Read ????????? ????????? api ?????? (get)
        for(int j = 0 ; j < iKey ; j++) {
            String elevatorNo = tempMap.get("" + j);

            //???????????? API ??????
            Map<String, String> requestHeaders = new HashMap<>();
            String apiURI = "http://apis.data.go.kr/openapi/service/ElevatorSelfCheckService/getSelfCheckList"
                    + "?serviceKey=" + serviceKey
                    + "&yyyymm=" + checkMonth
                    + "&elevator_no=" + elevatorNo;

            String responseBody = apiHttpRequest.get(apiURI,requestHeaders);

            //timeout 0.7???
            Thread.sleep(700);

            HashMap<String, Object> map = converterUtil.jsonString2Map(converterUtil.xml2JsonString(responseBody));
            HashMap<String, Object> itemsHashMap = new HashMap<>();

            //3. API ????????? ????????? ??????
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

            //????????? ID ??? ?????? ?????? ??????
            int systemManualSendCnt = 0;
            int exceptionCnt = 0;
            String tempElevatorNo = "";

            //4. ????????? ???????????? ????????? ????????? ??? ?????? ?????? / ??????????????? ???????????? ?????????
            for(HashMap<String, Object> tempMap1 : tempList) {
                tempElevatorNo = String.valueOf(tempMap1.get("elevatorNo"));
                //???????????? ??????????????? 22?????? 10??? ??? ?????????
                if("??????".equals((String)tempMap1.get("selchkResultNm")) && checkMonth.equals((String.valueOf(tempMap1.get("selchkBeginDate"))).substring(0,6))) {
                    exceptionCnt ++;
                }

                //registDt : 2022-11-11 ????????? ????????? ?????? 11??? ?????? ?????? / selchkBeginDate(????????????) : 2022-10-31 
                // 11??? 11?????? ????????? ?????? ????????? ???????????? ?????? 
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

        //5. ????????? ???????????? Excel ?????? ??????
        FileOutputStream fos = new FileOutputStream(writeFile);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("new");    // sheet ??????

        XSSFRow curRow;

        int row = list.size();    // list ??????
        try {
            curRow = sheet.createRow(0);    // ????????? Row
            curRow.createCell(0).setCellValue("????????? ??????");    // row??? ??? cell ??????
            curRow.createCell(1).setCellValue("???????????? ?????? ??????");
            curRow.createCell(2).setCellValue("????????? ?????? ??????");

            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt + 1);    // row ??????
                curRow.createCell(0).setCellValue((String)(list.get(tempInt).get("elevatorNo")));    // row??? ??? cell ??????
                curRow.createCell(1).setCellValue((String)(list.get(tempInt).get("exceptionCnt")));
                curRow.createCell(2).setCellValue((String)(list.get(tempInt).get("systemManualSendCnt")));
            }
            /****
            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt);    // row ??????
                curRow.createCell(0).setCellValue((String) (list.get(tempInt).get("elevatorNo")));    // row??? ??? cell ??????
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
        String checkMonth = "202210";
        String readFile = "C:\\excelreadwrite\\readDetail_20221209.csv";
        String writeFile = "C:\\excelreadwrite\\writeDetail_20221209.xlsx";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int iKey = 0 ;
        HashMap<String, String> tempMap = new HashMap<>();

        // 1. CSV ???????????? ?????? Read(????????? ??????)  -> EXCEL ???????????? ??????
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        logger.info("test logger !! ");

        for(int y = 1; y< list1.size() ; y++) {//Row 0 ????????? ?????????
            List<String> line = list1.get(y);
            for(int x = 0; x<line.size(); x++) {    // Column - 0 ?????? ??? READ
                if(!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0)
                        tempMap.put(String.valueOf(iKey++), String.format("%07d", Integer.parseInt(line.get(x))));
                }

            }
        }

        //2. Read ????????? ????????? api ?????? (get)
        for(int j = 0 ; j < iKey ; j++) {
            String elevatorNo = tempMap.get("" + j);

            //???????????? API ??????
            Map<String, String> requestHeaders = new HashMap<>();
            String apiURI = "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN"
                    + "?serviceKey=" + serviceKey
                    + "&elevator_no=" + elevatorNo;
            try {
                String responseBody = apiHttpRequest.get(apiURI,requestHeaders);

                //timeout 0.5???
                Thread.sleep(500);

                HashMap<String, Object> map = converterUtil.jsonString2Map(converterUtil.xml2JsonString(responseBody));
                HashMap<String, Object> itemsHashMap = new HashMap<>();

                //3. API ????????? ????????? ??????
                List<HashMap<String, Object>> tempList = new ArrayList<>();
                Class itemClass = ((HashMap<String, Object>) ((HashMap<String, Object>)(((HashMap<String, Object>) map.get("response")).get("body"))).get("item")).getClass();
                if(itemClass.getName().equals("java.util.LinkedHashMap")) {
                    tempList.add((HashMap<String, Object>) ((HashMap<String, Object>)(((HashMap<String, Object>) map.get("response")).get("body"))).get("item"));
                }
                //Origin
                else {
                    tempList = (List<HashMap<String, Object>>) (HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item");
                }

                //????????? ID ??? ?????? ?????? ??????
                String tempElevatorNo = "";
                String lastInspctDe = "";
                String installationDe = "";
                //4. ????????? ???????????? ????????? ????????? ??? ?????? ?????? / ??????????????? ???????????? ?????????
                for(HashMap<String, Object> tempMap1 : tempList) {
                    tempElevatorNo = String.valueOf(tempMap1.get("elevatorNo"));
                    lastInspctDe = String.valueOf(tempMap1.get("lastInspctDe"));
                    installationDe = String.valueOf(tempMap1.get("installationDe"));
                }

                HashMap<String, Object> resultMap = new HashMap<>();
                resultMap.put("elevatorNo", tempElevatorNo);
                resultMap.put("lastInspctDe", lastInspctDe);    //????????????
                resultMap.put("installationDe", installationDe); //????????????

                logger.info("No. : " + j + " - elevatorNo : " + tempElevatorNo + " - lastInspctDe : " + lastInspctDe + " - installationDe : " + installationDe);

                list.add(resultMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //5. ????????? ???????????? Excel ?????? ??????
        FileOutputStream fos = new FileOutputStream(writeFile);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("new");    // sheet ??????

        XSSFRow curRow;

        int row = list.size();    // list ??????
        try {
            curRow = sheet.createRow(0);    // ????????? Row
            curRow.createCell(0).setCellValue("????????? ??????");    // row??? ??? cell ??????
            curRow.createCell(1).setCellValue("???????????? ??????");
            curRow.createCell(2).setCellValue("?????? ??????");

            for (int tempInt = 0; tempInt < row; tempInt++) {
                curRow = sheet.createRow(tempInt + 1);    // row ??????
                curRow.createCell(0).setCellValue((String)(list.get(tempInt).get("elevatorNo")));    // row??? ??? cell ??????
                curRow.createCell(1).setCellValue((String)(list.get(tempInt).get("lastInspctDe")));
                curRow.createCell(2).setCellValue((String)(list.get(tempInt).get("installationDe")));
            }
            /****
             for (int tempInt = 0; tempInt < row; tempInt++) {
             curRow = sheet.createRow(tempInt);    // row ??????
             curRow.createCell(0).setCellValue((String) (list.get(tempInt).get("elevatorNo")));    // row??? ??? cell ??????
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

    @Transactional
    public int insertGovElevatorInfoFromGovApi() throws Exception {
        int insertCnt = 0;

        String serviceKey = "U%2Fu1aFs%2B%2FD5EDhaAqwTEBC2NoYbubcKBg3gw8UDWWCYXIa1NX0HKk9dcXf0rqoU7%2F%2FQxvhDh2FXk%2Bfhg7KXZSQ%3D%3D";
        String readFile = "C:\\excelreadwrite\\readElId.csv";
        String apiUri = "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN";

        List<HashMap<String, Object>> list = new ArrayList<>();

        int iKey = 0;
        HashMap<String, String> tempMap = new HashMap<>();

        // 1. CSV ???????????? ?????? Read(????????? ??????)  -> EXCEL ???????????? ??????
        ConverterUtil converterUtil = new ConverterUtil();
        List<List<String>> list1 = ConverterUtil.readToList(readFile);

        for (int y = 1; y < list1.size(); y++) {//Row 0 ????????? ?????????
            List<String> line = list1.get(y);
            for (int x = 0; x < line.size(); x++) {    // Column - 0 ?????? ??? READ
                if (!line.get(0).equals("") && line.get(0) != null) {
                    if (x == 0)
                        tempMap.put(String.valueOf(iKey++), String.format("%07d", Integer.parseInt(line.get(x))));
                }
            }
        }

        //2. Read ????????? ????????? api ?????? (get)
        for (int j = 0; j < iKey; j++) {
            String elevatorNo = tempMap.get("" + j);

            //???????????? API ??????
            Map<String, String> requestHeaders = new HashMap<>();
            apiUri = "http://openapi.elevator.go.kr/openapi/service/ElevatorInformationService/getElevatorViewN"
                    + "?serviceKey=" + serviceKey
                    + "&elevator_no=" + elevatorNo;
            try {
                String responseBody = apiHttpRequest.get(apiUri, requestHeaders);

                //timeout 0.5???
                Thread.sleep(500);

                HashMap<String, Object> map = converterUtil.jsonString2Map(converterUtil.xml2JsonString(responseBody));
                HashMap<String, Object> itemsHashMap = new HashMap<>();

                //3. API ????????? ????????? ??????
                List<HashMap<String, Object>> tempList = new ArrayList<>();
                Class itemClass = ((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item")).getClass();
                if (itemClass.getName().equals("java.util.LinkedHashMap")) {
                    tempList.add((HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item"));
                }
                //Origin
                else {
                    tempList = (List<HashMap<String, Object>>) (HashMap<String, Object>) ((HashMap<String, Object>) (((HashMap<String, Object>) map.get("response")).get("body"))).get("item");
                }

                //4. ????????? ???????????? ??????
                for (HashMap<String, Object> tempMap1 : tempList) {
                    ObjectMapper mapper = new ObjectMapper();
                    GovElevatorInfoDto govElevatorInfoDto = mapper.convertValue(tempMap1, GovElevatorInfoDto.class);

                    //Dto to Entity
                    GovElevatorInfo govElevatorInfo = govElevatorInfoDto.toEntity();

                    /*
                    govElevatorInfoRepository.save(GovElevatorInfo.builder()
                            .elevatorNo("X000002")
                            .address1("????????? ?????????")
                            .address2("????????????")
                            .build());
                    */

                    //?????? ??????
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
