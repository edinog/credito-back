package br.com.credito.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Definindo o nome do tópico Kafka
    private static final String TOPIC = "consulta-eventos";

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Método para enviar eventos
    public void enviarEventoConsulta(String numeroNfse) {
        String mensagem = "Consulta realizada para o NFS-e: " + numeroNfse;
        kafkaTemplate.send(TOPIC, mensagem);
    }
}
