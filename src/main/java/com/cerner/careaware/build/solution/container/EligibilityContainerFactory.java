package com.cerner.careaware.build.solution.container;

import com.cerner.careaware.build.factory.ServiceFactory;
import com.cerner.careaware.build.model.container.ContainerEntity;
import com.cerner.careaware.datamodel.configuration.context.schema.ConfigurationSchema;
import com.cerner.careaware.datamodel.configuration.context.schema.SchemaElement;
import com.cerner.ibus.cfc.eligibility.CfcEligibilityService;
import com.cerner.ibus.cfc.eligibility.context.config.DeployerConfig;
import com.cerner.icommand.ContextData;

public class EligibilityContainerFactory {

	private static final String CORE_FINANCIAL_CLEARANCE_ELIGIBILITY_CONTAINER = "CoreFinancialClearanceEligibilityContainer";
	private static final String CFC_ELIGIBILITY_CONFIGURATIONS_LABEL = "CFC Eligibility Configurations";
	private static final String CORE_FINANCIAL_CLEARANCE_ELIGIBILITY_SERVICE = "CoreFinancialClearanceEligibilityService";


	public static ContainerEntity buildContainer() throws Exception {
		final ContainerEntity container = new ContainerEntity(CORE_FINANCIAL_CLEARANCE_ELIGIBILITY_CONTAINER);
		container.requireService(new EligibilityServiceFactory().buildService());
		return container;
	}

	public static class EligibilityServiceFactory extends ServiceFactory {

		public EligibilityServiceFactory() {
			super(EligibilityContainerFactory.CORE_FINANCIAL_CLEARANCE_ELIGIBILITY_SERVICE, CfcEligibilityService.class);
		}

		@Override
		protected ConfigurationSchema createSchema() throws Exception {
			
			final SchemaElement configGroup = ELEMENT_LABEL(EligibilityContainerFactory.CFC_ELIGIBILITY_CONFIGURATIONS_LABEL);

			configGroup.addElements(ELEMENT_DATABASE_CONFIG());
			
			
			  final SchemaElement rulesStatus = ELEMENT_STRING("Eligibility Rules Service", null,
			  DeployerConfig.RULES_STATUS);
			  rulesStatus.addConditions(IF(ALWAYS()).enforce("Disabled", "Enabled"));
			 
		      final SchemaElement automaticProcessingGroup =
		          ELEMENT_GROUP("Automatic Processing flag" , "Automatic Processing flag");
		      final SchemaElement automaticProcessing =
		          ELEMENT_BOOLEAN(automaticProcessingGroup, "Vendor Non-CFC Automatic Processing flag",
		              "Automatic Processing Indicator for Vendor Rule Results", DeployerConfig.AUTOMATIC_PROCESSING_FLAG);

		      automaticProcessingGroup.addElements(automaticProcessing);

			return new ConfigurationSchema(configGroup,ELEMENT_SPACER(),rulesStatus ,ELEMENT_SPACER(),automaticProcessingGroup);
		}

		@Override
		protected ContextData getDefaultConfig() throws Exception {
			ContextData contextData= new ContextData();
			contextData.put(DeployerConfig.AUTOMATIC_PROCESSING_FLAG, false);
			contextData.put(DeployerConfig.RULES_STATUS, "Disabled");
			return contextData;
		}

	}




}
