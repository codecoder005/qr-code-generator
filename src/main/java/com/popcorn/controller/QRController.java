package com.popcorn.controller;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.popcorn.request.QRCodeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api/v1/qrcode")
@RequiredArgsConstructor
@Slf4j
public class QRController {
    private final Gson jsonHelper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generateQRCode(@RequestBody QRCodeData qrCodeData) throws Exception {
        log.info("QRController::generateQRCode data = {}", jsonHelper.toJson(qrCodeData));
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(jsonHelper.toJson(qrCodeData), BarcodeFormat.QR_CODE, 200, 200);
        return new ResponseEntity<>(MatrixToImageWriter.toBufferedImage(bitMatrix), HttpStatus.OK);
    }
}
