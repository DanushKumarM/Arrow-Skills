package com.as.enrollment_service.util;

import com.as.enrollment_service.repository.CertificateAssetsRepository;
import com.as.enrollment_service.model.CertificateAssets;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificatePdfGenerator {

    private final CertificateAssetsRepository certificateAssetsRepository;

    public byte[] generate(String studentName, String courseTitle, String issueDate, String certificateId) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, out);
        document.open();

        // 👉 Load assets
        byte[] bgBytes = getAsset("background");
        byte[] logoBytes = getAsset("logo");
        byte[] signBytes = getAsset("signature");

        // ✅ Add background image
        if (bgBytes != null) {
            Image bg = Image.getInstance(bgBytes);
            bg.scaleAbsolute(PageSize.A4.getHeight(), PageSize.A4.getWidth());
            bg.setAbsolutePosition(0, 0);
            document.add(bg);
        }

        // ✅ Add logo
        if (logoBytes != null) {
            Image logo = Image.getInstance(logoBytes);
            logo.scaleToFit(100, 100);
            logo.setAbsolutePosition(40, 490);
            document.add(logo);
        }

        // 📝 Add certificate content
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 16);

        document.add(new Paragraph("\n\n\n\n\n"));

        Paragraph p1 = new Paragraph("Certificate of Completion", titleFont);
        p1.setAlignment(Element.ALIGN_CENTER);
        document.add(p1);

        Paragraph p2 = new Paragraph("This certifies that", regularFont);
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        Paragraph p3 = new Paragraph(studentName, titleFont);
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);

        Paragraph p4 = new Paragraph("has successfully completed the course:", regularFont);
        p4.setAlignment(Element.ALIGN_CENTER);
        document.add(p4);

        Paragraph p5 = new Paragraph(courseTitle, titleFont);
        p5.setAlignment(Element.ALIGN_CENTER);
        document.add(p5);

        Paragraph p6 = new Paragraph("Issued on: " + issueDate, regularFont);
        p6.setAlignment(Element.ALIGN_CENTER);
        document.add(p6);

        Paragraph p7 = new Paragraph("Certificate ID: " + certificateId, regularFont);
        p7.setAlignment(Element.ALIGN_CENTER);
        document.add(p7);


        // ✅ Add signature
        if (signBytes != null) {
            Image sign = Image.getInstance(signBytes);
            sign.scaleToFit(100, 50);
            sign.setAbsolutePosition(700, 50);
            document.add(sign);
        }

        document.close();
        return out.toByteArray();
    }

    private byte[] getAsset(String name) {
        return certificateAssetsRepository.findByName(name).map(CertificateAssets::getImage).orElse(null);
    }
}
