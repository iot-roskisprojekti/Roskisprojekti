package fi.roskisprojekti.application.service.task;

import fi.roskisprojekti.application.port.in.broker.DomainEventSubscriber;
import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.domain.common.EndTimeStamp;
import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.alert.AlertState;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.entity.task.TaskId;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.FillLevel;

import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinEmptyingRequiredEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ManageTasksService implements ManageTasksUseCase {
    private final TaskRepository taskRepository;
    private final AlertRepository alertRepository;
    private final BinRepository binRepository;
    //private final DomainEventSubscriber eventSubscriber;

    /**
    @PostConstruct
    public void init() {
        eventSubscriber.subscribeToBinDomainEvents(this::onBinEvent);
    }

    private void onBinEvent(BinDomainEvent event) {
        if (event instanceof BinEmptyingRequiredEvent e) {
            taskRepository.save(Task.createNew()));
        }
    }
    */
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void complete(Long taskId) {
        Task task = taskRepository.findById(new TaskId(taskId))
            .orElseThrow(() -> new IllegalArgumentException("Tehtävää ei löydy: " + taskId));

        alertRepository.findById(task.getAlertId()).ifPresent(alert -> {
            task.complete(EndTimeStamp.now(), alert.getBinId()); // binId alertilta
            taskRepository.save(task);

            Alert acknowledged = new Alert(
                alert.getAlertId(),
                alert.getBinId(),
                AlertState.ACKNOWLEDGED,
                alert.getAlertType(),
                alert.getTriggeredAt(),
                EndTimeStamp.now()
            );
            alertRepository.save(acknowledged);

             // Nollaa täyttöaste demoa varten
            binRepository.findById(alert.getBinId()).ifPresent(bin -> {
                bin.updateFillLevel(new FillLevel(0.0));
                binRepository.save(bin);
        });
        });
    }

}
