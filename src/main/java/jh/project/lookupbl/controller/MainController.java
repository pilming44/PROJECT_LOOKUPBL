package jh.project.lookupbl.controller;

import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.service.MainService;
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
import java.time.ZoneId;
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

    @GetMapping("/lookupResult")
    public String lookupResult() {
        return "lookupResult";
    }

    @GetMapping("/test")
    public String test(@ModelAttribute HblForm hblForm, Model model) {

        int inputBoxCount = hblForm.getInputBoxCount();
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
//        logger.debug("현재시간 : {}", now);

        // 2년 전
        int year = now.getYear() - 2;
        logger.debug("현재년도 : {}", year);
        List<Integer> yearList = new ArrayList<>();
        yearList.add(year);

        for(int i =1; i <= 3; i++) {
            yearList.add(year+i);
        }
        logger.debug("년도리스트 : {}", yearList);

        Map<String, Object> result = new HashMap<>();
        result.put("inputBoxCount", inputBoxCount);
        result.put("yearList", yearList);
        result.put("currentYear", now.getYear());
        result.put("hblForm", hblForm);

        model.addAttribute("result", result);
        return "test";
    }

    @PostMapping("/test")
    public String testPost(Model model, @ModelAttribute HblForm hblForm) throws Exception {
        logger.debug("getHblNo : {}", hblForm.getHblList());

        for(HblForm data : hblForm.getHblList()) {
            logger.debug("data.getBlYy : {} / data.getHblNo : {}", data.getBlYy(), data.getHblNo());
        }
//
//        List<Hbl> extractHblList = new ArrayList<>();
//
//        for(int i =0; i < hblList.size(); i++) {
//            extractHblList.addAll(mainService.extractHblNo(hblList.get(i)));
//        }
//        HblForm hblForm1 = new HblForm();
//        for (int i = 0; i < extractHblList.size(); i++) {
//            System.out.println(i+1+"번째 bl" + extractHblList.get(i).getHblYear() + " / " + extractHblList.get(i).getHblNo());
//            hblForm1.setBlYy(extractHblList.get(i).getHblYear());
//            hblForm1.setHblNo(extractHblList.get(i).getHblNo());
//        }
//
//
        //검색 결과가 담길 리스트
        List<CargCsclPrgsInfoQryRtnVoTag> cargCsclPrgsInfoQryRtnVoTag = mainService.lookupHbl(hblForm.getHblList());
        for(CargCsclPrgsInfoQryRtnVoTag temp : cargCsclPrgsInfoQryRtnVoTag) {
            if(temp.getCargCsclPrgsInfoQryVoTag() == null) {
                break;
            }
            //데이터값 포맷팅
            for(CargCsclPrgsInfoQryVoTag info : temp.getCargCsclPrgsInfoQryVoTag()) {
                String prcsDttm = info.getPrcsDttm();
                //시간 포맷설정
                SimpleDateFormat oldDtFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // String 타입을 Date 타입으로 변환
                Date oldFormatDate = oldDtFormat.parse(prcsDttm);
                // Date타입의 변수를 새롭게 지정한 포맷으로 변환
                String strNewDtFormat = newDtFormat.format(oldFormatDate);
                info.setPrcsDttm(strNewDtFormat);


                logger.debug("mblNo : {} / hblNo : {} / prcsDttm : {}", info.getMblNo() ,info.getHblNo(), info.getPrcsDttm());
            }
        }




//        // 현재 날짜 구하기
//        LocalDate now = LocalDate.now();
//        // 2년 전
//        int year = now.getYear() - 2;
//        logger.debug("현재년도 : {}", year);
//        List<Integer> yearList = new ArrayList<>();
//        yearList.add(year);
//
//        for(int i =1; i <= 3; i++) {
//            yearList.add(year+i);
//        }
//        logger.debug("년도리스트 : {}", yearList);
//        Map<String, Object> result = new HashMap<>();
//        result.put("inputBoxCount", hblForm.getInputBoxCount());
//        result.put("yearList", yearList);
//        result.put("currentYear", now.getYear());
//        result.put("hblForm", hblForm);


        Map<String, Object> result = new HashMap<>();
        result.put("lookupResult", cargCsclPrgsInfoQryRtnVoTag);

        model.addAttribute("result", result);
        return "lookupResult";
    }
}
