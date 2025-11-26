package com.Aprova.demo.Controller;
import com.Aprova.demo.Service.DigitalSignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seguranca")
public class SignatureController {

    private final DigitalSignatureService signatureService;

    public SignatureController(DigitalSignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping(value = "/testar-bouncy-castle", produces = "text/html")
    public ResponseEntity<String> testSignature() {
        try {
            // documento VERDADEIRO q sera assinado para garantir a integridade
            String documentoOriginal = "ID Transacao: 12345; Valor: 1000.00; Usuario: Laura";

            // 1. Assina o documento
            String assinatura = signatureService.signData(documentoOriginal);

            // 2. Verifica com o documento original (SUCESSO = TRUE)
            boolean isValidaOriginal = signatureService.verifySignature(documentoOriginal, assinatura);

            // 3. Tenta verificar com um documento ADULTERADO (FALHA = FALSE)
            String documentoAdulterado = "ID Transacao: 12345; Valor: 9999.00; Usuario: Laura"; // Altera o valor
            boolean isValidaAdulterada = signatureService.verifySignature(documentoAdulterado, assinatura);

            // Retorna o resultado em HTML
            String htmlResponse = String.format(
                    "<h2> 🔑 Demonstração Bouncy Castle (Assinatura Digital) </h2>" +
                            "<p><b>Documento Original:</b> <code>%s</code></p>" +
                            "<p><b>Assinatura (Base64):</b> <code>%s</code></p>" +
                            "<hr>" +
                            "<h3>Resultados da Verificação:</h3>" +
                            "<p><b>Verificação (Dado Original):</b> <span style='color:green; font-weight:bold;'>%s</span></p>" +
                            "<p><b>Verificação (Dado Adulterado):</b> <span style='color:red; font-weight:bold;'>%s</span></p>" +
                            "<br/>" +
                            "<p>Isso prova que o Bouncy Castle garantiu a <b>integridade</b> do dado.</p>",
                    documentoOriginal, assinatura, isValidaOriginal, isValidaAdulterada
            );

            return ResponseEntity.ok(htmlResponse);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao executar a demonstração: " + e.getMessage());
        }
    }
}