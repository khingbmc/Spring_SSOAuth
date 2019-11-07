/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.springframework.security.saml.provider.provisioning;

import org.springframework.security.saml.SamlMetadataCache;
import org.springframework.security.saml.SamlTransformer;
import org.springframework.security.saml.SamlValidator;
import org.springframework.security.saml.key.SimpleKey;
import org.springframework.security.saml.provider.config.LocalProviderConfiguration;
import org.springframework.security.saml.provider.config.SamlConfigurationRepository;
import org.springframework.security.saml.provider.identity.IdentityProviderService;
import org.springframework.security.saml.provider.identity.config.LocalIdentityProviderConfiguration;
import org.springframework.security.saml.provider.service.ServiceProviderService;
import org.springframework.security.saml.provider.service.config.LocalServiceProviderConfiguration;
import org.springframework.security.saml.saml2.metadata.Binding;
import org.springframework.security.saml.saml2.metadata.Endpoint;
import org.springframework.security.saml.saml2.metadata.IdentityProviderMetadata;
import org.springframework.security.saml.saml2.metadata.NameId;
import org.springframework.security.saml.saml2.metadata.ServiceProviderMetadata;
import org.springframework.security.saml.saml2.signature.AlgorithmMethod;
import org.springframework.security.saml.saml2.signature.DigestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.springframework.security.saml.saml2.metadata.Binding.REDIRECT;
import static org.springframework.util.StringUtils.hasText;

public abstract class AbstractHostbasedSamlProviderProvisioning {

	private final SamlConfigurationRepository configuration;
	private final SamlTransformer transformer;
	private final SamlValidator validator;
	private final SamlMetadataCache cache;

	public AbstractHostbasedSamlProviderProvisioning(SamlConfigurationRepository configuration,
													 SamlTransformer transformer,
													 SamlValidator validator,
													 SamlMetadataCache cache) {
		this.configuration = configuration;
		this.transformer = transformer;
		this.validator = validator;
		this.cache = cache;
	}

	public SamlConfigurationRepository getConfigurationRepository() {
		return configuration;
	}

	protected IdentityProviderService getHostedIdentityProvider(LocalIdentityProviderConfiguration idpConfig) {
		return null;
	}

	protected String getAliasPath(LocalProviderConfiguration configuration) {
		return hasText(configuration.getAlias()) ?
			UriUtils.encode(configuration.getAlias(), StandardCharsets.ISO_8859_1.name()) :
			UriUtils.encode(configuration.getEntityId(), StandardCharsets.ISO_8859_1.name());
	}

	protected IdentityProviderMetadata identityProviderMetadata(String baseUrl,
																SimpleKey signingKey,
																List<SimpleKey> keys,
																String prefix,
																String aliasPath,
																AlgorithmMethod signAlgorithm,
																DigestMethod signDigest) {

		return new IdentityProviderMetadata()
			.setEntityId(baseUrl)
			.setId("IDPM"+UUID.randomUUID().toString())
			.setSigningKey(signingKey, signAlgorithm, signDigest)
			.setProviders(
				asList(
					new org.springframework.security.saml.saml2.metadata.IdentityProvider()
						.setWantAuthnRequestsSigned(true)
						.setSingleSignOnService(
							asList(
								getEndpoint(baseUrl, prefix + "SSO/alias/" + aliasPath, Binding.POST, 0, true),
								getEndpoint(baseUrl, prefix + "SSO/alias/" + aliasPath, REDIRECT, 1, false)
							)
						)
						.setNameIds(asList(NameId.PERSISTENT, NameId.EMAIL))
						.setKeys(keys)
						.setSingleLogoutService(
							asList(
								getEndpoint(baseUrl, prefix + "logout/alias/" + aliasPath, REDIRECT, 0, true)
							)
						)
				)
			);

	}

	public SamlTransformer getTransformer() {
		return transformer;
	}

	public SamlValidator getValidator() {
		return validator;
	}

	public SamlMetadataCache getCache() {
		return cache;
	}

	protected Endpoint getEndpoint(String baseUrl, String path, Binding binding, int index, boolean isDefault) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
		builder.pathSegment(path);
		return getEndpoint(builder.build().toUriString(), binding, index, isDefault);
	}

	protected Endpoint getEndpoint(String url, Binding binding, int index, boolean isDefault) {
		return
			new Endpoint()
				.setIndex(index)
				.setBinding(binding)
				.setLocation(url)
				.setDefault(isDefault)
				.setIndex(index);
	}

	protected ServiceProviderService getHostedServiceProvider(LocalServiceProviderConfiguration spConfig) {
		return null;
	}

	protected ServiceProviderMetadata serviceProviderMetadata(String baseUrl,
															  SimpleKey signingKey,
															  List<SimpleKey> keys,
															  String prefix,
															  String aliasPath,
															  AlgorithmMethod signAlgorithm,
															  DigestMethod signDigest) {

		return new ServiceProviderMetadata()
			.setEntityId(baseUrl)
			.setId("SPM"+UUID.randomUUID().toString())
			.setSigningKey(signingKey, signAlgorithm, signDigest)
			.setProviders(
				asList(
					new org.springframework.security.saml.saml2.metadata.ServiceProvider()
						.setKeys(keys)
						.setWantAssertionsSigned(true)
						.setAuthnRequestsSigned(signingKey != null)
						.setAssertionConsumerService(
							asList(
								getEndpoint(baseUrl, prefix + "SSO/alias/" + aliasPath, Binding.POST, 0, true),
								getEndpoint(baseUrl, prefix + "SSO/alias/" + aliasPath, REDIRECT, 1, false)
							)
						)
						.setNameIds(asList(NameId.PERSISTENT, NameId.EMAIL))
						.setKeys(keys)
						.setSingleLogoutService(
							asList(
								getEndpoint(baseUrl, prefix + "logout/alias/" + aliasPath, REDIRECT, 0, true)
							)
						)
				)
			);
	}


}
