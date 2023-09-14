package com.drool.controller;

import org.springframework.web.bind.annotation.RestController;

import com.drool.entity.OrderRequest;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DecisionController {
	
	private final KieContainer kieContainer;
	
	public DecisionController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
	
	@PostMapping("/discount")
    private OrderRequest getDiscountPercent(@RequestBody OrderRequest orderRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(orderRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderRequest;
    }

}
