package com.cerner.careaware.build.solution.container;

import com.cerner.careaware.build.factory.ServiceFactory;
import com.cerner.careaware.build.model.container.ContainerEntity;
import com.cerner.careaware.datamodel.configuration.context.schema.ConfigurationSchema;
import com.cerner.careaware.datamodel.configuration.context.schema.SchemaElement;
import com.cerner.ibus.cfc.eligibilityrules.CfcEligibilityRulesService;
import com.cerner.ibus.cfc.eligibilityrules.context.config.DeployerConfig;
import com.cerner.icommand.ContextData;

public class EligibilityRulesContainerFactory {

  private static final String CORE_FINANCIAL_CLEARANCE_RULES_CONTAINER =
      "CoreFinancialClearanceEligibilityRulesContainer";
  private static final String CFC_RULES_CONFIGURATIONS_LABEL =
      "CFC Eligibility Rules Configurations";
  private static final String CORE_FINANCIAL_CLEARANCE_ELIGIBILITYRULES_SERVICE =
      "CoreFinancialClearanceEligibilityRulesService";

  public static ContainerEntity buildContainer() throws Exception {
    final ContainerEntity container = new ContainerEntity(CORE_FINANCIAL_CLEARANCE_RULES_CONTAINER);
    container.requireService(new RulesServiceFactory().buildService());
    return container;
  }

  public static class RulesServiceFactory extends ServiceFactory {

    public RulesServiceFactory() {
      super(EligibilityRulesContainerFactory.CORE_FINANCIAL_CLEARANCE_ELIGIBILITYRULES_SERVICE,
          CfcEligibilityRulesService.class);
    }

    @Override
    protected ConfigurationSchema createSchema() throws Exception {
     
      final SchemaElement configGroup =
          ELEMENT_LABEL(EligibilityRulesContainerFactory.CFC_RULES_CONFIGURATIONS_LABEL);

      configGroup.addElements(ELEMENT_DATABASE_CONFIG());

      final SchemaElement rulesEngineGroup =
          ELEMENT_GROUP("Rules Engine Configuration", "Rules Engine Configuration");
      rulesEngineGroup.addElements(ELEMENT_STRING(rulesEngineGroup, "URL:",
          "URL for rules engine. (Note: If using port, please follow this format 'IP Address:Port'",
          DeployerConfig.RULES_URL));
      rulesEngineGroup.addElements(ELEMENT_PASSWORD(rulesEngineGroup, "Token:",
          "Security token for rules engine endpoint", DeployerConfig.RULES_TOKEN));

      final SchemaElement readinessGroup =
          ELEMENT_GROUP("Readiness Health Check", "Readiness Health Check");
      final SchemaElement healthCheck =
          ELEMENT_BOOLEAN(readinessGroup, "Perform Rules Engine Health Check",
              "Do readiness health check for Rules", DeployerConfig.RULES_ENGINE_READINESS_CHECK);

      readinessGroup.addElements(healthCheck);

      return new ConfigurationSchema(configGroup, ELEMENT_SPACER(),
          rulesEngineGroup, ELEMENT_SPACER(), readinessGroup);
    }

    @Override
    protected ContextData getDefaultConfig() throws Exception {
      ContextData contextData = new ContextData();
      contextData.put(DeployerConfig.RULES_ENGINE_READINESS_CHECK, false);
      return contextData;
    }
  }
}
