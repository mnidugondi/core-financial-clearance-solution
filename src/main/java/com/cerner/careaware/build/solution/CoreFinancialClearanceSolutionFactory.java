package com.cerner.careaware.build.solution;

import com.cerner.careaware.build.factory.SolutionFactory;
import com.cerner.careaware.build.model.SolutionEntity;
import com.cerner.careaware.build.solution.capability.EligibilityCapabilityFactory;
import com.cerner.careaware.build.solution.container.EligibilityContainerFactory;
import com.cerner.careaware.build.solution.container.EligibilityRulesContainerFactory;
import com.cerner.careaware.build.solution.container.SpringbootCareAwarePubSubContainerFactory;
import com.cerner.careaware.build.solution.capability.EligibilityRulesCapabilityFactory;
import com.cerner.careaware.build.solution.capability.SpringbootCareAwarePubSubCapabilityFactory;


public class CoreFinancialClearanceSolutionFactory extends SolutionFactory {

	private static final String CORE_FINANCIAL_CLEARANCE_IBUS_SOLUTION = "Core Financial Clearance iBus Solution";
	private static final String CFC_SERVICES = "Core Financial Clearance";

	@Override
	public void build(SolutionEntity solution) throws Exception {
		solution.display(CFC_SERVICES)
			.description(CORE_FINANCIAL_CLEARANCE_IBUS_SOLUTION);

		// Optional Capability: Order Alphabetically
		solution.optionalCapability(EligibilityCapabilityFactory.buildCapability());
		solution.optionalCapability(EligibilityRulesCapabilityFactory.buildCapability());
		solution.optionalCapability(SpringbootCareAwarePubSubCapabilityFactory.buildCapability());

		// Optional Services: Order Alphabetically
		solution.include(EligibilityContainerFactory.buildContainer());
		solution.include(EligibilityRulesContainerFactory.buildContainer());
		solution.include(SpringbootCareAwarePubSubContainerFactory.buildContainer());
		
	}

}
