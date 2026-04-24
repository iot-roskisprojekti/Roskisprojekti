package fi.roskisprojekti.adapter.in.rest.controller.task;

import fi.roskisprojekti.application.port.in.notification.SendNotificationUseCase;
import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TaskController {

    private final ManageTasksUseCase manageTasksUseCase;
    private final SendNotificationUseCase notificationService;

    @GetMapping("/test-email")
    public ResponseEntity<String> testEmail() {
        // KORJATTU: Lisätty testiparametrit, jotta rajapinta täsmää
        notificationService.send("TESTI-ROSKIS", "Testialue 123"); 
        return ResponseEntity.ok("Testiviesti lähetetty Mailtrapiin! Kohde: TESTI-ROSKIS");
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long id) {
        try {
            System.out.println("DEBUG: Aloitetaan kuittaus ID:lle: " + id);
            
            manageTasksUseCase.complete(id);
            
            System.out.println("DEBUG: Kuittaus onnistui tietokantaan!");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("VIRHE KUITTAUKSESSA: " + e.getMessage());
            e.printStackTrace(); 
            return ResponseEntity.status(500).build();
        }
    }
}