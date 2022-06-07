package jh.project.lookupbl.service;

import jh.project.lookupbl.dto.CargCsclPrgs;
import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryRtnVoTag;
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
public class MainService {

    @Value("${API0001.URL}")
    private String apiUrl;

    @Value("${API0001.KEY}")
    private String key;

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

    public CargCsclPrgsInfoQryRtnVoTag lookupHbl(HblForm form) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); //한글인코딩설정
        String restApiUrl = apiUrl + key + "&hblNo=" +form.getHblNo() + "&blYy=" + form.getBlYy();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(restApiUrl);
        doc.getDocumentElement().normalize();

        JAXBContext jaxbContext = JAXBContext.newInstance(CargCsclPrgsInfoQryRtnVoTag.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        CargCsclPrgsInfoQryRtnVoTag cargCsclPrgsInfoQryRtnVoTag = (CargCsclPrgsInfoQryRtnVoTag) unmarshaller.unmarshal(doc);

        return cargCsclPrgsInfoQryRtnVoTag;
    }

    public List<Hbl> extractHblNo(HblForm form) {
        List<Hbl> hblList = new ArrayList<>();

        //공백제거
        form.setHblNo(form.getHblNo().replaceAll(" ", ""));
        form.setHblNo(form.getHblNo().trim());

        //구문문자체크
        String[] checkSplit = form.getHblNo().split(",|/|-|~");

        //구분문자가있을경우
        if(checkSplit.length > 1) {
            for(int i =0; i < checkSplit.length; i++) {
                Hbl tempHbl = new Hbl();

                if(i == 0) { //첫 기준 문자
                    tempHbl.setHblYear(form.getBlYy());
                    tempHbl.setHblNo(checkSplit[i]);
                } else { //추가적인 번호가 있으면
                    int cuttingLength = checkSplit[i].length();
                    System.out.println(cuttingLength + " : 자를 길이");
                    System.out.println(checkSplit[0] + " : 0번째 문자열, 기준문자열");
                    checkSplit[i] = checkSplit[0].substring(0, checkSplit[0].length()-cuttingLength) + checkSplit[i];
                    System.out.println(checkSplit[0].substring(0, checkSplit[0].length()-cuttingLength) + " : 자른후0번째 문자열");
                    System.out.println(checkSplit[i]+ " : 수정된 문자열");
                    tempHbl.setHblYear(form.getBlYy());
                    tempHbl.setHblNo(checkSplit[i]);
                }

                hblList.add(tempHbl);
            }
        } else { //구분문자가 없을때 그냥 추가
            Hbl tempHbl = new Hbl();
            tempHbl.setHblYear(form.getBlYy());
            tempHbl.setHblNo(checkSplit[0]);

            hblList.add(tempHbl);
        }

        return hblList;
    }
}
