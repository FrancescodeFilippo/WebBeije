package it.beije.quiz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.bean.Domanda;
import it.beije.utils.Utils;


/**
 * Servlet implementation class StartQuiz
 */
@WebServlet({ "/StartQuiz", "/risposta" })
public class StartQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().equals(request.getContextPath()+"/StartQuiz")) {
			List<Domanda> domande = Utils.readFileDomande("C:\\temp\\test.xml");
			
			request.getSession().putValue("elencoDomande", domande);
			//request.getSession().putValue("indice", new Integer(0));
			
			//response.getWriter().append("Totale domande caricate: ").append(""+domande.size());
			
			response.sendRedirect("domanda.jsp?index=0");
		} else {
			//ELABORAZIONE RISPOSTE
			List<Domanda> domande = (List) request.getSession().getValue("elencoDomande");
			if (domande != null) {
				for (Domanda d : domande) {
					boolean corretta = Utils.controllaRisposta(d.getRisposta().getRispostaEsatta(), d.getRisposta().getRispostaUtente());
					
					response.setContentType("text/html");
					response.getWriter().append("DOMANDA " + d.getId() + " : la tua risposta : " + d.getRisposta().getRispostaUtente() + "<br><br>");
					if (corretta) {
						response.getWriter().append("ESATTO!!! :)<br>");
					} else {
						response.getWriter().append("La risposta esatta era " +  d.getRisposta().getRispostaEsatta() + " :(<br>");
					}
					
					response.getWriter().append("<br><br>");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Domanda> domande = (List) request.getSession().getValue("elencoDomande");
		
		if (domande != null) {
			String rispostaUtente = request.getParameter("rispostaUtente");
			String index = request.getParameter("index");
			int i = 0;
			if (rispostaUtente != null) {
				rispostaUtente = rispostaUtente.trim().toUpperCase();
				if (index != null) {
					i = Integer.parseInt(index);
					domande.get(i).getRisposta().setRispostaUtente(rispostaUtente);
					//request.getSession().putValue("indice", new Integer(++indice));
				}
			}
			
			response.sendRedirect("domanda.jsp?index=" + ++i);
		} else {
			response.getWriter().append("SESSIONE SCADUTA! RICARICARE LE DOMANDE");
		}
	}

}
