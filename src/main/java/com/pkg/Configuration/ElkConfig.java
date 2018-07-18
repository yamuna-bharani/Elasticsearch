package com.pkg.Configuration;

import com.pkg.Utils.Constants;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.pkg")
public class ElkConfig {

    @Value(Constants.ELK_HOST_PROP)
    private String EsHost;

    @Value(Constants.ELK_PORT_PROP)
    private int EsPort;

    @Value(Constants.ELK_CLUSTER_NAME_PROP)
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put(Constants.CLUSTER_NAME_PROP, EsClusterName)
                .build();

        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), Constants.ALIAS_PORT));

    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}