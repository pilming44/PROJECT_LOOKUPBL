package jh.project.lookupbl.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HblForm { //하우스bl 입력 폼
    private Integer inputBoxCount = 1;
    private String hblNo; //hbl번호
    private String blYy; //입항년도

    private List<HblForm> hblList = new ArrayList<>();
}
