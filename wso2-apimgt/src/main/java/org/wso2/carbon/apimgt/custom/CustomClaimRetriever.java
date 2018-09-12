package org.wso2.carbon.apimgt.custom;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.cache.Cache;
import javax.cache.Caching;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.impl.internal.ServiceReferenceHolder;
import org.wso2.carbon.apimgt.impl.token.ClaimsRetriever;
import org.wso2.carbon.apimgt.impl.utils.APIUtil;
import org.wso2.carbon.apimgt.impl.utils.ClaimCacheKey;
import org.wso2.carbon.apimgt.impl.utils.UserClaims;
import org.wso2.carbon.user.api.ClaimManager;
import org.wso2.carbon.user.api.ClaimMapping;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

public class CustomClaimRetriever implements ClaimsRetriever
{
	private final static String dialectURI = ClaimsRetriever.DEFAULT_DIALECT_URI;
	private Cache<Object, Object> claimsLocalCache;

	@Override
	public void init() throws APIManagementException
	{
		claimsLocalCache = getClaimsLocalCache();
	}

	protected Cache<Object, Object> getClaimsLocalCache()
	{
		return Caching.getCacheManager("API_MANAGER_CACHE").getCache("claimsLocalCache");
	}

	@Override
	public SortedMap<String, String> getClaims(final String endUserName) throws APIManagementException
	{
		SortedMap<String, String> claimValues;
		try {
			final int tenantId = APIUtil.getTenantId(endUserName);
			// check in local cache
			final String key = endUserName + ":" + tenantId;
			final ClaimCacheKey cacheKey = new ClaimCacheKey(key);
			// Object result = claimsLocalCache.getValueFromCache(cacheKey);
			final Object result = claimsLocalCache.get(cacheKey);
			if (result != null) {
				claimValues = ((UserClaims) result).getClaimValues();
			}
			else {
				final ClaimManager claimManager = ServiceReferenceHolder.getInstance().getRealmService()
					.getTenantUserRealm(tenantId).getClaimManager();
				final ClaimMapping[] claims = claimManager.getAllClaimMappings(dialectURI);
				final String[] claimURIs = claimMappingToURI(claims);
				final UserStoreManager userStoreManager = ServiceReferenceHolder.getInstance().getRealmService()
					.getTenantUserRealm(tenantId).getUserStoreManager();

				String tenantAwareUserName = endUserName;
				if (MultitenantConstants.SUPER_TENANT_ID != tenantId) {
					tenantAwareUserName = MultitenantUtils.getTenantAwareUsername(endUserName);
				}
				claimValues = new TreeMap<>(userStoreManager.getUserClaimValues(tenantAwareUserName, claimURIs, null));
				final UserClaims userClaims = new UserClaims(claimValues);
				// add to cache
				claimsLocalCache.put(cacheKey, userClaims);
			}
		}
		catch (final UserStoreException e) {
			throw new APIManagementException("Error while retrieving user claim values from user store", e);
		}
		return claimValues;
	}

	public SortedMap<String, String> getClaims(final String endUserName, final String accessToken)
		throws APIManagementException
	{
		SortedMap<String, String> claimValues;
		try {
			final int tenantId = APIUtil.getTenantId(endUserName);
			// check in local cache
			final String key = endUserName + ":" + tenantId + ":" + accessToken;
			final ClaimCacheKey cacheKey = new ClaimCacheKey(key);
			// Object result = claimsLocalCache.getValueFromCache(cacheKey);
			final Object result = claimsLocalCache.get(cacheKey);
			if (result != null) {
				claimValues = ((UserClaims) result).getClaimValues();
			}
			else {
				final ClaimManager claimManager = ServiceReferenceHolder.getInstance().getRealmService()
					.getTenantUserRealm(tenantId).getClaimManager();
				// Claim[] claims = claimManager.getAllClaims(dialectURI);
				final ClaimMapping[] claims = claimManager.getAllClaimMappings(dialectURI);
				final String[] claimURIs = claimMappingToURI(claims);
				final UserStoreManager userStoreManager = ServiceReferenceHolder.getInstance().getRealmService()
					.getTenantUserRealm(tenantId).getUserStoreManager();

				String tenantAwareUserName = endUserName;
				if (MultitenantConstants.SUPER_TENANT_ID != tenantId) {
					tenantAwareUserName = MultitenantUtils.getTenantAwareUsername(endUserName);
				}
				claimValues = new TreeMap<>(userStoreManager.getUserClaimValues(tenantAwareUserName, claimURIs, "Admin"));
				final UserClaims userClaims = new UserClaims(claimValues);
				// add to cache
				claimsLocalCache.put(cacheKey, userClaims);
			}
		}
		catch (final UserStoreException e) {
			throw new APIManagementException("Error while retrieving user claim values from user store", e);
		}
		return claimValues;
	}

	@Override
	public String getDialectURI(final String endUserName) throws APIManagementException
	{
		return dialectURI;
	}

	private String[] claimMappingToURI(final ClaimMapping[] claims)
	{
		final Stream<ClaimMapping> claimStream = Stream.of(claims);
		final String[] claimURIs = claimStream.map(claim -> claim.getClaim().getClaimUri()).toArray(String[]::new);

		return claimURIs;
	}
}
