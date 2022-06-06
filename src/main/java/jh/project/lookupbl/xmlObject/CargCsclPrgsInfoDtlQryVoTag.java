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
public class CargCsclPrgsInfoDtlQryVoTag {
    @XmlElement(name = "cargTrcnRelaBsopTpcd")
    private String cargTrcnRelaBsopTpcd; //처리구분

    @XmlElement(name = "rlbrDttm")
    private String rlbrDttm; //반출입일시

    @XmlElement(name = "rlbrCn")
    private String rlbrCn; //반출입내용

    @XmlElement(name = "wght")
    private String wght; //중량

    @XmlElement(name = "shedSgn")
    private String shedSgn; //장치장부호

    @XmlElement(name = "shedNm")
    private String shedNm; //장치장명

    @XmlElement(name = "dclrNo")
    private String dclrNo; //신고번호

    @XmlElement(name = "rlbrBssNo")
    private String rlbrBssNo; //반출입근거번호

    @XmlElement(name = "bfhnGdncCn")
    private String bfhnGdncCn; //사전안내내용

    @XmlElement(name = "pckGcnt")
    private String pckGcnt; //포장개수

    @XmlElement(name = "pckUt")
    private String pckUt; //포장단위

    @XmlElement(name = "wghtUt")
    private String wghtUt; //중량단위

    @XmlElement(name = "prcsDttm")
    private String prcsDttm; //처리일시
}
