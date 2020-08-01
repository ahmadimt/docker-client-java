package com.imti.dockerclient;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.command.PingCmd;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.LocalDirectorySSLConfig;
import com.github.dockerjava.jaxrs.JerseyDockerHttpClient;
import com.github.dockerjava.jaxrs.JerseyDockerHttpClient.Builder;
import com.github.dockerjava.transport.DockerHttpClient;
import com.github.dockerjava.transport.SSLConfig;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = ClientProperties.class)
public class DockerClientApplication implements CommandLineRunner {

  @Autowired
  private ClientProperties clientProperties;


  public static void main(String[] args) {
    SpringApplication.run(DockerClientApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {
    final DefaultDockerClientConfig config
        = DefaultDockerClientConfig.createDefaultConfigBuilder()
        .withDockerCertPath(clientProperties.getCertPath())
        .withDockerTlsVerify(clientProperties.getTlsVerify())
        .withDockerHost(clientProperties.getHost())
        .build();

    final JerseyDockerHttpClient dockerHttpClient = new Builder()
        .dockerHost(config.getDockerHost())
        .sslConfig(config.getSSLConfig())
        .build();
    DockerClient dockerClient = DockerClientImpl.getInstance(config, dockerHttpClient);
    final List<Image> images = dockerClient.listImagesCmd().exec();
    images.forEach(image -> System.out.println(image.getId()));
  }
}
