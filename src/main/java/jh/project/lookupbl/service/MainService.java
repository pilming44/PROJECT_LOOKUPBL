package jh.project.lookupbl.service;

import jh.project.lookupbl.dto.CargCsclPrgs;
import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.form.MblForm;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryRtnVoTag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    @Value("${API0001.URL}")
    private String apiUrl;

    @Value("${API0001.KEY}")
    private String key;

    private final Logger logger = LoggerFactory.getLogger(getClass());


//    언마셜 방법 사용해서 아래방법 제외
//    Document lookupHbl(HblForm form) throws ParserConfigurationException, IOException, SAXException {
//        CargCsclPrgs cargCsclPrgs = new CargCsclPrgs();
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); //한글인코딩설정
//        String restApiUrl = apiUrl + key + "&hblNo=" +form.getHblNo() + "&blYy=" + form.getBlYy();
//        //String result = restTemplate.getForObject(restApiUrl, String.class);
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(restApiUrl);
//        doc.getDocumentElement().normalize();
//        return doc;
//    }

    //하우스비엘 번호 검색
    public List<CargCsclPrgsInfoQryRtnVoTag> lookupHbl(List<HblForm> form) throws Exception {

        //유효성 검사 및 비엘번호 추출
        List<HblForm> refineData = extractHblNo(form);

        List<CargCsclPrgsInfoQryRtnVoTag> returnList = new ArrayList<>();

        for(int i = 0; i < refineData.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); //한글인코딩설정
            String restApiUrl = apiUrl + key + "&hblNo=" +refineData.get(i).getHblNo() + "&blYy=" + refineData.get(i).getBlYy();
            logger.debug("blYy : {} / hblNo : {}", refineData.get(i).getBlYy(), refineData.get(i).getHblNo());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(restApiUrl);
            doc.getDocumentElement().normalize();

            JAXBContext jaxbContext = JAXBContext.newInstance(CargCsclPrgsInfoQryRtnVoTag.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            CargCsclPrgsInfoQryRtnVoTag cargCsclPrgsInfoQryRtnVoTag = (CargCsclPrgsInfoQryRtnVoTag) unmarshaller.unmarshal(doc);
            returnList.add(cargCsclPrgsInfoQryRtnVoTag);
        }

        logger.debug("================테스트===============");

        return returnList;
    }

    //마스터비엘 검색
    public List<CargCsclPrgsInfoQryRtnVoTag> lookupMbl(List<MblForm> form) throws Exception {

        List<CargCsclPrgsInfoQryRtnVoTag> returnList = new ArrayList<>();

        for(int i = 0; i < form.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); //한글인코딩설정
            String restApiUrl = apiUrl + key + "&mblNo=" +form.get(i).getMblNo() + "&blYy=" + form.get(i).getBlYy();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(restApiUrl);
            doc.getDocumentElement().normalize();

            JAXBContext jaxbContext = JAXBContext.newInstance(CargCsclPrgsInfoQryRtnVoTag.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            CargCsclPrgsInfoQryRtnVoTag cargCsclPrgsInfoQryRtnVoTag = (CargCsclPrgsInfoQryRtnVoTag) unmarshaller.unmarshal(doc);
            returnList.add(cargCsclPrgsInfoQryRtnVoTag);
        }

        logger.debug("================테스트===============");

        return returnList;
    }

    //비엘번호 추출
    public List<HblForm> extractHblNo(List<HblForm> form) {

        List<HblForm> returnHblList = new ArrayList<>();

        //길이가 0이라면 null리턴
        if(form.size() == 0) {
            return null;
        }

        for(HblForm hf : form) {
            //공백 제거, 대문자로 변경
            String blYy = hf.getBlYy();
            String blNum = hf.getHblNo().replaceAll(" ","").toUpperCase();

            //구문문자체크
            String[] checkSplit = blNum.split(",|/|-");

            //구분문자가 있을때
            if(checkSplit.length > 1) {
                for(int i =0; i < checkSplit.length; i++) {
                    HblForm tempHBl = new HblForm();

                    if(i == 0) { //첫 기준 문자는 그대로 추가
                        tempHBl.setBlYy(blYy);
                        tempHBl.setHblNo(checkSplit[i]);
                    } else { //추가적인 번호가 있으면 추가번호의 길이만큼 첫 기준문자를 지우고 붙임
                        int cuttingLength = checkSplit[i].length();
                        checkSplit[i] = checkSplit[0].substring(0, checkSplit[0].length()-cuttingLength) + checkSplit[i];
                        tempHBl.setBlYy(blYy);
                        tempHBl.setHblNo(checkSplit[i]);
                    }
                    returnHblList.add(tempHBl);
                }
            } else {
                //구분문자가 없을때 그대로 추가
                HblForm tempHBl = new HblForm();
                tempHBl.setBlYy(blYy);
                tempHBl.setHblNo(blNum);
                returnHblList.add(tempHBl);
            }
        }
        return returnHblList;
    }
}
