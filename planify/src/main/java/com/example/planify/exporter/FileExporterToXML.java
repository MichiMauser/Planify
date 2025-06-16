package com.example.planify.exporter;

import com.example.planify.dto.UserDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.StringWriter;

public class  FileExporterToXML {

    public void exportData(Object object) throws JAXBException {

        String xmlContent = null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File file = new File("E:\\AN3\\SEM2\\project-MichiMauser\\planify\\users.xml");

            jaxbMarshaller.marshal(object, file);

        } catch (JAXBException e) {
            throw new RuntimeException("Failed to export UserDTO to XML", e);
        }
    }
}
