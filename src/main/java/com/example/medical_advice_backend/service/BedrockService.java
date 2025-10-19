@Service
public class BedrockService {
    private final BedrockRuntimeClient client;
    @Value("${bedrock.modelId}") private String modelId;

    public BedrockService(BedrockRuntimeClient client) { this.client = client; }

    public String invoke(String requestJson) {
        InvokeModelRequest req = InvokeModelRequest.builder()
                .modelId(modelId)
                .contentType("application/json")
                .accept("application/json")
                .body(SdkBytes.fromUtf8String(requestJson))
                .build();

        InvokeModelResponse resp = client.invokeModel(req);
        String body = resp.body().asUtf8String();

        // gpt-oss-20b returns a text field; parse accordingly
        // Example: {"outputText":"..."} or provider-specific schema.
        try {
            com.fasterxml.jackson.databind.JsonNode n =
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(body);
            if (n.has("outputText")) return n.get("outputText").asText();   // common text field
            if (n.has("content")) return n.get("content").get(0).get("text").asText(); // Anthropic-style
            return body; // fallback
        } catch (Exception e) { return body; }
    }
}