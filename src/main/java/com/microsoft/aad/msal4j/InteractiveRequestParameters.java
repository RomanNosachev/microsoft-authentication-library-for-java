// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.aad.msal4j;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URI;
import java.net.URL;
import java.util.Set;

import static com.microsoft.aad.msal4j.ParameterValidationUtils.validateNotBlank;
import static com.microsoft.aad.msal4j.ParameterValidationUtils.validateNotEmpty;
import static com.microsoft.aad.msal4j.ParameterValidationUtils.validateNotNull;

/**
 * Object containing parameters for interactive requests. Can be used as parameter to
 * {@link PublicClientApplication#acquireToken(InteractiveRequestParameters)}.
 */
@Builder
@Accessors(fluent = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InteractiveRequestParameters {

    /**
     * Scopes that the application is requesting access to and the user will consent to.
     */
    @NonNull
    private Set<String> scopes;

    /**
     * Redirect URI where MSAL will listen to for the authorization code returned by Azure AD.
     * Should be a loopback URL with a port specified (for example, http://localhost:3671). If no
     * port is specified, MSAL will find an open port. For more information, see
     * https://aka.ms/msal4j-interactive-request.
     */
    @Setter(AccessLevel.PACKAGE)
    @NonNull
    private URI redirectUri;

    /**
     * Indicate the type of user interaction that is required.
     */
    private Prompt prompt;

    /**
     * Can be used to pre-fill the username/email address field of the sign-in page for the user,
     * if you know the username/email address ahead of time. Often apps use this parameter during
     * re-authentication, having already extracted the username from a previous sign-in using the
     * preferred_username claim.
     */
    private String loginHint;

    /**
     * Provides a hint about the tenant or domain that the user should use to sign in. The value
     * of the domain hint is a registered domain for the tenant.
     **/
    private String domainHint;

    /**
     * Sets {@link SystemBrowserOptions} to be used by the PublicClientApplication
     */
    private SystemBrowserOptions systemBrowserOptions;

    private static InteractiveRequestParametersBuilder builder() {
        return new InteractiveRequestParametersBuilder();
    }

    public static InteractiveRequestParametersBuilder builder(Set<String> scopes, URI redirectUri) {

        validateNotEmpty("scopes", scopes);
        validateNotNull("redirect_uri", redirectUri);

        return builder()
                .scopes(scopes)
                .redirectUri(redirectUri);
    }
}