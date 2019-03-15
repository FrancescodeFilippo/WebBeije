package it.beije.utils;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.bean.Domanda;
import it.beije.bean.Risposta;

public class Utils {

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();

		NodeList nodeList = element.getChildNodes();
		Node node = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				childElements.add((Element) node);
			}
		}

		return childElements;
	}

	public static List<Domanda> readFileDomande(String pathFile) {
		List<Domanda> arrayDomande = new ArrayList<Domanda>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// Load the input XML document, parse it and return an instance of the
			// Document class.
			Document document = builder.parse(new File(pathFile));
			Element element = document.getDocumentElement();
//	        System.out.println(element.getTagName());
			List<Element> domande = Utils.getChildElements(element);
//	        System.out.println(domande);

			Element domanda = null;
			List<Element> contenutoDomanda = null;
			for (int i = 0; i < domande.size(); i++) {
				domanda = domande.get(i);
				contenutoDomanda = Utils.getChildElements(domanda);
				int id = Integer.parseInt(domanda.getAttribute("id"));
				String book = domanda.getAttribute("book");
				int chapter = Integer.parseInt(domanda.getAttribute("chapter"));
				int question = Integer.parseInt(domanda.getAttribute("question"));
				String testo = contenutoDomanda.get(0).getTextContent();
				// Element risposte = contenutoDomanda.get(1);
				String risposta = contenutoDomanda.get(1).getTextContent();
				Risposta r = new Risposta();
				r.setRispostaEsatta(risposta);
				Domanda d = new Domanda(id, book, chapter, question, testo, r);
				arrayDomande.add(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayDomande;
	}

	public static String formattaTesto(String testo) {
		if (testo != null && testo.length() > 0) {
			testo = testo.replace("\n", "<br>").replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		}

		return testo;
	}

	public static boolean controllaRisposta(String rispostaEsatta, String risposta) {
		for (int i = 0; i < risposta.length(); i++) {
			char c = risposta.charAt(i);
			if (c == ' ' || c == ',')
				continue;
			if (rispostaEsatta.indexOf(c) < 0) {
				return false;// se non trovo la risposta termino il ciclo
			} else {
				// tolgo risposta esatta da elenco risposte esatte per evitare duplicati
				rispostaEsatta = rispostaEsatta.replace(Character.toString(c), "");
			}
		}

		return rispostaEsatta.length() == 0;
	}

	public static String formatDuration(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

}
