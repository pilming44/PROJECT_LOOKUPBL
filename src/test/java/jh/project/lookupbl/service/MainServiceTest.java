package jh.project.lookupbl.service;

import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.form.MblForm;
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

import java.util.ArrayList;
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
    void 마스터비엘검색_언마셜() {
        //given
        List<MblForm> mblFormList = new ArrayList<>();

        MblForm mblForm = new MblForm();
        mblForm.setMblNo("18065047345");
        mblForm.setBlYy("2020");
        mblFormList.add(mblForm);

        MblForm mblForm2 = new MblForm();
        mblForm2.setMblNo("18091998174");
        mblForm2.setBlYy("2021");
        mblFormList.add(mblForm2);

        //when
        List<CargCsclPrgsInfoQryRtnVoTag> result = null;
        try{
            result = mainService.lookupMbl(mblFormList);

            //then
            for(CargCsclPrgsInfoQryRtnVoTag v : result) {
                System.out.println(v);
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("테스트 종료");
    }
    @Test
    void 하우스비엘검색_언마셜() {
        //given
        List<HblForm> hblFormList = new ArrayList<>();

        HblForm hblForm = new HblForm();
        hblForm.setHblNo("CCIK205095");
        hblForm.setBlYy("2020");
        hblFormList.add(hblForm);
        HblForm hblForm2 = new HblForm();
        hblForm2.setHblNo("CCIK205096");
        hblForm2.setBlYy("2020");
        hblFormList.add(hblForm2);
        HblForm hblForm3 = new HblForm();
        hblForm3.setHblNo("CCIK205097");
        hblForm3.setBlYy("2020");
        hblFormList.add(hblForm3);
        //when

        List<CargCsclPrgsInfoQryRtnVoTag> result = null;
        try{
            result = mainService.lookupHbl(hblFormList);

            //then
            for(CargCsclPrgsInfoQryRtnVoTag v : result) {
                System.out.println(v);
//                System.out.println("NtceInfo: " + v.getNtceInfo());
//                System.out.println("TCnt: " + v.getTCnt());
//                if(Integer.parseInt(v.getTCnt()) > 0) {
//                    System.out.println("----------------------------------------개요----------------------------------------------");
//                    System.out.println("cargMtNo : "+v.getCargCsclPrgsInfoQryVoTag().getCargMtNo());
//                    System.out.println("prgsStts : "+v.getCargCsclPrgsInfoQryVoTag().getPrgsStts());
//                    System.out.println("prgsStCd : "+v.getCargCsclPrgsInfoQryVoTag().getPrgsStCd());
//                    System.out.println("shipNat : "+v.getCargCsclPrgsInfoQryVoTag().getShipNat());
//                    System.out.println("shipNatNm : "+v.getCargCsclPrgsInfoQryVoTag().getShipNatNm());
//                    System.out.println("mblNo : "+v.getCargCsclPrgsInfoQryVoTag().getMblNo());
//                    System.out.println("hblNo : "+v.getCargCsclPrgsInfoQryVoTag().getHblNo());
//                    System.out.println("agnc : "+v.getCargCsclPrgsInfoQryVoTag().getAgnc());
//                    System.out.println("shcoFlcoSgn : "+v.getCargCsclPrgsInfoQryVoTag().getShcoFlcoSgn());
//                    System.out.println("shcoFlco : "+v.getCargCsclPrgsInfoQryVoTag().getShcoFlco());
//                    System.out.println("cargTp : "+v.getCargCsclPrgsInfoQryVoTag().getCargTp());
//                    System.out.println("ldprCd : "+v.getCargCsclPrgsInfoQryVoTag().getLdprCd());
//                    System.out.println("ldprNm : "+v.getCargCsclPrgsInfoQryVoTag().getLdprNm());
//                    System.out.println("lodCntyCd : "+v.getCargCsclPrgsInfoQryVoTag().getLodCntyCd());
//                    System.out.println("shipNm : "+v.getCargCsclPrgsInfoQryVoTag().getShipNm());
//                    System.out.println("pckGcnt : "+v.getCargCsclPrgsInfoQryVoTag().getPckGcnt());
//                    System.out.println("pckUt : "+v.getCargCsclPrgsInfoQryVoTag().getPckUt());
//                    System.out.println("blPt : "+v.getCargCsclPrgsInfoQryVoTag().getBlPt());
//                    System.out.println("blPtNm : "+v.getCargCsclPrgsInfoQryVoTag().getBlPtNm());
//                    System.out.println("dsprCd : "+v.getCargCsclPrgsInfoQryVoTag().getDsprCd());
//                    System.out.println("dsprNm : "+v.getCargCsclPrgsInfoQryVoTag().getDsprNm());
//                    System.out.println("etprCstm : "+v.getCargCsclPrgsInfoQryVoTag().getEtprCstm());
//                    System.out.println("etprDt : "+v.getCargCsclPrgsInfoQryVoTag().getEtprDt());
//                    System.out.println("msrm : "+v.getCargCsclPrgsInfoQryVoTag().getMsrm());
//                    System.out.println("ttwg : "+v.getCargCsclPrgsInfoQryVoTag().getTtwg());
//                    System.out.println("wghtUt : "+v.getCargCsclPrgsInfoQryVoTag().getWghtUt());
//                    System.out.println("prnm : "+v.getCargCsclPrgsInfoQryVoTag().getPrnm());
//                    System.out.println("cntrGcnt : "+v.getCargCsclPrgsInfoQryVoTag().getCntrGcnt());
//                    System.out.println("cntrNo : "+v.getCargCsclPrgsInfoQryVoTag().getCntrNo());
//                    System.out.println("csclPrgsStts : "+v.getCargCsclPrgsInfoQryVoTag().getCsclPrgsStts());
//                    System.out.println("prcsDttm : "+v.getCargCsclPrgsInfoQryVoTag().getPrcsDttm());
//                    System.out.println("frwrSgn : "+v.getCargCsclPrgsInfoQryVoTag().getFrwrSgn());
//                    System.out.println("frwrEntsConm : "+v.getCargCsclPrgsInfoQryVoTag().getFrwrEntsConm());
//                    System.out.println("vydf : "+v.getCargCsclPrgsInfoQryVoTag().getVydf());
//                    System.out.println("spcnCargCd : "+v.getCargCsclPrgsInfoQryVoTag().getSpcnCargCd());
//                    System.out.println("mtTrgtCargYnNm : "+v.getCargCsclPrgsInfoQryVoTag().getMtTrgtCargYnNm());
//                    System.out.println("rlseDtyPridPassTpcd : "+v.getCargCsclPrgsInfoQryVoTag().getRlseDtyPridPassTpcd());
//                    System.out.println("dclrDelyAdtxYn : "+v.getCargCsclPrgsInfoQryVoTag().getDclrDelyAdtxYn());
//                    System.out.println("---------------------------------------------------------------------------------------");
//                    System.out.println("---------------------------------------------------------------------------------------");
//                    System.out.println("---------------------------상세단계-------------------------------------------------------");
//                    for(int i =0; i < Integer.parseInt(v.getTCnt()); i++) {
//                        System.out.println("cargTrcnRelaBsopTpcd : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getCargTrcnRelaBsopTpcd());
//                        System.out.println("rlbrDttm : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrDttm());
//                        System.out.println("rlbrCn : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrCn());
//                        System.out.println("wght : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getWght());
//                        System.out.println("shedSgn : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getShedSgn());
//                        System.out.println("shedNm : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getShedNm());
//                        System.out.println("dclrNo : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getDclrNo());
//                        System.out.println("rlbrBssNo : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getRlbrBssNo());
//                        System.out.println("bfhnGdncCn : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getBfhnGdncCn());
//                        System.out.println("pckGcnt : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPckGcnt());
//                        System.out.println("pckUt : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPckUt());
//                        System.out.println("wghtUt : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getWghtUt());
//                        System.out.println("prcsDttm : "+v.getCargCsclPrgsInfoDtlQryVoTag().get(i).getPrcsDttm());
//                        System.out.println("---------------------------------------------------------------------------------------");
//                    }
//                }
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
