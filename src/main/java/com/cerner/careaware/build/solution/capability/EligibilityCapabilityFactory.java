package com.cerner.careaware.build.solution.capability;

import com.cerner.careaware.build.model.CapabilityEntity;

public class EligibilityCapabilityFactory{

    public static CapabilityEntity buildCapability() throws Exception{
        final CapabilityEntity capability = new CapabilityEntity("Eligibility").group("Services");
        capability.display("Eligibility").description("Eligibility");
        capability.requireService("CoreFinancialClearanceEligibilityService");
        return capability;
    }
}