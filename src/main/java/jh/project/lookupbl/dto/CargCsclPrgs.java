package jh.project.lookupbl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CargCsclPrgs { //한 건 조회시 조회결과
    private CargCsclPrgsInfoDto cargCsclPrgsInfoDto; //진행정보 개요
    private List<CargCsclPrgsInfoDtlDto> cargCsclPrgsInfoDtlDtoList; //진행정보상세단계
}
