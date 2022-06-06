package jh.project.lookupbl.xmlObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@ToString
public class CargCsclPrgsInfoQryVoTag {
    @XmlElement(name = "cargMtNo")
    private String cargMtNo; //화물관리번호

    @XmlElement(name = "prgsStts")
    private String prgsStts; //진행상태

    @XmlElement(name = "prgsStCd")
    private String prgsStCd; //진행상태코드

    @XmlElement(name = "shipNat")
    private String shipNat; //선박국적

    @XmlElement(name = "shipNatNm")
    private String shipNatNm; //선박국적명

    @XmlElement(name = "mblNo")
    private String mblNo; //MBL번호

    @XmlElement(name = "hblNo")
    private String hblNo; //HBL번호

    @XmlElement(name = "agnc")
    private String agnc; //대리점

    @XmlElement(name = "shcoFlcoSgn")
    private String shcoFlcoSgn; //선사항공사부호

    @XmlElement(name = "shcoFlco")
    private String shcoFlco; //선사항공사

    @XmlElement(name = "cargTp")
    private String cargTp; //화물구분

    @XmlElement(name = "ldprCd")
    private String ldprCd; //적재항코드

    @XmlElement(name = "ldprNm")
    private String ldprNm; //적재항명

    @XmlElement(name = "lodCntyCd")
    private String lodCntyCd; //적출국가코드

    @XmlElement(name = "shipNm")
    private String shipNm; //선박명

    @XmlElement(name = "pckGcnt")
    private String pckGcnt; //포장개수

    @XmlElement(name = "pckUt")
    private String pckUt; //포장단위

    @XmlElement(name = "blPt")
    private String blPt; //BL유형

    @XmlElement(name = "blPtNm")
    private String blPtNm; //BL유형명

    @XmlElement(name = "dsprCd")
    private String dsprCd; //양륙항코드

    @XmlElement(name = "dsprNm")
    private String dsprNm; //양륙항명

    @XmlElement(name = "etprCstm")
    private String etprCstm; //입항세관

    @XmlElement(name = "etprDt")
    private String etprDt; //입항일자

    @XmlElement(name = "msrm")
    private String msrm; //용적

    @XmlElement(name = "ttwg")
    private String ttwg; //총중량

    @XmlElement(name = "wghtUt")
    private String wghtUt; //중량단위

    @XmlElement(name = "prnm")
    private String prnm; //품명

    @XmlElement(name = "cntrGcnt")
    private String cntrGcnt; //컨테이너개수

    @XmlElement(name = "cntrNo")
    private String cntrNo; //컨테이너번호

    @XmlElement(name = "csclPrgsStts")
    private String csclPrgsStts; //통관진행상태

    @XmlElement(name = "prcsDttm")
    private String prcsDttm; //처리일시

    @XmlElement(name = "frwrSgn")
    private String frwrSgn; //포워더부호

    @XmlElement(name = "frwrEntsConm")
    private String frwrEntsConm; //포워더명

    @XmlElement(name = "vydf")
    private String vydf; //항차

    @XmlElement(name = "spcnCargCd")
    private String spcnCargCd; //특수화물코드

    @XmlElement(name = "mtTrgtCargYnNm")
    private String mtTrgtCargYnNm; //관리대상화물여부명

    @XmlElement(name = "rlseDtyPridPassTpcd")
    private String rlseDtyPridPassTpcd; //반출의무과태로여부

    @XmlElement(name = "dclrDelyAdtxYn")
    private String dclrDelyAdtxYn; //신고지연가산세여부
}
