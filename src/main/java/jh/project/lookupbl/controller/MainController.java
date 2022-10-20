package jh.project.lookupbl.controller;

import jh.project.lookupbl.dto.Hbl;
import jh.project.lookupbl.form.HblForm;
import jh.project.lookupbl.service.MainService;
import jh.project.lookupbl.xmlObject.CargCsclPrgsInfoQryRtnVoTag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String test(@ModelAttribute HblForm hblForm) {
        //널체크 null이면 최초진입
        if (hblForm.getBlYy() == null) {
            hblForm.setBlYy("2020");
        }
        return "test";
    }

    @PostMapping("/test")
    public String testPost(Model model, @ModelAttribute HblForm hblForm) throws Exception {
        //여러개 입력 시 구분자(,) 로 구분돼서 담아짐

        //공백제거
        hblForm.setHblNo(hblForm.getHblNo().replaceAll(" ", ""));
        hblForm.setBlYy(hblForm.getBlYy().replaceAll(" ", ""));

        //이슈
        //구분자를 통해서 입력이 들어오면 비엘년도와 비엘번호 갯수가 달라짐.
        //이슈

        String[] hblNoArry = hblForm.getHblNo().split(",");
        String[] hblYyArry = hblForm.getBlYy().split(",");

        List<HblForm> hblList = new ArrayList<>();

        for (int i = 0; i < hblNoArry.length; i++) {
            HblForm tempHbl = new HblForm();
            tempHbl.setBlYy(hblYyArry[i]);
            tempHbl.setHblNo(hblNoArry[i]);
            hblList.add(tempHbl);
        }


        List<Hbl> extractHblList = new ArrayList<>();

        for(int i =0; i < hblList.size(); i++) {
            extractHblList.addAll(mainService.extractHblNo(hblList.get(i)));
        }
        HblForm hblForm1 = new HblForm();
        for (int i = 0; i < extractHblList.size(); i++) {
            System.out.println(i+1+"번째 bl" + extractHblList.get(i).getHblYear() + " / " + extractHblList.get(i).getHblNo());
            hblForm1.setBlYy(extractHblList.get(i).getHblYear());
            hblForm1.setHblNo(extractHblList.get(i).getHblNo());
        }


        List<CargCsclPrgsInfoQryRtnVoTag> cargCsclPrgsInfoQryRtnVoTag = mainService.lookupHbl(hblList);

        logger.info(cargCsclPrgsInfoQryRtnVoTag.toString());

        return "test";
    }
}
