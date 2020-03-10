package com.cerner.careaware.build.solution.capability;

import com.cerner.careaware.build.model.CapabilityEntity;

public class EligibilityRulesCapabilityFactory {

  public static CapabilityEntity buildCapability() throws Exception {
    final CapabilityEntity capability = new CapabilityEntity("EligibilityRule").group("Services");
    capability.display("EligibilityRules").description("CFC Eligibility Rules");
    capability.requireService("CoreFinancialClearanceEligibilityRulesService");
    return capability;
  }
}
