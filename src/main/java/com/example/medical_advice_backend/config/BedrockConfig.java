@Configuration
public class BedrockConfig {
    @Value("${bedrock.region}") private String region;

    @Bean
    BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder()
                .region(software.amazon.awssdk.regions.Region.of(region))
                .build(); // uses DefaultCredentialsProvider
    }
}