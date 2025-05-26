package tn.esprit.maintenanceservice.controllers;

import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ChatbotController {

    @PostMapping("/chat")
    public Response chat(@RequestBody Request request) {
        String question = request.getQuestion();
        if (question != null && question.trim().isEmpty()) {
            return new Response("Bonjour ! Comment puis-je vous aider aujourd'hui ? N'hésitez pas à poser votre question.");
        }
        String response = getAnswerToQuestion(question);
        return new Response(response);
    }

    private String getAnswerToQuestion(String question) {
        String lowerCaseQuestion = question.toLowerCase();
        if (lowerCaseQuestion.contains("bonjour") || lowerCaseQuestion.contains("salut")) {
            return "Bonjour ! Que puis-je faire pour vous aujourd'hui ?";
        } else if (lowerCaseQuestion.contains("comment déclarer une panne")) {
            return "Pour déclarer une panne, veuillez vous rendre dans la section 'Déclarer une panne' du menu principal et suivre les étapes indiquées dans le formulaire.";
        } else if (lowerCaseQuestion.contains("manuel d'utilisation") || lowerCaseQuestion.contains("documentation équipement")) {
            return "Les manuels d'utilisation des équipements sont généralement disponibles dans la section 'Mes documents'. Si vous ne le trouvez pas, n'hésitez pas à contacter le support technique.";
        } else if (lowerCaseQuestion.contains("état de ma demande") || lowerCaseQuestion.contains("suivi de ma demande")) {
            return "Vous pouvez consulter l'état de votre demande dans la section 'Mes documents' sous l'onglet 'Demande'. Les informations de suivi y sont mises à jour régulièrement.";
        } else if (lowerCaseQuestion.contains("modifier mes informations") || lowerCaseQuestion.contains("changer mon profil")) {
            return "Pour modifier vos informations personnelles, accédez à la section 'Mon profil' et vous y trouverez les options nécessaires pour mettre à jour vos données.";
        } else if (lowerCaseQuestion.contains("message d'erreur") || lowerCaseQuestion.contains("écran bloqué")) {
            return "Veuillez noter le message d'erreur exact et le décrire dans une nouvelle déclaration de panne. Cela aidera le support technique à diagnostiquer le problème plus rapidement.";
        } else if (lowerCaseQuestion.contains("sauvegarde de mes fichiers") || lowerCaseQuestion.contains("backup de données")) {
            return "Oui, une procédure de sauvegarde des fichiers est disponible dans la section 'Mes documents'. Nous vous recommandons de la suivre régulièrement pour éviter toute perte de données.";
        } else if (lowerCaseQuestion.contains("contacter en cas d'urgence") || lowerCaseQuestion.contains("support urgent")) {
            return "En cas de problème urgent, veuillez contacter directement le support technique par téléphone au [Numéro de téléphone du support]. Vous trouverez également ces informations dans la section 'Contact' de la plateforme.";
        } else if (lowerCaseQuestion.contains("installer un logiciel") || lowerCaseQuestion.contains("nouveau programme")) {
            return "L'installation de nouveaux logiciels est généralement gérée par l'équipe informatique. Veuillez soumettre une demande via la section 'Demande' en précisant le logiciel souhaité et la raison de son installation.";
        } else if (lowerCaseQuestion.contains("heure")) {
            return "L'heure actuelle est " + LocalTime.now().toString();
        } else if (lowerCaseQuestion.contains("quel est ton nom")) {
            return "Je suis votre assistant virtuel pour la gestion du parc informatique. Comment puis-je vous aider ?";
        } else {
            return "Je suis désolé, je n'ai pas bien compris votre question. Pourriez-vous la reformuler s'il vous plaît ?";
        }
    }
}

class Request {
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

class Response {
    private String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}