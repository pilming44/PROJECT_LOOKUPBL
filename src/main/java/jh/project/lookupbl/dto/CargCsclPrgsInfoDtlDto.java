package jh.project.lookupbl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargCsclPrgsInfoDtlDto { //진행정보상세단계
    private String cargTrcnRelaBsopTpcd; //처리구분
    private String rlbrDttm; //반출입일시
    private String rlbrCn; //반출입내용
    private String wght; //중량
    private String shedSgn; //장치장부호
    private String shedNm; //장치장명
    private String dclrNo; //신고번호
    private String rlbrBssNo; //반출입근거번호
    private String bfhnGdncCn; //사전안내내용
    private String pckGcnt; //포장개수
    private String pckUt; //포장단위
    private String wghtUt; //중량단위
    private String prcsDttm; //처리일시

}
