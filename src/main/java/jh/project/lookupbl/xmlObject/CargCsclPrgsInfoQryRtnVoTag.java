package jh.project.lookupbl.xmlObject;

import jh.project.lookupbl.dto.CargCsclPrgsInfoDtlDto;
import jh.project.lookupbl.dto.CargCsclPrgsInfoDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cargCsclPrgsInfoQryRtnVo")
@Getter @Setter @ToString
public class CargCsclPrgsInfoQryRtnVoTag {

    @XmlElement(name = "ntceInfo")
    private String ntceInfo; //오류메세지 공통

    @XmlElement(name = "tCnt")
    private String tCnt; // 검색결과 카운트, 오류시 코드 -1

    @XmlElement(name = "cargCsclPrgsInfoQryVo")
    private CargCsclPrgsInfoQryVoTag cargCsclPrgsInfoQryVoTag; //진행정보 개요

    @XmlElement(name = "cargCsclPrgsInfoDtlQryVo")
    private List<CargCsclPrgsInfoDtlQryVoTag> cargCsclPrgsInfoDtlQryVoTag; //진행정보상세단계

}
