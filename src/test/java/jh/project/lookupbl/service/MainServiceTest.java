package jh.project.lookupbl.service;

import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryRtnVoTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

@SpringBootTest
public class MainServiceTest {
//    언마셜 방법 사용해서 아래 방법 제외
//    @Test
//    void 하우스비엘검색테스트() {
//        //given
//        HblForm hblForm = new HblForm();
//        hblForm.setHblNo("CCIK205095");
//        hblForm.setBlYy("2020");
//
//        //when
//        MainService mainService = new MainService();
//        Document result = null;
//        try{
//            result = mainService.lookupHbl(hblForm);
//
//            String rootElement = result.getDocumentElement().getNodeName();
//            System.out.println("Root Element :" + rootElement);
//
//            NodeList rootNodeList = result.getElementsByTagName(rootElement);
//
//            System.out.println("rootNode 길이" + rootNodeList.getLength());
//
//            Node statusNode = rootNodeList.item(0); //루트노드 가져옴
//            Element statusElement = (Element) statusNode;
//            System.out.println("ntceInfo :" + statusElement.getElementsByTagName("ntceInfo").item(0).getTextContent());
//            int tCnt = Integer.parseInt(statusElement.getElementsByTagName("tCnt").item(0).getTextContent());
//            System.out.println("tCnt :" + statusElement.getElementsByTagName("tCnt").item(0).getTextContent());
//
//            NodeList infoNodeList = result.getElementsByTagName("cargCsclPrgsInfoQryVo");
//            System.out.println("infoNode 길이 : " + infoNodeList.getLength());
//            if(infoNodeList.getLength() > 0) {
//                Node infoNode = infoNodeList.item(0);
//                Element infoElement = (Element) infoNode;
//                System.out.println("csclPrgsStts :" + infoElement.getElementsByTagName("csclPrgsStts").item(0).getTextContent());
//                System.out.println("prnm :" + infoElement.getElementsByTagName("prnm").item(0).getTextContent());
//                System.out.println("msrm :" + infoElement.getElementsByTagName("msrm").item(0).getTextContent());
//                //todo CargCsclPrgsInfo객체에 담기
//
//                //진행상황 디테일
//                NodeList dtlNodeList = result.getElementsByTagName("cargCsclPrgsInfoDtlQryVo");
//                System.out.println("dtlNodeList 길이 : " + dtlNodeList.getLength());
//                for(int i = 0; i < tCnt; i++) {
//                    Node dtlNode = dtlNodeList.item(i);
//                    Element dtlElement = (Element) dtlNode;
//                    System.out.println( tCnt-i +"번째 prcsDttm :" + dtlElement.getElementsByTagName("prcsDttm").item(0).getTextContent());
//                    //todo CargCsclPrgsInfoDtl객체에 담기
//                }
//            }
//
//
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//
//
//        //then
//        System.out.println("테스트 종료");
//    }
    @Autowired
    private MainService mainService;

