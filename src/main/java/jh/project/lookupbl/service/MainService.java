package jh.project.lookupbl.service;

import jh.project.lookupbl.dto.CargCsclPrgs;
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
}
