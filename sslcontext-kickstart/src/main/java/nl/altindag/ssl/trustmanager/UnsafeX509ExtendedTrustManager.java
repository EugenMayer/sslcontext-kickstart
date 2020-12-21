/*
 * Copyright 2019-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.altindag.ssl.trustmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import java.net.Socket;
import java.security.cert.X509Certificate;

/**
 * An insecure {@link UnsafeX509ExtendedTrustManager TrustManager} that trusts all X.509 certificates without any verification.
 * <p>
 * <strong>NOTE:</strong>
 * Never use this {@link UnsafeX509ExtendedTrustManager} in production.
 * It is purely for testing purposes, and thus it is very insecure.
 * </p>
 * <br>
 * Suppressed warning: java:S4830 - "Server certificates should be verified during SSL/TLS connections"
 *                                  This TrustManager doesn't validate certificates and should not be used at production.
 *                                  It is just meant to be used for testing purposes and it is designed not to verify server certificates.
 *
 * @author Hakan Altindag
 */
@SuppressWarnings("java:S4830")
public final class UnsafeX509ExtendedTrustManager extends X509ExtendedTrustManager {

    public static final UnsafeX509ExtendedTrustManager INSTANCE = new UnsafeX509ExtendedTrustManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(UnsafeX509ExtendedTrustManager.class);
    private static final X509Certificate[] EMPTY_X509_CERTIFICATES = new X509Certificate[0];
    private static final String CLIENT_CERTIFICATE_LOG_MESSAGE = "Accepting a client certificate: [{}]";
    private static final String SERVER_CERTIFICATE_LOG_MESSAGE = "Accepting a server certificate: [{}]";

    private UnsafeX509ExtendedTrustManager() {}

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(CLIENT_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType, Socket socket) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(CLIENT_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType, SSLEngine sslEngine) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(CLIENT_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(SERVER_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType, Socket socket) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(SERVER_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType, SSLEngine sslEngine) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(SERVER_CERTIFICATE_LOG_MESSAGE, x509Certificates[0].getSubjectDN());
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return EMPTY_X509_CERTIFICATES;
    }

}