    @Test
    void 단일_하우스비엘검색_언마셜() {
        //given

        HblForm hblForm = new HblForm();
        hblForm.setHblNo("CCIK205095");
        hblForm.setBlYy("2020");

        //when

        CargCsclPrgsInfoQryRtnVoTag result = null;
        try{
            result = mainService.lookupHbl(hblForm);

            //then
            System.out.println("NtceInfo: " + result.getNtceInfo());
            System.out.println("TCnt: " + result.getTCnt());
            if(Integer.parseInt(result.getTCnt()) > 0) {
                System.out.println("----------------------------------------개요----------------------------------------------");
                System.out.println("cargMtNo : "+result.getCargCsclPrgsInfoQryVoTag().getCargMtNo());
                System.out.println("prgsStts : "+result.getCargCsclPrgsInfoQryVoTag().getPrgsStts());
                System.out.println("prgsStCd : "+result.getCargCsclPrgsInfoQryVoTag().getPrgsStCd());
                System.out.println("shipNat : "+result.getCargCsclPrgsInfoQryVoTag().getShipNat());
                System.out.println("shipNatNm : "+result.getCargCsclPrgsInfoQryVoTag().getShipNatNm());
                System.out.println("mblNo : "+result.getCargCsclPrgsInfoQryVoTag().getMblNo());
                System.out.println("hblNo : "+result.getCargCsclPrgsInfoQryVoTag().getHblNo());
                System.out.println("agnc : "+result.getCargCsclPrgsInfoQryVoTag().getAgnc());
                System.out.println("shcoFlcoSgn : "+result.getCargCsclPrgsInfoQryVoTag().getShcoFlcoSgn());
                System.out.println("shcoFlco : "+result.getCargCsclPrgsInfoQryVoTag().getShcoFlco());
                System.out.println("cargTp : "+result.getCargCsclPrgsInfoQryVoTag().getCargTp());
                System.out.println("ldprCd : "+result.getCargCsclPrgsInfoQryVoTag().getLdprCd());
                System.out.println("ldprNm : "+result.getCargCsclPrgsInfoQryVoTag().getLdprNm());
                System.out.println("lodCntyCd : "+result.getCargCsclPrgsInfoQryVoTag().getLodCntyCd());
                System.out.println("shipNm : "+result.getCargCsclPrgsInfoQryVoTag().getShipNm());
                System.out.println("pckGcnt : "+result.getCargCsclPrgsInfoQryVoTag().getPckGcnt());
                System.out.println("pckUt : "+result.getCargCsclPrgsInfoQryVoTag().getPckUt());
                System.out.println("blPt : "+result.getCargCsclPrgsInfoQryVoTag().getBlPt());
                System.out.println("blPtNm : "+result.getCargCsclPrgsInfoQryVoTag().getBlPtNm());
                System.out.println("dsprCd : "+result.getCargCsclPrgsInfoQryVoTag().getDsprCd());
                System.out.println("dsprNm : "+result.getCargCsclPrgsInfoQryVoTag().getDsprNm());
                System.out.println("etprCstm : "+result.getCargCsclPrgsInfoQryVoTag().getEtprCstm());
                System.out.println("etprDt : "+result.getCargCsclPrgsInfoQryVoTag().getEtprDt());
                System.out.println("msrm : "+result.getCargCsclPrgsInfoQryVoTag().getMsrm());
                System.out.println("ttwg : "+result.getCargCsclPrgsInfoQryVoTag().getTtwg());
                System.out.println("wghtUt : "+result.getCargCsclPrgsInfoQryVoTag().getWghtUt());
                System.out.println("prnm : "+result.getCargCsclPrgsInfoQryVoTag().getPrnm());
                System.out.println("cntrGcnt : "+result.getCargCsclPrgsInfoQryVoTag().getCntrGcnt());
                System.out.println("cntrNo : "+result.getCargCsclPrgsInfoQryVoTag().getCntrNo());
                System.out.println("csclPrgsStts : "+result.getCargCsclPrgsInfoQryVoTag().getCsclPrgsStts());
                System.out.println("prcsDttm : "+result.getCargCsclPrgsInfoQryVoTag().getPrcsDttm());
                System.out.println("frwrSgn : "+result.getCargCsclPrgsInfoQryVoTag().getFrwrSgn());
                System.out.println("frwrEntsConm : "+result.getCargCsclPrgsInfoQryVoTag().getFrwrEntsConm());
                System.out.println("vydf : "+result.getCargCsclPrgsInfoQryVoTag().getVydf());
                System.out.println("spcnCargCd : "+result.getCargCsclPrgsInfoQryVoTag().getSpcnCargCd());
                System.out.println("mtTrgtCargYnNm : "+result.getCargCsclPrgsInfoQryVoTag().getMtTrgtCargYnNm());
                System.out.println("rlseDtyPridPassTpcd : "+result.getCargCsclPrgsInfoQryVoTag().getRlseDtyPridPassTpcd());
                System.out.println("dclrDelyAdtxYn : "+result.getCargCsclPrgsInfoQryVoTag().getDclrDelyAdtxYn());
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("---------------------------상세단계-------------------------------------------------------");
                for(int i =0; i < Integer.parseInt(result.getTCnt()); i++) {
                    System.out.println("cargTrcnRelaBsopTpcd : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getCargTrcnRelaBsopTpcd());
                    System.out.println("rlbrDttm : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrDttm());
                    System.out.println("rlbrCn : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrCn());
                    System.out.println("wght : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getWght());
                    System.out.println("shedSgn : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getShedSgn());
                    System.out.println("shedNm : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getShedNm());
                    System.out.println("dclrNo : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getDclrNo());
                    System.out.println("rlbrBssNo : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrBssNo());
                    System.out.println("bfhnGdncCn : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getBfhnGdncCn());
                    System.out.println("pckGcnt : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPckGcnt());
                    System.out.println("pckUt : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPckUt());
                    System.out.println("wghtUt : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getWghtUt());
                    System.out.println("prcsDttm : "+result.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPrcsDttm());
                    System.out.println("---------------------------------------------------------------------------------------");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("테스트 종료");
    }

    @Test
    void 단일_하우스비엘_번호추출() {

        HblForm hblForm = new HblForm();
        hblForm.setHblNo("    CC IK2  05095 ,  96, 9  7,98  ");
        hblForm.setBlYy("2020");

        List<Hbl> testList = mainService.extractHblNo(hblForm);

        System.out.println(testList);
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i).getHblNo());
        }

    }
}
