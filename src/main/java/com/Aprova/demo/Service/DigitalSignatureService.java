package com.Aprova.demo.Service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import java.security.*;
import java.util.Base64;
import jakarta.annotation.PostConstruct;

@Service
public class DigitalSignatureService {
    private KeyPair keyPair;
    @PostConstruct
    public void init() throws NoSuchAlgorithmException, NoSuchProviderException {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
        keyGen.initialize(2048);
        this.keyPair = keyGen.generateKeyPair();
    }

    public String signData(String data) throws Exception {

        Signature signature = Signature.getInstance("SHA256withRSA", "BC");
        signature.initSign(keyPair.getPrivate());
        signature.update(data.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(signature.sign());
    }

    public boolean verifySignature(String data, String signatureBase64) throws Exception {
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);

        Signature signature = Signature.getInstance("SHA256withRSA", "BC");
        signature.initVerify(keyPair.getPublic());
        signature.update(data.getBytes("UTF-8"));

        return signature.verify(signatureBytes);
    }
}