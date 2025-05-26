package tn.esprit.maintenanceservice.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.services.IProgrammeMaintenance;

import java.time.LocalDate;
import java.util.List;

@Component
public class MaintenanceReminderScheduler {

    private final IProgrammeMaintenance programmeMaintenanceService;

    public MaintenanceReminderScheduler(IProgrammeMaintenance programmeMaintenanceService) {
        this.programmeMaintenanceService = programmeMaintenanceService;
    }

    // Cette tâche s'exécute chaque jour à 8h00 du matin
    // Ou toutes les minutes pour les tests (pour les tests, vous pouvez utiliser fixedRate = 60000)
    @Scheduled(cron = "0 0 8 * * ?") // Exécuter tous les jours à 8h00
    // @Scheduled(fixedRate = 60000) // Pour tester toutes les minutes
    public void checkUpcomingMaintenance() {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysFromNow = today.plusDays(7); // Rappel 7 jours avant

        List<ProgrammeMaintenance> allProgrammes = programmeMaintenanceService.getAllProgrammesMaintenance();

        for (ProgrammeMaintenance programme : allProgrammes) {
            LocalDate nextDueDate = programme.getNextDueDate();

            if (nextDueDate != null) {
                // Si la maintenance est due aujourd'hui
                if (nextDueDate.isEqual(today)) {
                    System.out.println("RAPPEL IMPORTANT : Le programme de maintenance '" + programme.getPlanAction() + "' est dû AUJOURD'HUI. Responsable: " + programme.getResponsable());
                    // Ici, vous enverriez un e-mail ou une notification.
                    // Exemple: emailService.sendReminderEmail(programme.getResponsableEmail(), "Maintenance Due Today", "...");
                }
                // Si la maintenance est due dans les 7 prochains jours
                else if (nextDueDate.isAfter(today) && nextDueDate.isBefore(sevenDaysFromNow.plusDays(1))) {
                    System.out.println("Rappel : Le programme de maintenance '" + programme.getPlanAction() + "' est dû le " + nextDueDate + ". Responsable: " + programme.getResponsable());
                    // Ici, vous enverriez un e-mail ou une notification préventive.
                }
            }
        }
    }
}