package sih;
package com.example.uidai_face_encryption;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import javax.crypto.Cipher;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_ALIAS = "uidaiFaceKey";
    private ImageCapture imageCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the camera
        startCamera();

        // Generate or retrieve hardware-backed key pair
        try {
            KeyStoreHelper.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Capture face image and encrypt
        captureAndEncryptFaceImage();
    }

    // Start Camera for capturing the image
    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                imageCapture = new ImageCapture.Builder().build();
                cameraProvider.bindToLifecycle(this, null, imageCapture);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    // Capture and Encrypt Face Image
    private void captureAndEncryptFaceImage() {
        imageCapture.takePicture(ContextCompat.getMainExecutor(this), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                byte[] faceImageData = imageToByteArray(image);
                image.close();

                // Encrypt face image data
                try {
                    byte[] encryptedData = EncryptionHelper.encryptData(faceImageData);
                    // Store the encrypted image securely
                    FileHelper.saveEncryptedData(getApplicationContext(), encryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.e("ImageCapture", "Capture failed: " + exception.getMessage());
            }
        });
    }

    // Convert ImageProxy to byte array
    private byte[] imageToByteArray(ImageProxy image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        return byteArray;
    }

    // Helper class for generating and retrieving key pair from Android Keystore
    public static class KeyStoreHelper {

        public static void generateKeyPair() throws Exception {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");

            KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setKeySize(2048)
                    .build();

            keyPairGenerator.initialize(keyGenParameterSpec);
            keyPairGenerator.generateKeyPair();
        }

        public static KeyPair getKeyPair() throws Exception {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(KEY_ALIAS, null);

            if (privateKeyEntry != null) {
                return new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
            }
            return null;
        }
    }

    // Helper class for Encryption using the public key
    public static class EncryptionHelper {

        public static byte[] encryptData(byte[] data) throws Exception {
            KeyPair keyPair = KeyStoreHelper.getKeyPair();
            PublicKey publicKey = keyPair.getPublic();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        }
    }

    // Helper class for saving encrypted data securely to storage
    public static class FileHelper {

        public static void saveEncryptedData(Context context, byte[] encryptedData) {
            try (FileOutputStream fos = context.openFileOutput("encrypted_face_data", Context.MODE_PRIVATE)) {
                fos.write(encryptedData);
                Log.i("FileHelper", "Encrypted face data saved");
            } catch (Exception e) {
                Log.e("FileHelper", "Failed to save encrypted data: " + e.getMessage());
            }
        }
    }
}
