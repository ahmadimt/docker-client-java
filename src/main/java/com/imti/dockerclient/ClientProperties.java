package com.imti.dockerclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "docker.client")
public class ClientProperties {

  private String certPath;

  private String tlsVerify;

  private String host;

  public String getCertPath() {
    return certPath;
  }

  public void setCertPath(final String certPath) {
    this.certPath = certPath;
  }

  public String getTlsVerify() {
    return tlsVerify;
  }

  public void setTlsVerify(final String tlsVerify) {
    this.tlsVerify = tlsVerify;
  }

  public String getHost() {
    return host;
  }

  public void setHost(final String host) {
    this.host = host;
  }

  @Override
  public String toString() {
    return "ClientProperties{" +
        "certPath='" + certPath + '\'' +
        ", tlsVerify='" + tlsVerify + '\'' +
        ", host='" + host + '\'' +
        '}';
  }
}
