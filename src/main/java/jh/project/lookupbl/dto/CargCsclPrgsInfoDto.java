package jh.project.lookupbl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargCsclPrgsInfoDto { //진행정보 개요

    private String cargMtNo; //화물관리번호
    private String prgsStts; //진행상태
    private String prgsStCd; //진행상태코드
    private String shipNat; //선박국적
    private String shipNatNm; //선박국적명
    private String mblNo; //MBL번호
    private String hblNo; //HBL번호
    private String agnc; //대리점
    private String shcoFlcoSgn; //선사항공사부호
    private String shcoFlco; //선사항공사
    private String cargTp; //화물구분
    private String ldprCd; //적재항코드
    private String ldprNm; //적재항명
    private String lodCntyCd; //적출국가코드
    private String shipNm; //선박명
    private String pckGcnt; //포장개수
    private String pckUt; //포장단위
    private String blPt; //BL유형
    private String blPtNm; //BL유형명
    private String dsprCd; //양륙항코드
    private String dsprNm; //양륙항명
    private String etprCstm; //입항세관
    private String etprDt; //입항일자
    private String msrm; //용적
    private String ttwg; //총중량
    private String wghtUt; //중량단위
    private String prnm; //품명
    private String cntrGcnt; //컨테이너개수
    private String cntrNo; //컨테이너번호
    private String csclPrgsStts; //통관진행상태
    private String prcsDttm; //처리일시
    private String frwrSgn; //포워더부호
    private String frwrEntsConm; //포워더명
    private String vydf; //항차
    private String spcnCargCd; //특수화물코드
    private String mtTrgtCargYnNm; //관리대상화물여부명
    private String rlseDtyPridPassTpcd; //반출의무과태로여부
    private String dclrDelyAdtxYn; //신고지연가산세여부

}
