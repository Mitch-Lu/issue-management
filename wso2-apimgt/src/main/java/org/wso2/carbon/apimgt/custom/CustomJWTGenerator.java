package org.wso2.carbon.apimgt.custom;

import java.util.Map;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.impl.utils.APIUtil;
import org.wso2.carbon.apimgt.keymgt.service.TokenValidationContext;
import org.wso2.carbon.apimgt.keymgt.token.JWTGenerator;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

public class CustomJWTGenerator extends JWTGenerator
{
	@Override
	public Map<String, String> populateStandardClaims(final TokenValidationContext validationContext)
		throws APIManagementException
	{
		final Map<String, String> claims = super.populateStandardClaims(validationContext);
		final String dialect = getDialectURI();
		if (claims.get(dialect + "/enduser") != null)
		{
			String enduser = claims.get(dialect + "/enduser");
			if (enduser.endsWith("@carbon.super")) {
				enduser = enduser.replace("@carbon.super", "");
				claims.put(dialect + "/enduser", enduser);
			}
		}

		return claims;
	}

	@Override
	public Map<String, String> populateCustomClaims(final TokenValidationContext validationContext)
		throws APIManagementException
	{
		final CustomClaimRetriever claimsRetriever = (CustomClaimRetriever) getClaimsRetriever();
		if (claimsRetriever != null) {
			String tenantAwareUserName = validationContext.getValidationInfoDTO().getEndUserName();

			if (MultitenantConstants.SUPER_TENANT_ID == APIUtil.getTenantId(tenantAwareUserName)) {
				tenantAwareUserName = MultitenantUtils.getTenantAwareUsername(tenantAwareUserName);
			}

			return claimsRetriever.getClaims(tenantAwareUserName, validationContext.getAccessToken());
		}

		return null;
	}
}
