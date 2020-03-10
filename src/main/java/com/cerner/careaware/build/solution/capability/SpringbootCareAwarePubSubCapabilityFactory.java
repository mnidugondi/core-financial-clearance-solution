package com.cerner.careaware.build.solution.capability;

import com.cerner.careaware.build.model.CapabilityEntity;

public class SpringbootCareAwarePubSubCapabilityFactory {
	
	public static CapabilityEntity buildCapability() throws Exception{
        final CapabilityEntity capability = new CapabilityEntity("SpringbootCareAwarePubSub").group("Services");
        capability.display("SpringbootCareAwarePubSub").description("SpringbootCareAwarePubSub");
        capability.requireService("SpringbootMicroserviceCareawarePubSub");
        return capability;
    }

}
