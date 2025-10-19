@RestController
@RequestMapping("/api/llm")
public class ChatController {
    private final BedrockService svc;
    public ChatController(BedrockService svc) { this.svc = svc; }

    @PostMapping("/invoke")
    public ResponseEntity<String> invoke(@RequestBody String bodyJson) {
        return ResponseEntity.ok(svc.invoke(bodyJson));
    }
}