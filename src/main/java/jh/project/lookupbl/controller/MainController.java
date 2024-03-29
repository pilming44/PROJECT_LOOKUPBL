package jh.project.lookupbl.controller;

import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.service.MainService;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoDtlQryVoTag;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryRtnVoTag;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryVoTag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String home() {


        return "home";
    }

    @GetMapping("/test")
    public String test(@ModelAttribute HblForm hblForm, Model model) {

        int inputBoxCount = hblForm.getInputBoxCount();
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 2년 전
        int year = now.getYear() - 2;
        List<Integer> yearList = new ArrayList<>();
        yearList.add(year);

        for(int i =1; i <= 3; i++) {
            yearList.add(year+i);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("inputBoxCount", inputBoxCount);
        result.put("yearList", yearList);
        result.put("currentYear", now.getYear());
        result.put("hblForm", hblForm);

        model.addAttribute("result", result);
        return "test";
    }

    @GetMapping("/lookupResult")
    public String testPost(Model model, @ModelAttribute HblForm hblForm) throws Exception {
        for(HblForm data : hblForm.getHblList()) {
            if(data.getHblNo() == null) {
                data.setHblNo("");
            }
            logger.debug("data.getBlYy : {} / data.getHblNo : {}", data.getBlYy(), data.getHblNo());
        }
        //검색 결과가 담길 리스트
        List<CargCsclPrgsInfoQryRtnVoTag> cargCsclPrgsInfoQryRtnVoTag = mainService.lookupHbl(hblForm.getHblList());
        // 현재 날짜/시간
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 포맷팅
        String formatedNow = nowDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        // 포맷팅 현재 날짜/시간 출력
        logger.debug("현재시간 : {}", formatedNow);


        //시간 포맷설정
        SimpleDateFormat oldDtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.debug("결과 : {}", cargCsclPrgsInfoQryRtnVoTag);
        for(CargCsclPrgsInfoQryRtnVoTag temp : cargCsclPrgsInfoQryRtnVoTag) {
            if(temp.getTCnt().equals("0") || temp.getTCnt().equals("-1")) {
                continue;
            }
            //데이터값 포맷팅
            for(CargCsclPrgsInfoQryVoTag info : temp.getCargCsclPrgsInfoQryVoTag()) {
                String prcsDttm = info.getPrcsDttm();
                // String 타입을 Date 타입으로 변환
                Date oldFormatDate = oldDtFormat.parse(prcsDttm);
                // Date타입의 변수를 새롭게 지정한 포맷으로 변환
                String strNewDtFormat = newDtFormat.format(oldFormatDate);
                info.setPrcsDttm(strNewDtFormat);
            }
            for(CargCsclPrgsInfoDtlQryVoTag dtl : temp.getCargCsclPrgsInfoDtlQryVoTag()) {
                String prcsDttm = dtl.getPrcsDttm();
                Date oldFormatDate = oldDtFormat.parse(prcsDttm);
                // Date타입의 변수를 새롭게 지정한 포맷으로 변환
                String strNewDtFormat = newDtFormat.format(oldFormatDate);
                dtl.setPrcsDttm(strNewDtFormat);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("lookupResult", cargCsclPrgsInfoQryRtnVoTag);
        logger.debug("cargCsclPrgsInfoQryRtnVoTag : {}", cargCsclPrgsInfoQryRtnVoTag);
        result.put("formatedNow", formatedNow);
        model.addAttribute("result", result);
        return "lookupResult";
    }
}
