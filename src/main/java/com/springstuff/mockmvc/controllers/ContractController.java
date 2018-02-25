package com.springstuff.mockmvc.controllers;

import com.springstuff.modell.Contract;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(ContractController.CONTRACT_URI)
public class ContractController {

    public static final String CONTRACT_URI = "svc/v4/contracts";

    @RequestMapping(value = "{contractNumber}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Contract getContract(@PathVariable final int contractNumber) {
        Contract contract = new Contract();
        contract.setName("jay");
        contract.setContractNumber(contractNumber);
        return contract;
    }

}
